package org.artemlebid.footballmanager.dtos.player;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer age;
    private Integer experience;
    private Long teamId;
}
