package co.com.icesi.backend.unit.service.matcher;

import co.com.icesi.backend.model.User;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class UserMatcher implements ArgumentMatcher<User> {
    private User userLeft;
    public UserMatcher(User userLeft){this.userLeft = userLeft;}
    @Override
    public boolean matches(User userRight) {
        return userRight.getUserId() != null && userRight.getRole() != null &&
                Objects.equals(userRight.getFirstName(), userLeft.getFirstName()) &&
                Objects.equals(userRight.getLastName(), userLeft.getLastName()) &&
                Objects.equals(userRight.getEmail(), userLeft.getEmail()) &&
                Objects.equals(userRight.getAddress(), userLeft.getAddress()) &&
                Objects.equals(userRight.getPassword(), userLeft.getPassword()) &&
                Objects.equals(userRight.getBirthday(), userLeft.getBirthday()) &&
                Objects.equals(userRight.getPhoneNumber(),userLeft.getPhoneNumber());
    }
}
