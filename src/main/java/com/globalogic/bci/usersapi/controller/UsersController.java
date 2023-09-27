package com.globalogic.bci.usersapi.controller;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users-api")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateUserResponseDTO> signUp(@Validated @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return ResponseEntity.ok(usersService.signUp(createUserRequestDTO));
    }
}
