package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.UserAPI;
import co.edu.icesi.Eshop.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.Eshop.api.UserAPI.BASE_USER_URL;

@RestController
@RequestMapping(BASE_USER_URL)
@AllArgsConstructor
public class UserController  implements UserAPI {
    @Override
    public UserDTO getUser(String userEmail) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return null;
    }
}
