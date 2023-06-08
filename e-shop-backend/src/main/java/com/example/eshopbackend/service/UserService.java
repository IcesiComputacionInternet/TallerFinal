package com.example.eshopbackend.service;

import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.dto.UserDTO;
import com.example.eshopbackend.mapper.UserMapper;
import com.example.eshopbackend.model.User;
import com.example.eshopbackend.repository.RoleRepository;
import com.example.eshopbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Builder
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    public User save(UserDTO userToSave){
        //Validate email and phone are not repeated
        validatePhoneAndEmail(userToSave.getPhoneNumber(), userToSave.getEmail());
        //Validate role exists
        validateRole(userToSave.getRole());

        //create user
        User user = userMapper.fromUserDTO(userToSave);
        user.setUserId(UUID.randomUUID());

        return userRepository.save(user);
    }

    private void validateRole(RoleDTO role) {
        Optional.ofNullable(role).orElseThrow(
            () -> new RuntimeException("The user must have a role"));

        roleRepository.findByRoleName(role.getRoleName()).orElseThrow(
                () -> new RuntimeException("Role does not exist"));
    }

    private void validatePhoneAndEmail(String phoneNumber, String email) {
        boolean emailPresent = validateEmail(email);
        boolean phonePresent = validatePhone(phoneNumber);

        //If both email and phone are already registered.
        if (emailPresent && phonePresent){
            throw new RuntimeException("Both email and phoneNumber are taken by other user already.");
        }
        if (emailPresent){
            throw new RuntimeException("Email is already taken by other user");
        }
        if (phonePresent){
            throw new RuntimeException("Phone is already taken by other user");
        }
    }

    private boolean validatePhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    private boolean validateEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
