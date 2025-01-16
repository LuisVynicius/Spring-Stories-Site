package com.mevy.stories.resources.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse {
    
    private Integer status;
    private String message;
    private Instant timestamp;

    public String toJson() {
        return String.format(
            "{\"status\": %s, \"message\": %s, \"timestamp\": %s}",
            status,
            message,
            timestamp.toString()
        );
    }

}
