package co.edu.icesi.Eshop.unit.service;

import co.edu.icesi.Eshop.dto.RoleDTO;
import co.edu.icesi.Eshop.error.exception.EShopException;
import co.edu.icesi.Eshop.mapper.RoleMapper;
import co.edu.icesi.Eshop.model.Role;
import co.edu.icesi.Eshop.repository.RoleRepository;
import co.edu.icesi.Eshop.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("Role created")
    public void testCreateRole(){
        var role = defaultRole();
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));

        roleService.save(roleMapper.fromRole(role));

        verify(roleRepository, times(1)).save(role);
    }

    @Test
    @DisplayName("Role not created. Duplicated name")
    public void testCreateRoleWithNameThatAlreadyExists(){
        var role = defaultRole();
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));

        var exception = assertThrows(EShopException.class, () -> roleService.save(roleMapper.fromRole(role)), "No exception was thrown");

        var error = exception.getError();
        var details = error.getDetails();
        assertEquals(1, details.size());
        var detail = details.get(0);
        assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
        assertEquals("Role with name ADMIN already exists", detail.getErrorMessage(), "Error message doesn't match");
    }

    private Role defaultRole(){
        return Role.builder()
                .roleId(UUID.fromString("6000962c-482d-43e7-b42a-da4596c08084"))
                .roleName("ADMIN")
                .description("A ADMIN role")
                .build();
    }

}
