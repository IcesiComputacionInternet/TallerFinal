package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.UserAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static co.edu.icesi.Eshop.api.UserAPI.BASE_USER_URL;

@RestController
@RequestMapping(BASE_USER_URL)
@AllArgsConstructor
public class UserController  implements UserAPI {
}
