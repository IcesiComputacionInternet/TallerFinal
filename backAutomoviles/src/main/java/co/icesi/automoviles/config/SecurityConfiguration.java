package co.icesi.automoviles.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import co.icesi.automoviles.api.EShopUserAPI;
import co.icesi.automoviles.api.RoleAPI;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final ShopAuthenticatorManager eShopUserAuthenticatorManager;
    private final String secret = "longenoughsecrettotestjwtencrypt";

    @Bean
    public AuthenticationManager authenticationManagerBean(){
        return new ProviderManager(eShopUserAuthenticatorManager);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthorizationManager<RequestAuthorizationContext> access)
            throws Exception{
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(access)) //permitAll para que funcione | access(access)
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public JwtDecoder jwtEncoder(){
        byte[] bytes = secret.getBytes();
        SecretKeySpec key = new SecretKeySpec(bytes,0,bytes.length, "RSA");
        return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
    }

    @Bean
    public JwtEncoder jwtDecoder(){return new NimbusJwtEncoder(new ImmutableSecret<>(secret.getBytes()));}

    @Bean
    public AuthorizationManager<RequestAuthorizationContext> requestMatcherAuthorizationManager
            (HandlerMappingIntrospector introspector){

        MvcRequestMatcher registerEShopUser = new MvcRequestMatcher(introspector, EShopUserAPI.ROOT_PATH);
        registerEShopUser.setMethod(HttpMethod.POST);

        MvcRequestMatcher login = new MvcRequestMatcher(introspector,"/token");

        RequestMatcher permitAll = new AndRequestMatcher(login, registerEShopUser);
        RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder =
                RequestMatcherDelegatingAuthorizationManager.builder()
                        .add(permitAll,(context,other)->new AuthorizationDecision(true));

        MvcRequestMatcher tempMvcRequestMatcher;

        tempMvcRequestMatcher = new MvcRequestMatcher(introspector, RoleAPI.ROOT_PATH);
        tempMvcRequestMatcher.setMethod(HttpMethod.POST);
        managerBuilder.add(tempMvcRequestMatcher, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        tempMvcRequestMatcher = new MvcRequestMatcher(introspector, EShopUserAPI.ROOT_PATH+"/{eShopUserId}/role/{roleName}");
        tempMvcRequestMatcher.setMethod(HttpMethod.PATCH);
        managerBuilder.add(tempMvcRequestMatcher, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        AuthorizationManager<HttpServletRequest> manager = managerBuilder.build();
        return (authentication, object) -> manager.check(authentication,object.getRequest());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                return configuration;
            }
        };
    }
    
}
