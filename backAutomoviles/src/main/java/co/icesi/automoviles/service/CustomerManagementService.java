package co.icesi.automoviles.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.icesi.automoviles.model.SecurityCustomer;
import co.icesi.automoviles.repository.CustomerRepository;


@Service
@AllArgsConstructor
public class CustomerManagementService implements UserDetailsService {

    private final CustomerRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).map(SecurityCustomer::new).orElseThrow(() -> new UsernameNotFoundException("username not found " + username));
    }
}
