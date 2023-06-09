package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.mapper.UserMapper;
import co.com.icesi.eShop_Back.model.User;
import co.com.icesi.eShop_Back.repository.RoleRepository;
import co.com.icesi.eShop_Back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public void create(RequestUserDTO userDTO) {
        var user = validationUser(userDTO);
        user.setRole(roleRepository.findByName(userDTO.getRole()).orElseThrow(() -> new CustomException("Role not found")));
        userRepository.save(user);
    }

    public void create(RequestUserDTO userDTO, String role) {
        var user = validationUser(userDTO);
        user.setRole(roleRepository.findByName(role).orElseThrow(() -> new CustomException("Role not found")));
        userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public RequestUserDTO get(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));
        var response = userMapper.fromUser(user);
        response.setRole(user.getRole().getName());
        return response;
    }

    private User validationUser(RequestUserDTO userDTO){
        boolean emailExists = userRepository.existsByEmail(userDTO.getEmail());
        boolean phoneExists = userRepository.existsByPhoneNumber(userDTO.getPhoneNumber());

        if (emailExists && phoneExists){ throw new CustomException("Email and Phone is already used");}
        if (emailExists){ throw new CustomException("Email already exists");}
        if (phoneExists){ throw new CustomException("Phone number already exists");}

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        var user = userMapper.fromUserDTO(userDTO);
        user.setUserId(UUID.randomUUID());
        return user;
    }

}
