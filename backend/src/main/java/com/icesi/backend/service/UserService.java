package com.icesi.backend.service;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.mappers.UserMapper;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public Optional<ShopUser> createUser(UserCreateDTO user){
        Optional<String> email = Optional.ofNullable(user.getEmail());
        Optional<String> numberPhone = Optional.ofNullable(user.getPhoneNumber());

        if(email.isPresent()){
            Optional<ShopUser> existingUserByEmail = userRepository.findByEmail(user.getEmail());

            if(existingUserByEmail.isPresent()) {
                throw new DuplicateKeyException("Email already exists");
            }
            ShopUser newShopUser = userMapper.fromUserCreateDTO(user);
            return Optional.of(userRepository.save(newShopUser));
        } else if(numberPhone.isPresent()) {
            Optional<ShopUser> existingUserByPhone = userRepository.findByPhoneNumber(user.getPhoneNumber());

            if(existingUserByPhone.isPresent()){
                throw new DuplicateKeyException("Number phone already exists");
            }
            ShopUser newShopUser = userMapper.fromUserCreateDTO(user);
            return Optional.of(userRepository.save(newShopUser));
        }

        return Optional.empty();
    }
}
