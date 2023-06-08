package co.com.icesi.eShop_Back.service.security;

import co.com.icesi.eShop_Back.model.security.SecurityUser;
import co.com.icesi.eShop_Back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManagementService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
