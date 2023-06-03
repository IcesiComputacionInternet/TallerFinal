package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.mapper.UserMapper;
import co.com.icesi.Eshop.mapper.UserMapperImpl;
import co.com.icesi.Eshop.repository.RoleRepository;
import co.com.icesi.Eshop.repository.UserRepository;
import co.com.icesi.Eshop.service.UserService;
import co.com.icesi.Eshop.service.security.AuthoritiesService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class UserServiceTest {
    private UserService userService;
    private  UserRepository userRepository;
    private  RoleRepository roleRepository;
    private AuthoritiesService authoritiesService;
    private UserMapper userMapper;
    @BeforeEach
    public void init(){
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        userMapper = spy(UserMapperImpl.class);
        authoritiesService = mock(AuthoritiesService.class);
        userService = new UserService(userRepository,roleRepository,authoritiesService,userMapper);
    }
}
