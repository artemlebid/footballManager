package org.artemlebid.footballmanager.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponseDto {
    private String error;
    private Integer status;
}
