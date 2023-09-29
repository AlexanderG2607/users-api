package com.globalogic.bci.usersapi.service.impl;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.dto.PhoneDTO;
import com.globalogic.bci.usersapi.exception.InvalidTokenException;
import com.globalogic.bci.usersapi.exception.UserAlreadyExistsException;
import com.globalogic.bci.usersapi.exception.UserNotFoundException;
import com.globalogic.bci.usersapi.exception.UserUnauthorizedException;
import com.globalogic.bci.usersapi.jpa.domains.Phone;
import com.globalogic.bci.usersapi.jpa.domains.User;
import com.globalogic.bci.usersapi.jpa.repositories.PhoneRepository;
import com.globalogic.bci.usersapi.jpa.repositories.UserRepository;
import com.globalogic.bci.usersapi.mappers.PhoneMapper;
import com.globalogic.bci.usersapi.mappers.UserMapper;
import com.globalogic.bci.usersapi.service.UsersService;
import com.globalogic.bci.usersapi.utils.TokenJWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final PhoneRepository phoneRepository;

    private final UserMapper userMapper;

    private final PhoneMapper phoneMapper;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository, UserMapper userMapper, PhoneMapper phoneMapper) {
        this.userMapper = userMapper;
        this.phoneMapper = phoneMapper;
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }


    @Override
    public CreateUserResponseDTO signUp(CreateUserRequestDTO requestDTO) {
        String email = requestDTO.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }

        requestDTO.setPassword(hashPassword(requestDTO.getPassword()));

        User user = userMapper.createUserRequestDTOToUser(requestDTO);
        User savedUser = userRepository.save(user);

        if(Objects.nonNull(requestDTO.getPhones())){
            savedUser.setPhones(phoneRepository.saveAll(createPhonesFromPhoneDTOList(requestDTO.getPhones(), savedUser)));
        }

        CreateUserResponseDTO responseDTO = userMapper.userToCCreateUserResponseDTO(savedUser);
        responseDTO.setToken(TokenJWTUtils.generateToken(email, requestDTO.getPassword()));

        return responseDTO;
    }

    private List<Phone> createPhonesFromPhoneDTOList(List<PhoneDTO> phones, User user){
        return phones.stream().map(phoneDTO -> {
            Phone phone = phoneMapper.phoneDTOToPhone(phoneDTO);
            phone.setUser(user);
            return phone;
        }).collect(Collectors.toList());
    }

    @Override
    public CreateUserResponseDTO login(String token) {
        Optional<Jws<Claims>> validatedToken = TokenJWTUtils.validateJWTToken(token);

        Jws<Claims> claimsJws = validatedToken.orElseThrow(() -> new InvalidTokenException("Token inválido"));

        String email = claimsJws.getBody().get("email", String.class);

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        user.setLastLogin(LocalDateTime.now());
        user = userRepository.save(user);

        CreateUserResponseDTO response = userMapper.userToCCreateUserResponseDTO(user);

        if(user.getPassword()
                .equals(claimsJws.getBody().get("password", String.class))){
            response.setToken(TokenJWTUtils.generateToken(email, response.getPassword()));

            return response;
        }

        throw new UserUnauthorizedException("No Autorizado");

    }

    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
