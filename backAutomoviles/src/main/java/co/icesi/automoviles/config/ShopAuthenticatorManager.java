package co.icesi.automoviles.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.icesi.automoviles.model.SecurityEShopUser;
import co.icesi.automoviles.security.CustomAuthentication;
import co.icesi.automoviles.service.EShopUserManagementService;


@Component
public class ShopAuthenticatorManager extends DaoAuthenticationProvider {
    public ShopAuthenticatorManager(EShopUserManagementService userManagementService,
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
        SecurityEShopUser securityEShopUser = (SecurityEShopUser) user;
        return new CustomAuthentication(successAuthentication,
                securityEShopUser.eShopUser().getEShopUserId().toString());
    }
    
}
