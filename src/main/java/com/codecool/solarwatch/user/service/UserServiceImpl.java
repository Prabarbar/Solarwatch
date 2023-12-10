package com.codecool.solarwatch.user.service;

import com.codecool.solarwatch.role.model.Role;
import com.codecool.solarwatch.role.repository.RoleRepository;
import com.codecool.solarwatch.user.model.UserEntity;
import com.codecool.solarwatch.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void insertUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserById(int userId, String password) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            UserEntity foundUser = optionalUser.get();
            foundUser.setPassword(password);
            userRepository.save(foundUser);
        }
    }
    @Override
    public void addRoleToUser(String username, String roleName){
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        Optional<Role> roleOptional = roleRepository.findByRoleName(roleName);
        if(userOptional.isPresent() && roleOptional.isPresent()){
            UserEntity user = userOptional.get();
            Role role = roleOptional.get();
            Set<Role> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}
