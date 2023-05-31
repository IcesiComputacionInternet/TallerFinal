package co.com.icesi.Eshop.service.security;

import co.com.icesi.Eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthoritiesService {

    private final UserRepository userRepository;

    public void validateAuthorities(String authorityName){
        /*
        Optional<UserPrincipal> userPrincipal = userRepository.findById(UUID.fromString(SecurityContext.getCurrentUserId()));
        userPrincipal.ifPresent(actual -> {
            if(actual.getAuthorities().stream().map(Authorities::getAuthority).noneMatch(authority -> authority.equals(authorityName))) {
                throw new RuntimeException("You are not authorized to perform this action");
            }
        });

         */
    }
}
