package co.icesi.automoviles.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.icesi.automoviles.model.SecurityCustomer;
import co.icesi.automoviles.security.CustomAuthentication;
import co.icesi.automoviles.service.CustomerManagementService;


@Component
public class ShopAuthenticatorManager extends DaoAuthenticationProvider {
    public ShopAuthenticatorManager(CustomerManagementService userManagementService,
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
