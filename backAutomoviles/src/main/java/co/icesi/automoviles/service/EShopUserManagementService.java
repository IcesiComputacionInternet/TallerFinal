package co.icesi.automoviles.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.icesi.automoviles.model.SecurityEShopUser;
import co.icesi.automoviles.repository.EShopUserRepository;


@Service
@AllArgsConstructor
public class EShopUserManagementService implements UserDetailsService {

    private final EShopUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).map(SecurityEShopUser::new).orElseThrow(() -> new UsernameNotFoundException("username not found " + username));
    }
}
