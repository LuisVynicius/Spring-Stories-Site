package com.mevy.metales_backend.exceptions.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
    
    private String text;

    public String toJson() {
        return String.format("{ \"text\": \"%s\" }", text);
    }
}
