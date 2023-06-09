package com.example.eshopbackend.config;

import com.example.eshopbackend.model.SecurityUser;
import com.example.eshopbackend.security.CustomAuthentication;
import com.example.eshopbackend.service.UserManagementService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EshopAuthenticationManager extends DaoAuthenticationProvider {
    public EshopAuthenticationManager(UserManagementService userManagementService,
                                      PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userManagementService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                      UserDetails user){
        UsernamePasswordAuthenticationToken succesAuthentication =
                (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(principal,
                        authentication, user);
        SecurityUser securityUser = (SecurityUser) user;
        return new CustomAuthentication(succesAuthentication,
                securityUser.user().getUserId().toString());
    }
}
