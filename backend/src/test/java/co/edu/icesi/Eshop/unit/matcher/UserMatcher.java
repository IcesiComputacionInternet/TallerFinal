package co.edu.icesi.Eshop.unit.matcher;

import co.edu.icesi.Eshop.model.EShopUser;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class UserMatcher implements ArgumentMatcher<EShopUser> {

    private EShopUser EShopUserLeft;

    public UserMatcher(EShopUser EShopUserLeft) {
        this.EShopUserLeft = EShopUserLeft;
    }

    @Override
    public boolean matches(EShopUser EShopUserRight) {
        return EShopUserRight.getUserId()!=null &&
                EShopUserRight.getRole()!=null &&
                EShopUserRight.getBirthday()!=null &&
                Objects.equals(EShopUserRight.getFirstName(), EShopUserLeft.getFirstName()) &&
                Objects.equals(EShopUserRight.getLastName(), EShopUserLeft.getLastName()) &&
                Objects.equals(EShopUserRight.getEmail(), EShopUserLeft.getEmail()) &&
                Objects.equals(EShopUserRight.getPhoneNumber(), EShopUserLeft.getPhoneNumber()) &&
                Objects.equals(EShopUserRight.getAddress(), EShopUserLeft.getAddress());
    }
}
