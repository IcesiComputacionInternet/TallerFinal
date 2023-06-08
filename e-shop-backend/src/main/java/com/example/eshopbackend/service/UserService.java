package com.example.eshopbackend.service;

import com.example.eshopbackend.dto.UserDTO;
import com.example.eshopbackend.mapper.UserMapper;
import com.example.eshopbackend.model.User;
import com.example.eshopbackend.repository.RoleRepository;
import com.example.eshopbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Builder
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    public User save(UserDTO userToSave){

        return null;
    }
}
