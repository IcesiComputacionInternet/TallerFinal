package co.com.icesi.Eshop.Unit.util;

import co.com.icesi.Eshop.model.Role;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class RoleMatcher implements ArgumentMatcher<Role> {
    @Override
    public boolean matches(Role role) {
        return false;
    }
}
