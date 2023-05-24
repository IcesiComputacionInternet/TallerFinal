package co.com.icesi.eShop_Back.controller;

import co.com.icesi.eShop_Back.controller.api.UserApi;
import co.com.icesi.eShop_Back.dto.RequestUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {


    @Override
    public void add(RequestUserDTO user) {

    }
}
