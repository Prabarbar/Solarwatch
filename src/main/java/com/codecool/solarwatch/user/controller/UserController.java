package com.codecool.solarwatch.user.controller;

import com.codecool.solarwatch.role.model.Role;
import com.codecool.solarwatch.role.service.RoleService;
import com.codecool.solarwatch.security.jwt.JwtUtils;
import com.codecool.solarwatch.user.model.UserEntity;
import com.codecool.solarwatch.user.payload.JwtResponse;
import com.codecool.solarwatch.user.payload.LoginUserRequest;
import com.codecool.solarwatch.user.payload.RegisterUserRequest;
import com.codecool.solarwatch.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open-access/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @GetMapping("/get-users")
    public List<UserEntity> getUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/register")
    public  ResponseEntity<String> createUser(@RequestBody RegisterUserRequest request){
        //sprawdzam, czy jest użytkownik: admin i role: ADMIN i USER, jeśli nie to dodaje je do DB.
        if(roleService.findRoleByName("ROLE_ADMIN") == null){
            roleService.insertRole(new Role("ROLE_ADMIN"));
            roleService.insertRole(new Role("ROLE_USER"));
            userService.insertUser(new UserEntity("admin", encoder.encode("pass")));
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            userService.addRoleToUser("admin", "ROLE_USER");
        }
        UserEntity user= new UserEntity(request.getUsername(), encoder.encode(request.getPassword()));
        userService.insertUser(user);
        userService.addRoleToUser(request.getUsername(), "ROLE_USER");
        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUserRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }


    @DeleteMapping("delete-user")
    public void deleteUser(@RequestParam("user_id") int userId) {
        userService.deleteUserById(userId);
    }
    @PutMapping("update-password")
    public void updateUserPassword(@RequestParam("user_id") int userId, @RequestParam("password") String password){
        userService.updateUserById(userId, password);
    }
    @PutMapping("add-role")
    public void addRole(@RequestParam("username") String username, @RequestParam("role-name") String roleName){
        userService.addRoleToUser(username, roleName);
    }

}
