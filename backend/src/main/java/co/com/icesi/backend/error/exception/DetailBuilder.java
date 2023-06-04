package co.com.icesi.backend.error.exception;

import co.com.icesi.backend.Enum.ErrorCode;
import lombok.Getter;

@Getter
public class DetailBuilder {
    private final ErrorCode errorCode;
    private final Object[] fields;

    public DetailBuilder(ErrorCode errorCode, Object... fields){
        this.errorCode = errorCode;
        this.fields = fields;
    }
}
