package com.best.carsalesapi.controller.model;

import com.best.carsalesapi.controller.validator.ValueOfEnum;
import com.best.carsalesapi.entity.model.UserRoleEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequestModel {
    @ValueOfEnum(enumClass = UserRoleEnum.class)
    private String role;

    @NotNull
    private String name;
}
