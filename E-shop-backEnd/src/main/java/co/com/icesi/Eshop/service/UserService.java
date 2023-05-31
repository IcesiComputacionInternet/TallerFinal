package co.com.icesi.Eshop.service;

import co.com.icesi.Eshop.dto.request.UserDTO;
import co.com.icesi.Eshop.dto.response.UserResponseDTO;
import co.com.icesi.Eshop.mapper.UserMapper;
import co.com.icesi.Eshop.model.UserPrincipal;
import co.com.icesi.Eshop.repository.UserRepository;
import co.com.icesi.Eshop.service.security.AuthoritiesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthoritiesService authoritiesService;
    private final UserMapper userMapper;


    public UserResponseDTO createUser(UserDTO userDTO) {
        authoritiesService.validateAuthorities("ADMIN");
        userAlreadyExists(userDTO.getEmail());
        phoneAlreadyExists(userDTO.getPhoneNumber());

        return userMapper.toUserResponseDTO(userRepository.save(userMapper.toUser(userDTO)));
    }

    private void userAlreadyExists(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new RuntimeException("UserPrincipal with " + email + " already exists");
        });
    }

    private void phoneAlreadyExists(String phoneNumber) {
        userRepository.findByPhoneNumber(phoneNumber).ifPresent(user -> {
            throw new RuntimeException("UserPrincipal with " + phoneNumber + " already exists");
        });
    }

    public UserResponseDTO updateUser(UserDTO userDTO) {
        UserPrincipal userPrincipalInDB = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new RuntimeException("UserPrincipal with " + userDTO.getEmail() + " does not exists"));
        UserPrincipal userPrincipalUpdated = userMapper.toUser(userDTO);

        userPrincipalUpdated.setUserId(userPrincipalInDB.getUserId());

        userRepository.save(userPrincipalUpdated);

        return userMapper.toUserResponseDTO(userRepository.save(userPrincipalUpdated));
    }

    public UserResponseDTO deleteUser(UserDTO userDTO) {
        Optional<UserPrincipal> user = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new RuntimeException("UserPrincipal with " + userDTO.getEmail() + " does not exists")));
        return user.map(value -> {
            userRepository.delete(value);
            return userMapper.toUserResponseDTO(value);
        }).orElse(null);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponseDTO).collect(Collectors.toList());
    }
}
