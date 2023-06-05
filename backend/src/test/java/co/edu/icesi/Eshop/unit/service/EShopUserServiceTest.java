package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.dto.UserDTO;
import co.edu.icesi.Eshop.error.exception.EShopException;
import co.edu.icesi.Eshop.mapper.UserMapper;
import co.edu.icesi.Eshop.mapper.UserMapperImpl;
import co.edu.icesi.Eshop.model.Role;
import co.edu.icesi.Eshop.model.Roles;
import co.edu.icesi.Eshop.model.EShopUser;
import co.edu.icesi.Eshop.repository.RoleRepository;
import co.edu.icesi.Eshop.repository.UserRepository;
import co.edu.icesi.Eshop.service.UserService;
import co.edu.icesi.Eshop.unit.matcher.UserMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class EShopUserServiceTest {

    private UserService userService;

    private UserMapper userMapper;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @BeforeEach
    private void init(){
        userRepository=mock(UserRepository.class);
        userMapper=spy(UserMapperImpl.class);
        roleRepository=mock(RoleRepository.class);
       // passwordEncoder=mock(PasswordEncoder.class);

        userService=new UserService(userRepository, userMapper, roleRepository);//,passwordEncoder);
        userService=spy(userService);
    }

    @Test
    public void testCreateUser() {
        when(roleRepository.findByName(any())).thenReturn(Optional.of(defaultRole()));

        userService.save(defaultUserDTO());
        EShopUser EShopUser = defaultUser();

        verify(userMapper,times(1)).fromUserDTO(any());
        verify(userRepository, times(1)).save(argThat(new UserMatcher(EShopUser)));
        verify(userMapper,times(1)).fromUser(any());

    }

    @Test
    public void testCreateUserWhenEmailAlreadyExists(){
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(defaultUser()));

        try{
            userService.save(defaultUserDTO());
            fail();
        }catch (EShopException exception){
            String message= exception.getMessage();
            assertEquals("User email is in use. ",message);

            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
            assertEquals("User email julietav@example.com already exists", detail.getErrorMessage(), "Error message doesn't match");

            verify(userRepository,times(1)).findByEmail(any());
            verify(roleRepository,never()).findByName(any());
            verify(userMapper,never()).fromUserDTO(any());
            verify(userRepository, never()).save(any());
            verify(userMapper,never()).fromUser(any());

        }
    }

    @Test
    public void testCreateUserWhenPhoneNumberAlreadyExists(){

        when(userRepository.findByPhoneNumber(any())).thenReturn(Optional.of(defaultUser()));
        try{
            userService.save(defaultUserDTO());
            fail();
        }catch (EShopException exception){
            String message= exception.getMessage();
            assertEquals("Phone number is in use.",message);
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
            assertEquals("Phone Number 3184441232 already exists", detail.getErrorMessage(), "Error message doesn't match");

            verify(userRepository,times(1)).findByPhoneNumber(any());
            verify(roleRepository,never()).findByName(any());
            verify(userMapper,never()).fromUserDTO(any());
            verify(userRepository, never()).save(any());
            verify(userMapper,never()).fromUser(any());
        }
    }

    @Test
    public void testCreateUserWhenEmailAndPhoneNumberAlreadyExists(){
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(defaultUser()));
        when(userRepository.findByPhoneNumber(any())).thenReturn(Optional.of(defaultUser()));

        try{
            userService.save(defaultUserDTO());
            fail();
        }catch (EShopException exception){
            String message= exception.getMessage();
            assertEquals("User email is in use. Phone number is in use.",message);
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(2, details.size());
            var detail1 = details.get(0);
            assertEquals("ERR_DUPLICATED", detail1.getErrorCode(), "Code doesn't match");
            assertEquals("User email julietav@example.com already exists", detail1.getErrorMessage(), "Error message doesn't match");

            var detail2 = details.get(1);
            assertEquals("ERR_DUPLICATED", detail2.getErrorCode(), "Code doesn't match");
            assertEquals("Phone Number 3184441232 already exists", detail2.getErrorMessage(), "Error message doesn't match");

            verify(userRepository,times(1)).findByEmail(any());
            verify(userRepository,times(1)).findByPhoneNumber(any());
            verify(roleRepository,never()).findByName(any());
            verify(userMapper,never()).fromUserDTO(any());
            verify(userRepository, never()).save(any());
            verify(userMapper,never()).fromUser(any());
        }
    }

    @Test
    public void testCreateUserWhenRoleDoesNotExists(){
        try {
            userService.save(defaultUserDTO());
            fail();
        }catch (EShopException exception){
            String message= exception.getMessage();
            assertEquals("User role does not exists",message);
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            assertEquals("ERR_404", detail.getErrorCode(), "Code doesn't match");
            assertEquals("User role ADMIN not found", detail.getErrorMessage(), "Error message doesn't match");

            verify(userRepository,times(1)).findByEmail(any());
            verify(userRepository,times(1)).findByPhoneNumber(any());
            verify(roleRepository,times(1)).findByName(any());
            verify(userMapper,never()).fromUserDTO(any());
            verify(userRepository, never()).save(any());
            verify(userMapper,never()).fromUser(any());
        }
    }

    private UserDTO defaultUserDTO(){

        return UserDTO.builder()
                .firstName("Julieta")
                .lastName("Venegas")
                .email("julietav@example.com")
                .phoneNumber("3184441232")
                .password("julieta123")
                .roleName(Roles.ADMIN.toString())
                .birthday("2023-03-27")
                .address("955 Steele Street")
                .build();
    }

    private EShopUser defaultUser(){

        return EShopUser.builder()
                .firstName("Julieta")
                .lastName("Venegas")
                .email("julietav@example.com")
                .phoneNumber("3184441232")
                .password("julieta123")
                .role(defaultRole())
                .birthday(LocalDate.parse("2023-03-27",DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .address("955 Steele Street")
                .userId(UUID.randomUUID())
                .build();
    }

    private Role defaultRole(){
        return Role.builder()
                .roleName(Roles.ADMIN.toString())
                .description("is an administrator")
                .roleId(UUID.randomUUID())
                .build();
    }

}
