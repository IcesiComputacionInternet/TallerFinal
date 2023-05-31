package co.com.icesi.Eshop.api;

import co.com.icesi.Eshop.dto.request.UserDTO;
import co.com.icesi.Eshop.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping(UserApi.BASE_URL)
public interface UserApi {
    String BASE_URL = "/api/users";

    @PostMapping("/create")
    UserResponseDTO createUser(UserDTO userDTO);

    @PostMapping("/update")
    UserResponseDTO updateUser(UserDTO userDTO);

    @PostMapping("/delete")
    UserResponseDTO deleteUser(UserDTO userDTO);

    @GetMapping("/all")
    List<UserResponseDTO> getAllUsers();
}
