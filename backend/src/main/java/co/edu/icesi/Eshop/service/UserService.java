package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.UserMapper;
import co.edu.icesi.Eshop.model.Role;
import co.edu.icesi.Eshop.model.User;
import co.edu.icesi.Eshop.repository.RoleRepository;
import co.edu.icesi.Eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopException;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private UserMapper userMapper;

    private RoleRepository roleRepository;


    public UserDTO save(UserDTO userDTO){


        Role role=roleRepository.findByName(userDTO.getRoleName()).orElseThrow(createEShopException(
                "User role does not exists",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "User role",userDTO.getRoleName())
        ));
        User user=userMapper.fromUserDTO(userDTO);
        user.setRole(role);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserId(UUID.randomUUID());
        return userMapper.fromUser(userRepository.save(user));

    }
    public UserDTO getUser(String userEmail) {

        return  userMapper.fromUser(userRepository.findByEmail(userEmail).orElseThrow(
                createEShopException(
                        "User email not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "User email",userEmail )
                )
        ));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUser)
                .toList();
    }



}
