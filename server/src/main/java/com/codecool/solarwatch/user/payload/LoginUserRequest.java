package com.codecool.solarwatch.user.payload;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String username;
    private String password;
}
