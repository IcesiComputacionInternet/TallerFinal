package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.Unit.util.CrudTest;
import co.com.icesi.Eshop.mapper.UserMapper;
//import co.com.icesi.Eshop.;
import co.com.icesi.Eshop.repository.RoleRepository;
import co.com.icesi.Eshop.repository.UserRepository;
import co.com.icesi.Eshop.service.UserService;
import co.com.icesi.Eshop.service.security.AuthoritiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class UserServiceTest implements CrudTest {
    private UserService userService;
    private  UserRepository userRepository;
    private  RoleRepository roleRepository;
    private AuthoritiesService authoritiesService;
    private UserMapper userMapper;

    @BeforeEach
    public void init(){
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        userMapper = spy(UserMapper.class);
        authoritiesService = mock(AuthoritiesService.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository,roleRepository,authoritiesService,encoder,userMapper);
    }


    @Test
    @Override
    public void createTest() {

    }

    @Test
    @Override
    public void readTest() {

    }
    @Test
    @Override
    public void updateTest() {

    }
    @Test
    @Override
    public void deleteTest() {

    }


}
