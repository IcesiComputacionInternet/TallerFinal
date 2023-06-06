package co.com.icesi.backend.unit.service.matcher;

import co.com.icesi.backend.model.Cellphone;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class CellphoneMatcher implements ArgumentMatcher<Cellphone> {
    private Cellphone cellphoneLeft;

    public CellphoneMatcher(Cellphone cellphoneLeft){
        this.cellphoneLeft = cellphoneLeft;
    }
    @Override
    public boolean matches(Cellphone cellphoneRight) {
        CategoryMatcher categoryMatcher = new CategoryMatcher(cellphoneRight.getCategory());
        return cellphoneRight.getCellphoneId() != null &&
                Objects.equals(cellphoneRight.getName(), cellphoneLeft.getName()) &&
                Objects.equals(cellphoneRight.getDescription(), cellphoneLeft.getDescription()) &&
                Objects.equals(cellphoneRight.getPrice(), cellphoneLeft.getPrice()) &&
                Objects.equals(cellphoneRight.getImageUrl(), cellphoneLeft.getImageUrl()) &&
                Objects.equals(cellphoneRight.getBrand(), cellphoneLeft.getBrand()) &&
                Objects.equals(cellphoneRight.getStorage(), cellphoneLeft.getStorage()) &&
                Objects.equals(cellphoneRight.getRam(), cellphoneLeft.getRam()) &&
                Objects.equals(cellphoneRight.getOperatingSystem(), cellphoneLeft.getOperatingSystem()) &&
                Objects.equals(cellphoneRight.getFrontCameraResolution(), cellphoneLeft.getFrontCameraResolution()) &&
                Objects.equals(cellphoneRight.getMainCameraResolution(), cellphoneLeft.getMainCameraResolution()) &&
                Objects.equals(cellphoneRight.getScreenSize(), cellphoneLeft.getScreenSize()) &&
                Objects.equals(cellphoneRight.getStock(), cellphoneLeft.getStock()) &&
                categoryMatcher.matches(cellphoneLeft.getCategory());
    }
}
