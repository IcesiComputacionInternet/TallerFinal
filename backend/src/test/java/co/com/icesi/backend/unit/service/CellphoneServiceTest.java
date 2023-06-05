package co.com.icesi.backend.unit.service;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.error.exception.CellphoneException;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.CellphoneMapper;
import co.com.icesi.backend.mapper.CellphoneMapperImpl;
import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.repository.CategoryRepository;
import co.com.icesi.backend.repository.CellphoneRepository;
import co.com.icesi.backend.service.CellphoneService;
import co.com.icesi.backend.unit.service.matcher.CellphoneMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CellphoneServiceTest {
    private CellphoneService cellphoneService;
    private CellphoneRepository cellphoneRepository;
    private CategoryRepository categoryRepository;
    private CellphoneMapper cellphoneMapper;
    private CellphoneShopExceptionBuilder exceptionBuilder;

    @BeforeEach
    private void init(){
        cellphoneRepository = mock(CellphoneRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        exceptionBuilder = spy(CellphoneShopExceptionBuilder.class);
        cellphoneMapper = spy(CellphoneMapperImpl.class);
        cellphoneService = new CellphoneService(cellphoneRepository, cellphoneMapper, categoryRepository, exceptionBuilder);
        cellphoneService = spy(cellphoneService);
    }

    @Test
    public void testCreateCellphone_HappyPath(){
        doNothing().when(cellphoneService).checkPermissions();
        when(categoryRepository.findByName(any())).thenReturn(Optional.ofNullable(defaultCategory()));
        cellphoneService.save(defaultRequestCellphoneDTO());
        Cellphone cellphone = defaultCellphone();

        verify(cellphoneRepository, times(1)).save(argThat(new CellphoneMatcher(cellphone)));
        verify(cellphoneMapper, times(1)).fromRequestCellphoneDTO(any());
        verify(cellphoneMapper, times(1)).fromCellphoneToResponseCellphoneDTO(any());
        verify(cellphoneRepository, times(1)).isNameInUse(any());
        verify(categoryRepository, times(1)).findByName(any());
    }

    @Test
    public void testCreateCellphoneWhenNameAlreadyExists(){
        doNothing().when(cellphoneService).checkPermissions();
        when(categoryRepository.findByName(any())).thenReturn(Optional.ofNullable(defaultCategory()));
        when(cellphoneRepository.isNameInUse(any())).thenReturn(true);

        try{
            cellphoneService.save(defaultRequestCellphoneDTO());
            fail();
        }catch (CellphoneException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            var detail = details.get(0);

            assertEquals(1, details.size());
            assertEquals("ERR_DUPLICATED", detail.getErrorCode(), "Code doesn't match");
            assertEquals("Samsung Max pro 24, already exists.", detail.getErrorMessage(), "Error message doesn't match");
            assertEquals("Another cellphone already has this name.", message);
        }
    }

    @Test
    public void testCreateCellphoneWhenCategoryDoesnotExists(){
        doNothing().when(cellphoneService).checkPermissions();
        when(cellphoneRepository.isNameInUse(any())).thenReturn(true);

        try{
            cellphoneService.save(defaultRequestCellphoneDTO());
            fail();
        }catch (CellphoneException exception){
            String message = exception.getMessage();
            var error = exception.getError();
            var details = error.getDetails();
            var detail = details.get(0);

            assertEquals(1, details.size());
            assertEquals("ERR_404", detail.getErrorCode(), "Code doesn't match");
            assertEquals("Not found HIGH_RANGE", detail.getErrorMessage(), "Error message doesn't match");
            assertEquals("The category with the specified name does not exists.", message);
        }
    }

    private Cellphone defaultCellphone(){
        return Cellphone.builder()
                .cellphoneId(UUID.randomUUID())
                .name("Samsung Max pro 24")
                .description("Simple description")
                .price(1234576L)
                .imageUrl("image.com")
                .brand("Samsung")
                .storage("125")
                .ram("16")
                .operatingSystem("Android")
                .frontCameraResolution("15mp")
                .mainCameraResolution("20mp")
                .screenSize("6.69inches")
                .stock(500)
                .category(defaultCategory())
                .build();
    }

    private RequestCellphoneDTO defaultRequestCellphoneDTO(){
        return RequestCellphoneDTO.builder()
                .name("Samsung Max pro 24")
                .description("Simple description")
                .price(1234576L)
                .imageUrl("image.com")
                .brand("Samsung")
                .storage("125")
                .ram("16")
                .operatingSystem("Android")
                .frontCameraResolution("15mp")
                .mainCameraResolution("20mp")
                .screenSize("6.69inches")
                .stock(500)
                .category(CategoryType.HIGH_RANGE.getCategory())
                .build();
    }

    private Category defaultCategory() {
        return Category.builder()
                .categoryId(UUID.randomUUID())
                .name(CategoryType.HIGH_RANGE.getCategory())
                .description("Loreno Insomnio, I never knew how to say it")
                .build();
    }
}
