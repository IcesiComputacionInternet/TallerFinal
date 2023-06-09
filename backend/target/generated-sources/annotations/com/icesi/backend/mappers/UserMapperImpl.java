package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.ShopUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2023-06-08T16:57:29-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
=======
    date = "2023-06-08T18:32:13-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230413-0857, environment: Java 17.0.7 (Eclipse Adoptium)"
>>>>>>> 32a316027a2b393a76c4c23c9b37039da120afe5
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

<<<<<<< HEAD
        shopUser.firstName( userCreateDTO.getFirstName() );
        shopUser.lastName( userCreateDTO.getLastName() );
=======
        shopUser.password( userCreateDTO.getPassword() );
>>>>>>> 32a316027a2b393a76c4c23c9b37039da120afe5
        shopUser.email( userCreateDTO.getEmail() );
        shopUser.phoneNumber( userCreateDTO.getPhoneNumber() );
        shopUser.address( userCreateDTO.getAddress() );
        shopUser.birthday( userCreateDTO.getBirthday() );

        return shopUser.build();
    }

    @Override
    public ShopUser fromUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        if ( userUpdateDTO == null ) {
            return null;
        }

        ShopUser.ShopUserBuilder shopUser = ShopUser.builder();

        shopUser.address( userUpdateDTO.getAddress() );
        shopUser.birthday( userUpdateDTO.getBirthday() );
        shopUser.email( userUpdateDTO.getEmail() );
        shopUser.firstName( userUpdateDTO.getFirstName() );
        shopUser.lastName( userUpdateDTO.getLastName() );
        shopUser.password( userUpdateDTO.getPassword() );
        shopUser.phoneNumber( userUpdateDTO.getPhoneNumber() );
        shopUser.userId( userUpdateDTO.getUserId() );

        return shopUser.build();
    }
}
