package com.codecool.solarwatch.role.service;

import com.codecool.solarwatch.role.model.Role;
import com.codecool.solarwatch.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl (RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void insertRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByName(String roleName) {
        Optional<Role> roleOptional = roleRepository.findByRoleName(roleName);
        if(roleOptional.isPresent()){
            return roleOptional.get();
        }
        return null;
    }
}
