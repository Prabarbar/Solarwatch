package com.codecool.solarwatch.user.service;

import com.codecool.solarwatch.user.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    void deleteUserById(int userId);

    void insertUser(UserEntity user);

    void updateUserById(int userId, String password);

    void addRoleToUser(String username, String roleName);
}
