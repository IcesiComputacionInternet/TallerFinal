package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.dto.response.ResponseUserDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(UserAPI.BASE_USER_URL)
public interface UserAPI {
    String BASE_USER_URL="/users";
    @GetMapping("/{userEmail}")
    ResponseUserDTO getUser(@PathVariable("userEmail")  String userEmail);
    @PostMapping("/create")
    ResponseUserDTO createUser(@Valid @RequestBody RequestUserDTO requestUserDTO);
    @GetMapping("/getUsers")
    List<ResponseUserDTO> getAllUsers();
}
