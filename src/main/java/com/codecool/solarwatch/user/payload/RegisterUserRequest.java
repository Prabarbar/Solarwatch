package com.codecool.solarwatch.user.payload;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}
