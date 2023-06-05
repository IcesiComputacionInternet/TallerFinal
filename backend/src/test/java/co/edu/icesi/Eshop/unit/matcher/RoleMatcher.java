package co.edu.icesi.Eshop.unit.matcher;

import co.edu.icesi.Eshop.model.Role;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class RoleMatcher implements ArgumentMatcher<Role> {

    private Role roleLeft;

    public RoleMatcher(Role roleLeft){
        this.roleLeft = roleLeft;
    }

    @Override
    public boolean matches(Role roleRight) {
        return roleRight.getRoleId() != null &&
                Objects.equals(roleRight.getRoleName(), roleLeft.getRoleName()) &&
                Objects.equals(roleRight.getDescription(), roleLeft.getDescription());
    }
}
