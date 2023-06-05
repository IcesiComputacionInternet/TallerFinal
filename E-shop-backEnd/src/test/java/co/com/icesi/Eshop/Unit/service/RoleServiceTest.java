package co.com.icesi.Eshop.Unit.service;

import co.com.icesi.Eshop.mapper.RoleMapper;
import co.com.icesi.Eshop.mapper.RoleMapperImpl;
import co.com.icesi.Eshop.mapper.UserMapper;
import co.com.icesi.Eshop.repository.RoleRepository;
import co.com.icesi.Eshop.repository.UserRepository;
import co.com.icesi.Eshop.service.RoleService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class RoleServiceTest {
    private  RoleRepository roleRepository;
    private RoleMapper roleMapper;

    private UserRepository userRepository;

    private RoleService roleService;

    @BeforeEach
    public void init(){
        roleMapper = spy(RoleMapperImpl.class);
        roleRepository = mock(RoleRepository.class);
        userRepository = mock(UserRepository.class);
        roleService = new RoleService(roleRepository,userRepository,roleMapper);
    }
}
