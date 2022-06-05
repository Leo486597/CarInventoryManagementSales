package com.best.carsalesapi.service;

import com.best.carsalesapi.controller.model.CreateUserRequestModel;
import com.best.carsalesapi.entity.UserEntity;
import com.best.carsalesapi.entity.model.UserRoleEnum;
import com.best.carsalesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity createNewUser(CreateUserRequestModel model) {
        UserEntity user = UserEntity.builder()
                .role(UserRoleEnum.valueOf(model.getRole()))
                .name(model.getName())
                .build();
        return userRepository.save(user);
    }
}
