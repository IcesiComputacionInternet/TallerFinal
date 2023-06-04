package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.OrderAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static co.edu.icesi.Eshop.api.OrderAPI.BASE_ORDER_URL;

@RestController
@RequestMapping(BASE_ORDER_URL)
@AllArgsConstructor
public class OrderController implements OrderAPI {
}
