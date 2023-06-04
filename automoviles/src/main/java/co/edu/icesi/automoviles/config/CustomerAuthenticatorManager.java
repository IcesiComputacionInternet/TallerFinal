package co.edu.icesi.automoviles.config;

import co.edu.icesi.automoviles.model.SecurityCustomer;
import co.edu.icesi.automoviles.security.CustomAuthentication;
import co.edu.icesi.automoviles.service.CustomerManagementService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerAuthenticatorManager extends DaoAuthenticationProvider {
    public CustomerAuthenticatorManager(CustomerManagementService userManagementService,
                                     PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userManagementService);
        this.setPasswordEncoder(passwordEncoder);
    }
    @Override
    public Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                      UserDetails user) {
        UsernamePasswordAuthenticationToken successAuthentication =
                (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(principal,
                        authentication, user);
        SecurityCustomer securityCustomer = (SecurityCustomer) user;
        return new CustomAuthentication(successAuthentication,
                securityCustomer.customer().getCustomerId().toString());
    }
    
}
