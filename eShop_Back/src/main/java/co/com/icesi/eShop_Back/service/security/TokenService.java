package co.com.icesi.eShop_Back.service.security;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseAuthDTO;
import co.com.icesi.eShop_Back.mapper.UserMapper;
import co.com.icesi.eShop_Back.model.User;
import co.com.icesi.eShop_Back.repository.UserRepository;
import co.com.icesi.eShop_Back.security.CustomAuthentication;
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
    private UserRepository userRepository;
    private UserMapper userMapper;

    public ResponseAuthDTO generateToken(Authentication authentication){
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                //.expiresAt(now.plus(Period.ofDays(2).multipliedBy(1000)))
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("userId", customAuthentication.getName())
                .build();
        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        var token = this.encoder.encode(encoderParameters).getTokenValue();
        Optional<User> user = userRepository.findById(UUID.fromString(customAuthentication.getName()));
        var responseUserDTO = userMapper.fromUserToResponseUserDTO(user.orElseThrow(()-> new RuntimeException("User not found")));
        return ResponseAuthDTO.builder()
                .token(token)
                .user(responseUserDTO)
                .build();
    }

}
