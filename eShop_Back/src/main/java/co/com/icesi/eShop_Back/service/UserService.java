package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.mapper.UserMapper;
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
        boolean emailExists = userRepository.existsByEmail(userDTO.getEmail());
        boolean phoneExists = userRepository.existsByPhoneNumber(userDTO.getPhoneNumber());

        if (emailExists && phoneExists){ throw new RuntimeException("Email and Phone is already used");}
        if (emailExists){ throw new RuntimeException("Email already exists");}
        if (phoneExists){ throw new RuntimeException("Phone number already exists");}
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        var user = userMapper.fromUserDTO(userDTO);

        user.setUserId(UUID.randomUUID());
        user.setRole(roleRepository.findByName(userDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found")));
        userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public void update(RequestUserDTO userDTO) {
        //TODO
    }

    public RequestUserDTO get(UUID id) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        var response = userMapper.fromUser(user);
        response.setRole(user.getRole().getName());
        return response;
    }

}
