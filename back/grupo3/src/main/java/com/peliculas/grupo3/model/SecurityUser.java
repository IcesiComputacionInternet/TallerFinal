package com.peliculas.grupo3.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private MovieUser movieUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(movieUser).map(MovieUser::getRole).map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return movieUser.getPassword();
    }

    public MovieUser getMovieUser(){
        return movieUser;
    }

    @Override
    public String getUsername() {
        return movieUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
