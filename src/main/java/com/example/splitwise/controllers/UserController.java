package com.example.splitwise.controllers;

import com.example.splitwise.dtos.RegisterUserRequestDto;
import com.example.splitwise.dtos.RegisterUserResponseDto;
import com.example.splitwise.models.User;
import com.example.splitwise.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request)
    {
        User user = userService.RegisterUser(request.getUserName(),request.getPassword(),request.getPhoneNumber());

        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();
        registerUserResponseDto.setUser(user);
        return registerUserResponseDto;
    };
}
