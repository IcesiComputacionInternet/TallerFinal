package co.com.icesi.backend.error.util;

import co.com.icesi.backend.Enum.ErrorCode;
import co.com.icesi.backend.error.exception.CellphoneError;
import co.com.icesi.backend.error.exception.CellphoneErrorDetail;
import co.com.icesi.backend.error.exception.CellphoneException;
import co.com.icesi.backend.error.exception.DetailBuilder;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class CellphoneShopExceptionBuilder {
    public CellphoneException noPermissionException(String message){
        CellphoneError error = createCellphoneError(HttpStatus.UNAUTHORIZED, new DetailBuilder(ErrorCode.ERROR_401,
                ErrorCode.ERROR_401.getMessage()));
        return new CellphoneException(message, error);
    }

    public CellphoneException notFoundException(String message, String field){
        CellphoneError error = createCellphoneError(HttpStatus.NOT_FOUND, new DetailBuilder(ErrorCode.ERROR_404,
                ErrorCode.ERROR_404.getMessage().formatted(field)));
        return new CellphoneException(message, error);
    }

    public CellphoneException badRequestException(String message, String field){
        CellphoneError error = createCellphoneError(HttpStatus.BAD_REQUEST, new DetailBuilder(ErrorCode.ERROR_400,
                ErrorCode.ERROR_400.getMessage().formatted(field)));
        return new CellphoneException(message, error);
    }

    public CellphoneException duplicatedValueException(String message, String field){
        CellphoneError error = createCellphoneError(HttpStatus.CONFLICT, new DetailBuilder(ErrorCode.ERR_DUPLICATED,
                String.format(ErrorCode.ERR_DUPLICATED.getMessage(),field)));
        return new CellphoneException(message, error);
    }

    public static CellphoneError createCellphoneError(HttpStatus httpStatus, DetailBuilder... details) {
        return CellphoneError.builder()
                .status(httpStatus)
                .details(
                        Arrays.stream(details)
                                .map(CellphoneShopExceptionBuilder::mapToCellphoneErrorDetail)
                                .toList()
                )
                .build();
    }

    private static CellphoneErrorDetail mapToCellphoneErrorDetail(DetailBuilder detailBuilder) {
        return CellphoneErrorDetail.builder()
                .errorCode(detailBuilder.getErrorCode().getCode())
                .errorMessage(detailBuilder.getErrorCode().getMessage().formatted(detailBuilder.getFields()))
                .build();
    }
}
