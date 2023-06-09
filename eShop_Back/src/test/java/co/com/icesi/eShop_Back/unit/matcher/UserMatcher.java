package co.com.icesi.eShop_Back.unit.matcher;

import co.com.icesi.eShop_Back.model.User;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

@AllArgsConstructor
public class UserMatcher implements ArgumentMatcher<User> {

    private User UserLeft;

    @Override
    public boolean matches(User UserRight) {
        return UserRight.getUserId() != null && UserRight.getRole() != null &&
                Objects.equals(UserRight.getFirstName(), UserLeft.getFirstName()) &&
                Objects.equals(UserRight.getLastName(), UserLeft.getLastName()) &&
                Objects.equals(UserRight.getEmail(), UserLeft.getEmail()) &&
                Objects.equals(UserRight.getPhoneNumber(), UserLeft.getPhoneNumber());
    }
}