package co.com.icesi.eShop_Back.unit.service;

import co.com.icesi.eShop_Back.dto.request.RequestUserDTO;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.mapper.UserMapper;
import co.com.icesi.eShop_Back.mapper.UserMapperImpl;
import co.com.icesi.eShop_Back.model.Role;
import co.com.icesi.eShop_Back.model.User;
import co.com.icesi.eShop_Back.repository.RoleRepository;
import co.com.icesi.eShop_Back.repository.UserRepository;
import co.com.icesi.eShop_Back.service.UserService;
import co.com.icesi.eShop_Back.unit.matcher.UserMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        UserMapper userMapper = spy(UserMapperImpl.class);
        PasswordEncoder encoder = spy(PasswordEncoder.class);
        userService = new UserService(userRepository, roleRepository, userMapper, encoder);
    }

    @Test
    public void testCreate_ValidUserDTO_UserSaved() {
        RequestUserDTO userDTO = defaultUserDTO();
        User user = defaultIcesiUser();
        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())).thenReturn(false);
        when(roleRepository.findByName(userDTO.getRole())).thenReturn(Optional.of(defaultRole()));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.create(userDTO);

        verify(roleRepository).findByName(userDTO.getRole());
        verify(userRepository, times(1)).save(argThat(new UserMatcher(defaultIcesiUser())));
    }

    @Test
    public void testCreateWithRole_RoleNotFound_ThrowsCustomException() {
        RequestUserDTO userDTO = defaultUserDTO();
        String role = "INVALID_ROLE";
        when(roleRepository.findByName(role)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> userService.create(userDTO, role));
        verify(roleRepository).findByName(role);
    }


    @Test
    public void testCreateWithRole_ValidUserDTOAndRole_UserSavedWithRole() {
        RequestUserDTO userDTO = defaultUserDTO();
        String role = "ADMIN";
        User user = defaultIcesiUser();
        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName(role)).thenReturn(Optional.of(defaultRole()));

        userService.create(userDTO, role);

        verify(roleRepository).findByName(userDTO.getRole());
        verify(userRepository, times(1)).save(argThat(new UserMatcher(defaultIcesiUser())));
    }


    @Test
    public void testCreate_UserWithEmailExists_ThrowsCustomException() {
        RequestUserDTO userDTO = defaultUserDTO();
        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(true);

        assertThrows(CustomException.class, () -> userService.create(userDTO));
        verify(userRepository, never()).save(any(User.class));
        verify(roleRepository, never()).findByName(anyString());
    }

    @Test
    public void testCreate_UserWithPhoneExists_ThrowsCustomException() {
        RequestUserDTO userDTO = defaultUserDTO();
        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())).thenReturn(true);

        assertThrows(CustomException.class, () -> userService.create(userDTO));
        verify(userRepository, never()).save(any(User.class));
        verify(roleRepository, never()).findByName(anyString());
    }

    @Test
    public void testCreate_InvalidRole_ThrowsCustomException() {
        RequestUserDTO userDTO = defaultUserDTO();
        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())).thenReturn(false);
        when(roleRepository.findByName(userDTO.getRole())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> userService.create(userDTO));
        verify(userRepository, never()).save(any(User.class));
        verify(roleRepository).findByName(userDTO.getRole());
    }

    @Test
    public void testDelete_ValidId_UserDeleted() {
        UUID userId = UUID.randomUUID();
        userService.delete(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    public void testGet_ValidId_UserFound() {
        UUID userId = UUID.randomUUID();
        User user = defaultIcesiUser();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        RequestUserDTO result = userService.get(userId);

        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(user.getRole().getName(), result.getRole());
        verify(userRepository).findById(userId);
    }

    @Test
    public void testGet_InvalidId_ThrowsCustomException() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> userService.get(userId));
        verify(userRepository).findById(userId);
    }

    private RequestUserDTO defaultUserDTO(){
        return RequestUserDTO.builder()
                .firstName("Arturo")
                .lastName("Diaz")
                .email("prueba@gmail.com")
                .phoneNumber("12345")
                .password("12345")
                .role("ADMIN")
                .build();
    }

    private User defaultIcesiUser(){
        return User.builder()
                .firstName("Arturo")
                .lastName("Diaz")
                .email("prueba@gmail.com")
                .phoneNumber("12345")
                .password("12345")
                .role(defaultRole())
                .build();
    }

    private Role defaultRole(){
        return Role.builder()
                .roleId(UUID.randomUUID())
                .name("ADMIN")
                .description("Ingeniero de sistemas")
                .build();
    }
}
