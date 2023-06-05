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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        validateEmailAndPhoneNumber(userDTO);
        Role role=roleRepository.findByName(userDTO.getRoleName()).orElseThrow(createEShopException(
                "User role does not exists",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "User role",userDTO.getRoleName())
        ));
        User user=userMapper.fromUserDTO(userDTO);
        user.setBirthday(LocalDate.parse(userDTO.getBirthday(),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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

    public void validateEmailAndPhoneNumber(UserDTO user){
        List<DetailBuilder> details= new ArrayList<>();
        String exMessage="";

        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            details.add(new DetailBuilder(ErrorCode.ERR_DUPLICATED, "User email",user.getEmail() ));
            exMessage+="User email is in use. ";
        }

        if(userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()){
            details.add(new DetailBuilder(ErrorCode.ERR_DUPLICATED, "Phone Number",user.getPhoneNumber()));
            exMessage+="Phone number is in use.";
        }

        if(!details.isEmpty()){
            throw createEShopException(
                    exMessage,
                    HttpStatus.CONFLICT,
                    details.toArray(DetailBuilder[]::new)
            ).get();
        }
    }

}
