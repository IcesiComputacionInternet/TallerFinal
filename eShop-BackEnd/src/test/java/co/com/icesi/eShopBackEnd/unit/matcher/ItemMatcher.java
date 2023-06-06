package co.com.icesi.eShopBackEnd.unit.matcher;

import co.com.icesi.eShopBackEnd.model.Item;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class ItemMatcher implements ArgumentMatcher<Item> {

    private final Item item;


    public ItemMatcher(Item item) {
        this.item = item;
    }


    @Override
    public boolean matches(Item itemOne) {

        return itemOne.getItemId() != null &&
                Objects.equals(itemOne.getName(), item.getName()) &&
                Objects.equals(itemOne.getDescription(), item.getDescription()) &&
                Objects.equals(itemOne.getPrice(), item.getPrice()) &&
                Objects.equals(itemOne.getBrand(), item.getBrand()) &&
                Objects.equals(itemOne.getStock(), item.getStock()) &&
                Objects.equals(itemOne.getImageURL(), item.getImageURL());

    }
}