package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.RequestUserDTO;
import co.com.icesi.eShop_Back.mapper.UserMapper;
import co.com.icesi.eShop_Back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void add(RequestUserDTO userDTO) {
        var user = userMapper.fromUserDTO(userDTO);
        userRepository.save(user);
    }

}
