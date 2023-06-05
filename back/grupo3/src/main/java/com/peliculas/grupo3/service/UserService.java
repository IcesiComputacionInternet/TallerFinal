package com.peliculas.grupo3.service;

import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.dto.response.UserResponseDTO;
import com.peliculas.grupo3.mapper.UserMapper;
import com.peliculas.grupo3.mapper.response.UserResponseMapper;
import com.peliculas.grupo3.model.MovieUser;
import com.peliculas.grupo3.model.Role;
import com.peliculas.grupo3.repository.RoleRepository;
import com.peliculas.grupo3.repository.UserRepository;
import com.peliculas.grupo3.error.util.MovieExceptionBuilder;
import com.peliculas.grupo3.security.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final UserResponseMapper userResponseMapper;


    public UserDTO save(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new RuntimeException("Ya hay un usuario con este email");
        }

        if(userDTO.getRoleName().isEmpty()){
            throw new RuntimeException("El usuario no tiene rol");
        }

        if(userRepository.findByPhone(userDTO.getPhone()).isPresent()){
            throw new RuntimeException("Ya hay un usuario con este celular");
        }



       Role role = roleRepository.findByName(userDTO.getRoleName()).orElseThrow(
                ()-> MovieExceptionBuilder.createMovieException("No existe un rol con este nombre", HttpStatus.NOT_FOUND,"ROLE_NOT_FOUND") );



        MovieUser user = userMapper.fromUserDTO(userDTO);
        user.setRole(role);
        user.setUserId(UUID.randomUUID());
        userRepository.save(user);

        return userDTO;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(userResponseMapper::fromUser).toList();
    }

    public Optional<UserResponseDTO> findByName(String name) {
        return userRepository.findByFirstName(name).map(userResponseMapper::fromUser);
    }

    public UserResponseDTO getCurrentUser() {
        MovieUser user = userRepository.findById(UUID.fromString(SecurityContext.getCurrentUserId())).orElseThrow(
                ()-> MovieExceptionBuilder.createMovieException("No existe un usuario con este id", HttpStatus.NOT_FOUND,"USER_NOT_FOUND") );
        return userResponseMapper.fromUser(user);
    }
}
