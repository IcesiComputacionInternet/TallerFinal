package co.com.icesi.backend.service;

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

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder;

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
        if((roleToAssign.equals("ADMIN") && CellphoneSecurityContext.getCurrentUserRole().equals("USER"))||(CellphoneSecurityContext.getCurrentUserRole().equals("SHOP"))){
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
}
