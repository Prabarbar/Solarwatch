package com.codecool.solarwatch.user.model;

import com.codecool.solarwatch.role.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "users_roles", joinColumns = @JoinColumn(name = "users_user_id"), inverseJoinColumns = @JoinColumn(name = "roles_role_id"))
    private Set<Role> roles = new HashSet<>();

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;;
    }
}
