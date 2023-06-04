package co.com.icesi.backend.unit.service.matcher;

import co.com.icesi.backend.model.Role;
import org.mockito.ArgumentMatcher;

public class RoleMatcher implements ArgumentMatcher<Role>{

    @Override
    public boolean matches(Role roleRight) {
        return false;
    }
}
