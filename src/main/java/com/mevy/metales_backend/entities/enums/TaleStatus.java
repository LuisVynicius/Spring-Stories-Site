package com.mevy.metales_backend.entities.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaleStatus {
    FINALIZED(1, "Finalizado"),
    ONGOING(2, "Lançando"),
    HIATUS(3, "Hiato"),
    CANCELLED(4, "Cancelado")
    ;

    private final Integer code;
    private final String description;

    public static TaleStatus toEnum(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }

        for (TaleStatus status : TaleStatus.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + code);
    }
}
