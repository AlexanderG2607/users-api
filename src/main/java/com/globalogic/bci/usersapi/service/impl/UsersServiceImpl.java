package com.globalogic.bci.usersapi.service.impl;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.exception.UserAlreadyExistsException;
import com.globalogic.bci.usersapi.jpa.repositories.UserRepository;
import com.globalogic.bci.usersapi.mappers.UserMapper;
import com.globalogic.bci.usersapi.service.UsersService;
import com.globalogic.bci.usersapi.utils.TokenJWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String email = requestDTO.getEmail();

        if(userRepository.existsByEmail(email)){
            throw new UserAlreadyExistsException(email);
        }

        CreateUserResponseDTO responseDTO = userMapper.userToCCreateUserResponseDTO(
                userRepository.save(
                        userMapper.createUserRequestDTOToUser(requestDTO)));

        responseDTO.setToken(TokenJWTUtils.generateToken(email, requestDTO.getPassword()));
        return responseDTO;
    }

}
