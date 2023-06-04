package co.com.icesi.backend.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    EN_PROCESO("EN_PROCESO"),
    ENVIADO("ENVIADO"),
    ENTREGADO("ENTREGADO");

    private final String status;
}
