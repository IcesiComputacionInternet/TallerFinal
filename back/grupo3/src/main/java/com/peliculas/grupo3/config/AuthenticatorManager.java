package com.peliculas.grupo3.config;

import com.peliculas.grupo3.model.SecurityUser;
import com.peliculas.grupo3.security.CustomAuthentication;
import com.peliculas.grupo3.service.UserManagementService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticatorManager extends DaoAuthenticationProvider {
    public AuthenticatorManager(UserManagementService userManagementService, PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userManagementService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user){
        UsernamePasswordAuthenticationToken successAuthentication =
                (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(principal,authentication,user);
        SecurityUser securityUser =(SecurityUser) user;
        return new CustomAuthentication(successAuthentication,securityUser.getMovieUser().getUserId().toString());
    }
}
