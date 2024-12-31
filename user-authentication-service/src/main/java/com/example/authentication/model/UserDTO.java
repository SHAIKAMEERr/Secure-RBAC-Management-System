package com.example.authentication.model;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password);
        return userEntity;
    }
}