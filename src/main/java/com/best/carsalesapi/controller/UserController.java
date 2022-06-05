package com.best.carsalesapi.controller;

import com.best.carsalesapi.controller.model.CreateUserRequestModel;
import com.best.carsalesapi.entity.UserEntity;
import com.best.carsalesapi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/user")
@Validated
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public UserEntity createNewUser(@RequestBody @Valid CreateUserRequestModel input) {
        return userService.createNewUser(input);
    }
}
