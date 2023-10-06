package com.globalogic.bci.usersapi.service.impl;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.exception.UserNotFoundException;
import com.globalogic.bci.usersapi.exception.UserUnauthorizedException;
import com.globalogic.bci.usersapi.jpa.domains.User;
import com.globalogic.bci.usersapi.jpa.repositories.UserRepository;
import com.globalogic.bci.usersapi.mappers.UserMapper;
import com.globalogic.bci.usersapi.service.UsersService;
import com.globalogic.bci.usersapi.utils.TokenJWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    public CreateUserResponseDTO signUp(CreateUserRequestDTO requestDTO) {

        requestDTO.setPassword(hashPassword(requestDTO.getPassword()));

        CreateUserResponseDTO responseDTO = userMapper.userToCreateUserResponseDTO(
                userRepository.save(
                        userMapper.createUserRequestDTOToUser(requestDTO)));

        responseDTO.setToken(TokenJWTUtils.generateToken(requestDTO.getEmail(), requestDTO.getPassword()));

        return responseDTO;
    }


    @Override
    public CreateUserResponseDTO login(String token) {
        Jws<Claims> claimsJws = TokenJWTUtils.validateAndExtractJWTClaims(token);

        String email = claimsJws.getBody().get("email", String.class);

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        user.setLastLogin(LocalDateTime.now());
        user = userRepository.save(user);

        CreateUserResponseDTO response = userMapper.userToCreateUserResponseDTO(user);

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
