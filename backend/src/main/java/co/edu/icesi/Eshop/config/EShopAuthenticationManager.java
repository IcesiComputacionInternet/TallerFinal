package co.edu.icesi.Eshop.config;

import co.edu.icesi.Eshop.model.SecurityUser;
import co.edu.icesi.Eshop.security.CustomAuthentication;
import co.edu.icesi.Eshop.service.UserManagementService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EShopAuthenticationManager extends DaoAuthenticationProvider {

    public EShopAuthenticationManager(UserManagementService userManagementService, PasswordEncoder passwordEncoderConfiguration){
        this.setUserDetailsService(userManagementService);
        this.setPasswordEncoder(passwordEncoderConfiguration);
    }

    @Override
    public Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails userDetails){
        UsernamePasswordAuthenticationToken successAuthentication = (UsernamePasswordAuthenticationToken) super.createSuccessAuthentication(
            principal, authentication, userDetails
        );

        SecurityUser securityUser = (SecurityUser) userDetails;

        return new CustomAuthentication(successAuthentication, securityUser.eShopUser().getUserId().toString());
    }

}
