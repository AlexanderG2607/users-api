package com.globalogic.bci.usersapi.service.impl;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.jpa.domains.User;
import com.globalogic.bci.usersapi.jpa.repositories.UserRepository;
import com.globalogic.bci.usersapi.mappers.UserMapper;
import com.globalogic.bci.usersapi.service.UsersService;
import org.modelmapper.ModelMapper;
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
    public CreateUserResponseDTO signUp(CreateUserRequestDTO createUserRequestDTO) {
       return userMapper.userToCCreateUserResponseDTO(
               userRepository.save(
                       userMapper.createUserRequestDTOToUser(createUserRequestDTO)));
    }

}
