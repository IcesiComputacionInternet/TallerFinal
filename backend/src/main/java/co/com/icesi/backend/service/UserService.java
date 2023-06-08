package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.dto.response.ResponseUserDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.RoleMapper;
import co.com.icesi.backend.mapper.UserMapper;
import co.com.icesi.backend.model.Role;
import co.com.icesi.backend.model.User;
import co.com.icesi.backend.repository.RoleRepository;
import co.com.icesi.backend.repository.UserRepository;
import co.com.icesi.backend.security.CellphoneSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder = new CellphoneShopExceptionBuilder();

    public ResponseUserDTO saveUser(RequestUserDTO requestUserDTO) {
        Role role = roleRepository.findByName(requestUserDTO.getRole()).orElseThrow(
                () -> exceptionBuilder.notFoundException(
                        "The role with the specified name does not exists.", requestUserDTO.getRole()));
        validateIfEmailIsDuplicated(requestUserDTO.getEmail());
        validateIfPhoneIsDuplicated(requestUserDTO.getPhoneNumber());

        checkPermissions(requestUserDTO.getRole());
        User user = userMapper.fromUserDTO(requestUserDTO);
        user.setUserId(UUID.randomUUID());
        user.setRole(role);
        userRepository.save(user);
        ResponseUserDTO responseUserDTO = userMapper.fromUserToResponseUserDTO(user);
        responseUserDTO.setRole(roleMapper.fromRoleToRoleDTO(role));
        return responseUserDTO;
    }

    public void checkPermissions(String roleToAssign) {
        String role = CellphoneSecurityContext.getCurrentUserRole();
        if((roleToAssign.equals(UserRole.ADMIN.getRole()) && role.equals(UserRole.USER.getRole()))
                || (role.equals(UserRole.SHOP.getRole()))){
            throw exceptionBuilder.noPermissionException(
                    "A normal user or a bank user can't create users of type ADMIN."
            );
        }
    }

    private void validateIfEmailIsDuplicated(String userEmail){
        if(userRepository.findByEmail(userEmail).isPresent()){
            throw exceptionBuilder.duplicatedValueException(
                    "A user with the entered email already exists.", userEmail);
        }
    }

    private void validateIfPhoneIsDuplicated(String userPhone){
        if(userRepository.findByPhoneNumber(userPhone).isPresent()){
            throw exceptionBuilder.duplicatedValueException(
                    "A user with the entered phone already exists.", userPhone);
        }
    }

    public ResponseUserDTO getUser(String userEmail) {
        return userMapper.fromUserToResponseUserDTO(
                userRepository.findByEmail(userEmail).orElseThrow(
                        () -> exceptionBuilder.notFoundException(
                                "The user with the specified email does not exists.", userEmail))
        );
    }
    public List<ResponseUserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::fromUserToResponseUserDTO)
                .collect(Collectors.toList());
    }
}
