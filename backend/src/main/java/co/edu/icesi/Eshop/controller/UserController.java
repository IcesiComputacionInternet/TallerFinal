package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.UserAPI;
import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.UserAPI.BASE_USER_URL;

@RestController
@RequestMapping(BASE_USER_URL)
@AllArgsConstructor
@CrossOrigin
public class UserController  implements UserAPI {
    private final UserService userService;
    @Override
    public UserDTO getUser(String userEmail) {
        return userService.getUser(userEmail);
    }

    @Override
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return userService.register(userDTO);
    }
}
