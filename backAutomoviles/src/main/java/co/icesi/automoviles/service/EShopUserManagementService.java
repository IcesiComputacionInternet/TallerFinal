package co.icesi.automoviles.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.icesi.automoviles.model.SecurityEShopUser;
import co.icesi.automoviles.repository.EShopUserRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EShopUserManagementService implements UserDetailsService {

    private final EShopUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityEShopUser> securityEShopUser = repository.findByPhoneNumber(username).map(SecurityEShopUser::new);
        if (securityEShopUser.isPresent()){
            return securityEShopUser.get();
        }
        return repository.findByEmail(username).map(SecurityEShopUser::new).orElseThrow(() -> new UsernameNotFoundException("username not found " + username));
    }
}
