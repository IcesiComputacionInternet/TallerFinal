package co.edu.icesi.Eshop.unit.matcher;

import co.edu.icesi.Eshop.model.User;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class UserMatcher implements ArgumentMatcher<User> {

    private User userLeft;

    public UserMatcher(User userLeft) {
        this.userLeft = userLeft;
    }

    @Override
    public boolean matches(User userRight) {
        return userRight.getUserId()!=null &&
                userRight.getRole()!=null &&
                userRight.getBirthday()!=null &&
                Objects.equals(userRight.getFirstName(),userLeft.getFirstName()) &&
                Objects.equals(userRight.getLastName(),userLeft.getLastName()) &&
                Objects.equals(userRight.getEmail(),userLeft.getEmail()) &&
                Objects.equals(userRight.getPhoneNumber(),userLeft.getPhoneNumber()) &&
                Objects.equals(userRight.getAddress(),userLeft.getAddress());
    }
}
