package co.com.icesi.Eshop.api;

import co.com.icesi.Eshop.dto.request.UserDTO;
import co.com.icesi.Eshop.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(UserApi.BASE_URL)
public interface UserApi {
    String BASE_URL = "/api/users";

    @PostMapping("/create")
    UserResponseDTO createUser(UserDTO userDTO);

    @PutMapping("/update")
    UserResponseDTO updateUser(UserDTO userDTO);

    @DeleteMapping("/delete/{email}")
    UserResponseDTO deleteUser(@PathVariable String email);

    @GetMapping("/all")
    List<UserResponseDTO> getAllUsers();
}
