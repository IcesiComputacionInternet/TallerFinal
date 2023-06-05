package co.com.icesi.backend.unit.service;

import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.RoleMapper;
import co.com.icesi.backend.mapper.UserMapper;
import co.com.icesi.backend.mapper.UserMapperImpl;
import co.com.icesi.backend.model.Role;
import co.com.icesi.backend.model.User;
import co.com.icesi.backend.repository.RoleRepository;
import co.com.icesi.backend.repository.UserRepository;
import co.com.icesi.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private RoleMapper roleMapper;
    private CellphoneShopExceptionBuilder exceptionBuilder;

    @BeforeEach
    private void init(){
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        userMapper = spy(UserMapperImpl.class);
        roleMapper = spy(RoleMapper.class);
        exceptionBuilder = spy(CellphoneShopExceptionBuilder.class);
        userService = new UserService(userRepository, roleRepository, userMapper,roleMapper,exceptionBuilder);
        userService=spy(userService);
    }
/*
    @Test
    public void testCreateUser(){
        // Arrange
        var userDTO = defaultUserDTO();
        var icesiRole= defaultIcesiRole();
        when(roleRepository.findByName(any())).thenReturn(Optional.of(icesiRole));
        doNothing().when(userService).checkPermissions(any());
        // Act
        userService.saveUser(userDTO);
        // Assert
        User newIcesiUser = User.builder()
                .role(defaultIcesiRole())
                .firstName("Damiano")
                .lastName("David")
                .email("ykaar@gmail.com")
                .phoneNumber("3152485689")
                .password("taEkbs08")
                .build();
        verify(userRepository, times(1)).findByEmail(any());
        verify(userRepository, times(1)).findByPhoneNumber(any());
        verify(roleRepository, times(1)).findByName(any());
        verify(userRepository,times( 1)).save(argThat(new UserMatcher(newIcesiUser)));
        verify(userMapper, times(1)).fromUserDTO(any());
        verify(userMapper, times(1)).fromUserToResponseUserDTO(any());
    }

    @Test
    public void testCreateUserWithoutRole(){
        // Arrange
        var userDTO = defaultUserDTO1();
        try {
            // Act
            userService.saveUser(userDTO);
            fail();
        } catch (AccountSystemException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            // Assert
            assertEquals("Role does not exist.", message);
            assertEquals("ERR_404", detail.getErrorCode(), "Code doesn't match");
            assertEquals("Role with name:  not found.", detail.getErrorMessage(), "Error message doesn't match");
        }
    }

    @Test
    public void testCreateUserWithRoleThatDoesNotExists(){
        // Arrange
        var userDTO = defaultUserDTO();
        try {
            // Act
            userService.saveUser(userDTO);
            fail();
        } catch (AccountSystemException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);
            // Assert
            assertEquals("Role does not exist.", message);
            assertEquals("ERR_404", detail.getErrorCode(), "Code doesn't match");
            assertEquals("Role with name: Director SIS not found.", detail.getErrorMessage(), "Error message doesn't match");
        }
    }

    @Test
    public void testCreateUserWhenEmailAlreadyExists(){
        // Arrange
        var userDTO = defaultUserDTO();
        var icesiRole= defaultIcesiRole();
        var icesiUser= defaultIcesiUser();
        when(roleRepository.findByName(any())).thenReturn(Optional.of(icesiRole));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(icesiUser));
        try {
            // Act
            userService.saveUser(userDTO);
            fail();
        } catch (AccountSystemException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail1 = details.get(0);
            // Assert
            assertEquals("A user with the entered email already exists.", message);
            assertEquals("ERR_DUPLICATED", detail1.getErrorCode(), "Code doesn't match");
            assertEquals("Resource user with field email: ykaar@gmail.com, already exists.", detail1.getErrorMessage(), "Error message doesn't match");
        }
    }

    @Test
    public void testCreateUserWhenPhoneAlreadyExists(){
        // Arrange
        var userDTO = defaultUserDTO();
        var icesiRole= defaultIcesiRole();
        var icesiUser= defaultIcesiUser();
        when(roleRepository.findByName(any())).thenReturn(Optional.of(icesiRole));
        when(userRepository.findByPhoneNumber(any())).thenReturn(Optional.of(icesiUser));
        try {
            // Act
            userService.saveUser(userDTO);
            fail();
        } catch (AccountSystemException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            assertEquals(1, details.size());
            var detail1 = details.get(0);
            // Assert
            assertEquals("A user with the entered phone already exists.", message);
            assertEquals("ERR_DUPLICATED", detail1.getErrorCode(), "Code doesn't match");
            assertEquals("Resource user with field phone: 3152485689, already exists.", detail1.getErrorMessage(), "Error message doesn't match");
        }
    }

    private RequestUserDTO defaultUserDTO(){
        return RequestUserDTO.builder()
                .role("Director SIS")
                .firstName("Damiano")
                .lastName("David")
                .email("ykaar@gmail.com")
                .phoneNumber("3152485689")
                .password("taEkbs08")
                .build();
    }

    private RequestUserDTO defaultUserDTO1(){
        return RequestUserDTO.builder()
                .role("")
                .firstName("Damiano")
                .lastName("David")
                .email("ykaar@gmail.com")
                .phoneNumber("3152485689")
                .password("taEkbs08")
                .build();
    }

    private User defaultIcesiUser(){
        return User.builder()
                .userId(UUID.randomUUID())
                .role(defaultIcesiRole())
                .firstName("Damiano")
                .lastName("David")
                .email("ykaar@gmail.com")
                .phoneNumber("3152485689")
                .password("taEkbs08")
                .build();
    }

    private Role defaultIcesiRole() {
        return Role.builder()
                .roleId(UUID.randomUUID())
                .description("Director del programa de Ingenieria de sistemas")
                .roleName("Director SIS")
                .build();
    }*/
}
