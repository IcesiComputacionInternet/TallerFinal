package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.models.ShopUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T13:14:52-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.address( userCreateDTO.getAddress() );
        shopUser.birthday( userCreateDTO.getBirthday() );
        shopUser.email( userCreateDTO.getEmail() );
        shopUser.firstName( userCreateDTO.getFirstName() );
        shopUser.lastName( userCreateDTO.getLastName() );
        shopUser.phoneNumber( userCreateDTO.getPhoneNumber() );

        return shopUser.build();
    }
}
