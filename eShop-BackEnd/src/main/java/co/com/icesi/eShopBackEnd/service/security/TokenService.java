package co.com.icesi.eShopBackEnd.service.security;

import co.com.icesi.eShopBackEnd.dto.response.TokenDTO;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.security.CustomAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TokenService {

    private final JwtEncoder encoder;
    private final CustomerRepository customerRepository;



    public TokenDTO generateToken(Authentication authentication){
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                //.expiresAt(now.plus(3650, ChronoUnit.DAYS))
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope",scope)
                .claim("customerId",customAuthentication.getName())
                .build();
        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),claims);
        var token = this.encoder.encode(encoderParameters).getTokenValue();
        Optional<Customer> customer = customerRepository.getCustomerById(UUID.fromString(customAuthentication.getName()));
        var role = customer.isPresent() ? customer.get().getRole().toString() :"NONE";
        return TokenDTO.builder()
                .token(token)
                .role(role)
                .build();
    }


}
