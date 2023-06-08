package co.com.icesi.backend.unit.service.matcher;

import co.com.icesi.backend.model.ShopUser;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class UserMatcher implements ArgumentMatcher<ShopUser> {
    private ShopUser shopUserLeft;
    public UserMatcher(ShopUser shopUserLeft){this.shopUserLeft = shopUserLeft;}
    @Override
    public boolean matches(ShopUser shopUserRight) {
        return shopUserRight.getUserId() != null && shopUserRight.getRole() != null &&
                Objects.equals(shopUserRight.getFirstName(), shopUserLeft.getFirstName()) &&
                Objects.equals(shopUserRight.getLastName(), shopUserLeft.getLastName()) &&
                Objects.equals(shopUserRight.getEmail(), shopUserLeft.getEmail()) &&
                Objects.equals(shopUserRight.getAddress(), shopUserLeft.getAddress()) &&
                Objects.equals(shopUserRight.getPassword(), shopUserLeft.getPassword()) &&
                Objects.equals(shopUserRight.getBirthday(), shopUserLeft.getBirthday()) &&
                Objects.equals(shopUserRight.getPhoneNumber(), shopUserLeft.getPhoneNumber());
    }
}
