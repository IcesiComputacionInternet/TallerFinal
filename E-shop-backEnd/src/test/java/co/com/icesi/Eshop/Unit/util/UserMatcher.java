package co.com.icesi.Eshop.Unit.util;

import co.com.icesi.Eshop.model.UserPrincipal;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class UserMatcher implements ArgumentMatcher<UserPrincipal> {
    @Override
    public boolean matches(UserPrincipal userPrincipal) {
        return false;
    }
}
