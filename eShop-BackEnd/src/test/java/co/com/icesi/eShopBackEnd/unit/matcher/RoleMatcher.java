package co.com.icesi.eShopBackEnd.unit.matcher;

import co.com.icesi.eShopBackEnd.model.Role;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class RoleMatcher implements ArgumentMatcher<Role> {

    private final Role role;



    public RoleMatcher(Role role){
        this.role = role;
    }




    @Override
    public boolean matches(Role roleOne) {
        return roleOne.getRoleId() != null &&
                Objects.equals(role.getRoleName(), roleOne.getRoleName()) &&
                Objects.equals(role.getDescription(), roleOne.getDescription());
    }
}
