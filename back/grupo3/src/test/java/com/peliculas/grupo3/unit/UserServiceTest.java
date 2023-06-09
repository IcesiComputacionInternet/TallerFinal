package com.peliculas.grupo3.unit;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.mapper.UserMapper;
import com.peliculas.grupo3.mapper.UserMapperImpl;
import com.peliculas.grupo3.mapper.response.UserResponseMapper;
import com.peliculas.grupo3.mapper.response.UserResponseMapperImpl;
import com.peliculas.grupo3.model.MovieUser;
import com.peliculas.grupo3.model.Role;
import com.peliculas.grupo3.repository.RoleRepository;
import com.peliculas.grupo3.repository.UserRepository;
import com.peliculas.grupo3.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTest {

    private UserService userService;

    private UserMapper userMapper;

    private UserResponseMapper userResponseMapper;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    Role user = Role.builder()
            .roleId(UUID.randomUUID())
            .description("Role for demo")
            .name("USER")
            .build();

    @BeforeEach
    public void init(){
        userRepository=mock(UserRepository.class);
        roleRepository=mock(RoleRepository.class);
        userMapper=spy(UserMapperImpl.class);
        userResponseMapper=spy(UserResponseMapperImpl.class);
        userService= new UserService(userRepository,userMapper,roleRepository,userResponseMapper);
    }

    @Test
    public void testCreateRole(){
        when(roleRepository.findByName(any())).thenReturn(Optional.of(user));
        userService.save(defaultUserDTO());
        MovieUser user = defaultUser();
        verify(userRepository,times(1)).findByEmail(argThat(name->name.equals(user.getEmail())));
    }

    public UserDTO defaultUserDTO(){
        return new UserDTO(
                "nombre",
                "apellido",
                "123@gmail.co",
                "+57 305 123 432",
                "123",
                "USER",
                "address",
                "10/10/2000");
    }

    public MovieUser defaultUser(){
        return MovieUser.builder()
                .userId(UUID.randomUUID())
                .phone("+57 305 123 432")
                .firstName("nombre")
                .lastName("apellido")
                .email("123@gmail.co")
                .phone("+57 305 123 432")
                .password("123")
                .address("address")
                .birthDate("10/10/2000")
                .role(Role.builder().name("USER").build())
                .build();
    }
}
