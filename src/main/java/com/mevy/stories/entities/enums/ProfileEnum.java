package com.mevy.stories.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProfileEnum {
    ADMIN(1, "ADMIN");

    int code;
    String description;

}