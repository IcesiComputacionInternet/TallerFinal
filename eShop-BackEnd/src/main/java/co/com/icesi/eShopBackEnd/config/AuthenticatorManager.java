package co.com.icesi.eShopBackEnd.config;

import co.com.icesi.eShopBackEnd.model.SecurityUser;
import co.com.icesi.eShopBackEnd.security.CustomAuthentication;
import co.com.icesi.eShopBackEnd.service.security.UserManagementService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorManager extends DaoAuthenticationProvider {

    public AuthenticatorManager(UserManagementService userManagementService, PasswordEncoder passwordEncoder) {
        super();
        this.setUserDetailsService(userManagementService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user){
        UsernamePasswordAuthenticationToken successAuth = (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(principal, authentication, user);
        SecurityUser securityUser = (SecurityUser) user;
        return new CustomAuthentication(successAuth, securityUser.customer().getCustomerId().toString());

    }
}
