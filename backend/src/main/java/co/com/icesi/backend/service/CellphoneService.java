package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.CategoryDTO;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.CellphoneMapper;
import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.repository.CategoryRepository;
import co.com.icesi.backend.repository.CellphoneRepository;
import co.com.icesi.backend.security.CellphoneSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CellphoneService {
    private final CellphoneRepository cellphoneRepository;
    private final CellphoneMapper cellphoneMapper;
    private final CategoryRepository categoryRepository;
    private final CellphoneShopExceptionBuilder exceptionBuilder;

    public ResponseCellphoneDTO save(RequestCellphoneDTO cellphoneDTO){
        checkPermissions();
        Category category = categoryRepository.findByName(cellphoneDTO.getCategory())
                .orElseThrow(() -> exceptionBuilder.notFoundException("The category with the specified name does not exists.",
                        cellphoneDTO.getCategory()));

        if(cellphoneRepository.isNameInUse(cellphoneDTO.getName())){
            throw exceptionBuilder.duplicatedValueException(
                    "Another cellphone already has this name.", cellphoneDTO.getName());
        }

        Cellphone cellphone = cellphoneMapper.fromRequestCellphoneDTO(cellphoneDTO);
        cellphone.setCellphoneId(UUID.randomUUID());
        cellphone.setCategory(category);
        cellphoneRepository.save(cellphone);
        return cellphoneMapper.fromCellphoneToResponseCellphoneDTO(cellphone);
    }

    public ResponseCellphoneDTO getCellphone(UUID cellphoneId){
        return cellphoneMapper.fromCellphoneToResponseCellphoneDTO(
                cellphoneRepository.findById(cellphoneId)
                        .orElseThrow(() -> exceptionBuilder.notFoundException(
                                "The category with the specified name does not exists.", cellphoneId.toString()))
        );
    }

    public void checkPermissions() {
        String role = CellphoneSecurityContext.getCurrentUserRole();
        if(role.equals(UserRole.USER)){
            throw exceptionBuilder.noPermissionException(
                    "Only an ADMIN user can create new roles and visualize them."
            );
        }
    }

    public List<ResponseCellphoneDTO> getAllCellphones(){
        return cellphoneRepository.findAll()
                .stream()
                .map(cellphoneMapper::fromCellphoneToResponseCellphoneDTO)
                .collect(Collectors.toList());
    }
}
