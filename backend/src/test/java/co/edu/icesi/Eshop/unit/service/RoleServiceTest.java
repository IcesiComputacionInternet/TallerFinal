package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.mapper.RoleMapper;
import co.edu.icesi.Eshop.repository.RoleRepository;
import co.edu.icesi.Eshop.service.RoleService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class RoleServiceTest {

    private RoleService roleService;

    private RoleRepository roleRepository;

    private RoleMapper roleMapper;

    @BeforeEach
    private void init(){
        roleRepository = mock(RoleRepository.class);
        roleMapper = spy(RoleMapper.class);
        roleService = new RoleService(roleRepository, roleMapper);
    }


}
