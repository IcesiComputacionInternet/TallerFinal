package co.edu.icesi.Eshop.unit.matcher;

import co.edu.icesi.Eshop.model.Item;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class ItemMatcher implements ArgumentMatcher<Item> {

    private Item itemLeft;

    public ItemMatcher(Item itemLeft){
        this.itemLeft = itemLeft;
    }

    @Override
    public boolean matches(Item itemRight) {
        return itemRight.getItemId() != null &&
                Objects.equals(itemRight.getDescription(), itemLeft.getDescription()) &&
                Objects.equals(itemRight.getName(), itemLeft.getName()) &&
                Objects.equals(itemRight.getPrice(), itemLeft.getPrice()) &&
                Objects.equals(itemRight.getImageUrl(), itemLeft.getImageUrl()) &&
                Objects.equals(itemRight.getCategory(), itemLeft.getCategory()) &&
                Objects.equals(itemRight.getMinVoltage(), itemLeft.getMinVoltage()) &&
                Objects.equals(itemRight.getMaxVoltage(), itemLeft.getMaxVoltage()) &&
                Objects.equals(itemRight.getSourceOfEnergy(), itemLeft.getSourceOfEnergy()) &&
                Objects.equals(itemRight.getLevelOfEfficiency(), itemLeft.getLevelOfEfficiency()) &&
                Objects.equals(itemRight.getMarca(), itemLeft.getMarca()) &&
                Objects.equals(itemRight.getModel(), itemLeft.getModel()) &&
                Objects.equals(itemRight.getGuarantee(), itemLeft.getGuarantee());
    }
}
