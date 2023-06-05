package co.com.icesi.backend.unit.service.matcher;

import co.com.icesi.backend.model.Role;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class RoleMatcher implements ArgumentMatcher<Role>{

    private Role roleLeft;

    public RoleMatcher(Role roleLeft){this.roleLeft = roleLeft;}

    @Override
    public boolean matches(Role roleRight) {
        return roleRight.getRoleId() != null &&
                Objects.equals(roleRight.getDescription(), roleLeft.getDescription()) &&
                Objects.equals(roleRight.getRoleName(), roleLeft.getRoleName());
    }
}
