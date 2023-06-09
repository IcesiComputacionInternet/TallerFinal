package co.icesi.automoviles.config;

import co.icesi.automoviles.api.*;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

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

        MvcRequestMatcher login = new MvcRequestMatcher(introspector,"/token");

        MvcRequestMatcher registerEShopUser = new MvcRequestMatcher(introspector, EShopUserAPI.ROOT_PATH);
        registerEShopUser.setMethod(HttpMethod.POST);

        MvcRequestMatcher getItemById = new MvcRequestMatcher(introspector, ItemAPI.ROOT_PATH+"/{itemId}");
        getItemById.setMethod(HttpMethod.GET);

        MvcRequestMatcher getAllItems = new MvcRequestMatcher(introspector, ItemAPI.ROOT_PATH);
        getAllItems.setMethod(HttpMethod.GET);

        RequestMatcher permitAll = new AndRequestMatcher(login, registerEShopUser, getItemById, getAllItems);
        RequestMatcherDelegatingAuthorizationManager.Builder managerBuilder =
                RequestMatcherDelegatingAuthorizationManager.builder()
                        .add(permitAll,(context,other)->new AuthorizationDecision(true));

        MvcRequestMatcher rolesEndpoints = new MvcRequestMatcher(introspector, RoleAPI.ROOT_PATH);
        managerBuilder.add(rolesEndpoints, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        MvcRequestMatcher assignRole = new MvcRequestMatcher(introspector, EShopUserAPI.ROOT_PATH+"/{eShopUserId}/role/{roleName}");
        assignRole.setMethod(HttpMethod.PATCH);
        managerBuilder.add(assignRole, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        MvcRequestMatcher categoryEndpoints = new MvcRequestMatcher(introspector, CategoryAPI.ROOT_PATH);
        managerBuilder.add(categoryEndpoints, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        MvcRequestMatcher updateCategory = new MvcRequestMatcher(introspector, CategoryAPI.ROOT_PATH+"/{categoryId}");
        updateCategory.setMethod(HttpMethod.PATCH);
        managerBuilder.add(updateCategory, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        MvcRequestMatcher createItem = new MvcRequestMatcher(introspector, ItemAPI.ROOT_PATH);
        createItem.setMethod(HttpMethod.POST);
        managerBuilder.add(createItem, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_SHOP"));

        MvcRequestMatcher updateItem = new MvcRequestMatcher(introspector, ItemAPI.ROOT_PATH+"/{itemId}");
        updateItem.setMethod(HttpMethod.PATCH);
        managerBuilder.add(updateItem, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_SHOP"));

        MvcRequestMatcher deleteItem = new MvcRequestMatcher(introspector, ItemAPI.ROOT_PATH+"/{itemId}");
        deleteItem.setMethod(HttpMethod.DELETE);
        managerBuilder.add(deleteItem, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_SHOP"));

        MvcRequestMatcher createPurchaseOrder = new MvcRequestMatcher(introspector, PurchaseOrderAPI.ROOT_PATH);
        createPurchaseOrder.setMethod(HttpMethod.POST);
        managerBuilder.add(createPurchaseOrder, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER"));

        MvcRequestMatcher getPurchaseOrderById = new MvcRequestMatcher(introspector, PurchaseOrderAPI.ROOT_PATH+"/{purchaseOrderId}");
        getPurchaseOrderById.setMethod(HttpMethod.GET);
        managerBuilder.add(getPurchaseOrderById, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER", "SCOPE_SHOP"));

        MvcRequestMatcher getAllPurchaseOrder = new MvcRequestMatcher(introspector, PurchaseOrderAPI.ROOT_PATH);
        getAllPurchaseOrder.setMethod(HttpMethod.GET);
        managerBuilder.add(getAllPurchaseOrder, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_USER", "SCOPE_SHOP"));

        MvcRequestMatcher updatePurchaseOrderStateById = new MvcRequestMatcher(introspector, PurchaseOrderAPI.ROOT_PATH+"/{purchaseOrderId}/{newState}");
        updatePurchaseOrderStateById.setMethod(HttpMethod.PATCH);
        managerBuilder.add(updatePurchaseOrderStateById, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN", "SCOPE_SHOP"));

        MvcRequestMatcher updatePurchaseOrderById = new MvcRequestMatcher(introspector, PurchaseOrderAPI.ROOT_PATH+"/{purchaseOrderId}");
        updatePurchaseOrderById.setMethod(HttpMethod.PATCH);
        managerBuilder.add(updatePurchaseOrderById, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

        MvcRequestMatcher deletePurchaseOrderById = new MvcRequestMatcher(introspector, PurchaseOrderAPI.ROOT_PATH+"/{purchaseOrderId}");
        deletePurchaseOrderById.setMethod(HttpMethod.DELETE);
        managerBuilder.add(deletePurchaseOrderById, AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_ADMIN"));

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
