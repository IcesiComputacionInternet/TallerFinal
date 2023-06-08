package co.com.icesi.backend.controller;

import co.com.icesi.backend.api.UserAPI;
import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.dto.response.ResponseUserDTO;
import co.com.icesi.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.com.icesi.backend.api.UserAPI.BASE_USER_URL;

@RestController
@RequestMapping(BASE_USER_URL)
@AllArgsConstructor
public class UserController implements UserAPI {
    private final UserService userService;

    @Override
    public ResponseUserDTO getUser(String userEmail) {
        return userService.getUser(userEmail);
    }


    @Override
    public List<ResponseUserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public ResponseUserDTO createUser(RequestUserDTO requestUserDTO) {
        return userService.saveUser(requestUserDTO);
    }

}
