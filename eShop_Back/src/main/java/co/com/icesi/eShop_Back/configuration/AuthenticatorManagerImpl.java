package co.com.icesi.eShop_Back.configuration;

import co.com.icesi.eShop_Back.model.security.SecurityUser;
import co.com.icesi.eShop_Back.security.CustomAuthentication;
import co.com.icesi.eShop_Back.service.security.UserManagementService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorManagerImpl extends DaoAuthenticationProvider {

    public AuthenticatorManagerImpl(UserManagementService userManagementService, PasswordEncoder passwordEncoder) {
        super();
        this.setUserDetailsService(userManagementService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user){
        UsernamePasswordAuthenticationToken successAuth = (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(principal, authentication, user);
        SecurityUser securityUser = (SecurityUser) user;
        return new CustomAuthentication(successAuth, securityUser.user().getUserId().toString());

    }
}
