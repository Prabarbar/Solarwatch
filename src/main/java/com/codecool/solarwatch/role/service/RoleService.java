package com.codecool.solarwatch.role.service;

import com.codecool.solarwatch.role.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void insertRole(Role role);
    List<Role> getRoles();
    Role findRoleByName(String roleName);
}
