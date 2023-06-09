package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.ShopUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T01:29:27-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.password( userCreateDTO.getPassword() );
        shopUser.email( userCreateDTO.getEmail() );
        shopUser.phoneNumber( userCreateDTO.getPhoneNumber() );

        return shopUser.build();
    }

    @Override
    public ShopUser fromUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        if ( userUpdateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.userId( userUpdateDTO.getUserId() );
        shopUser.password( userUpdateDTO.getPassword() );
        shopUser.firstName( userUpdateDTO.getFirstName() );
        shopUser.lastName( userUpdateDTO.getLastName() );
        shopUser.email( userUpdateDTO.getEmail() );
        shopUser.phoneNumber( userUpdateDTO.getPhoneNumber() );
        shopUser.address( userUpdateDTO.getAddress() );
        shopUser.birthday( userUpdateDTO.getBirthday() );

        return shopUser.build();
    }
}
