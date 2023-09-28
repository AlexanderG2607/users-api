package com.globalogic.bci.usersapi.service;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsersService {

    CreateUserResponseDTO signUp(@RequestBody CreateUserRequestDTO createUserRequestDTO);

    CreateUserResponseDTO login(String token);
}
