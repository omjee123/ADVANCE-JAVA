package com.example.SpringSecurity.service;

import com.example.SpringSecurity.dto.LoginDTO;
import com.example.SpringSecurity.dto.TokenDTO;
import com.example.SpringSecurity.dto.UserDTO;
import com.example.SpringSecurity.entity.Role;
import com.example.SpringSecurity.entity.User;
import com.example.SpringSecurity.repo.RoleRepo;
import com.example.SpringSecurity.repo.UserRepo;
import com.example.SpringSecurity.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final CustomUserDetailsService userDetailsService;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, CustomUserDetailsService userDetailsService, RoleRepo roleRepo, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
        this.roleRepo = roleRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDTO userDTO){
User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        List<Role> roles = new ArrayList<>();
        for (String roleName : userDTO.getRoles()){
            Role role = roleRepo.findByName(roleName).orElseThrow();

            roles.add(role);
        }
        if (!roles.isEmpty()){
            user.setRoles(roles);
        }
        return userRepo.save(user);
    }

    public TokenDTO loginUser(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(loginDTO.getUsername());

            String accessToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);

            return new TokenDTO(accessToken, refreshToken);
        }

        throw new RuntimeException("Invalid credentials");
    }
}
