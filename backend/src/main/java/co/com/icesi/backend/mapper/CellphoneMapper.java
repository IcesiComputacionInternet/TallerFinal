package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.response.ResponseCellphoneDTO;
import co.com.icesi.backend.model.Cellphone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CellphoneMapper {
    @Mapping(target = "category", source = "category",ignore=true)
    Cellphone fromRequestCellphoneDTO(RequestCellphoneDTO requestCellphoneDTO);
    @Mapping(target = "category", source = "category",ignore=true)
    ResponseCellphoneDTO fromCellphoneToResponseCellphoneDTO(Cellphone cellphone);
}
