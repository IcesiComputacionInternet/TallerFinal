package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.dto.CreateUserDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseUserDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
import co.com.icesi.eShopBackEnd.mapper.UserMapper;
import co.com.icesi.eShopBackEnd.model.User;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import co.com.icesi.eShopBackEnd.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public ResponseUserDTO save(CreateUserDTO user) {
        User newUser = userMapper.fromCreateUserDTO(user);
        newUser.setRole(roleRepository.findByRoleName("USER").orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"role")
                )
        ));
        newUser.setUserId(UUID.randomUUID());
        newUser.setPassword(encoder.encode(user.password()));
        ResponseUserDTO userResponse = userMapper.fromUserToResponseUserDTO(userRepository.save(newUser));
        userResponse.setUserId(newUser.getUserId());

        return userResponse;
    }

}
