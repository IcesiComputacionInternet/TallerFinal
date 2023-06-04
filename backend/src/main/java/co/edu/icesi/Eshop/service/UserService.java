package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.mapper.UserMapper;
import co.edu.icesi.Eshop.repository.RoleRepository;
import co.edu.icesi.Eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private UserMapper userMapper;

    private RoleRepository roleRepository;



}
