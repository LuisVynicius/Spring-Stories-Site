package com.mevy.stories.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProfileEnum {
    ADMIN(1, "ADMIN");

    Integer code;
    String description;

    public static ProfileEnum valueOf(Integer code) {
        for (ProfileEnum profile : ProfileEnum.values()) {
            if (code.equals(profile.code)) {
                return profile;
            }
        }
        throw new IllegalArgumentException("Valor de ProfileEnum inválido: " + code);
    }

}