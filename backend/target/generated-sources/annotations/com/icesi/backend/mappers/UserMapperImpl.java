package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.models.ShopUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T16:57:29-0500",
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

        shopUser.firstName( userCreateDTO.getFirstName() );
        shopUser.lastName( userCreateDTO.getLastName() );
        shopUser.email( userCreateDTO.getEmail() );
        shopUser.phoneNumber( userCreateDTO.getPhoneNumber() );
        shopUser.address( userCreateDTO.getAddress() );
        shopUser.birthday( userCreateDTO.getBirthday() );

        return shopUser.build();
    }
}
