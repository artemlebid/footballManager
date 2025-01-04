package org.artemlebid.footballmanager.dtos.player;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.artemlebid.footballmanager.constants.Messages;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatePlayerDto {
    @NotEmpty(message = Messages.PLAYER_FIRST_NAME_NOT_EMPTY)
    private String firstName;

    @NotEmpty(message = Messages.PLAYER_LAST_NAME_NOT_EMPTY)
    private String lastName;

    @NotEmpty(message = Messages.PLAYER_POSITION_NOT_EMPTY)
    private String position;

    @NotNull(message = Messages.PLAYER_AGE_NOT_NULL)
    @Positive(message = Messages.PLAYER_MIN_AGE)
    private Integer age;

    @NotNull(message = Messages.PLAYER_EXPERIENCE_NOT_NULL)
    @Min(value = 0, message = Messages.PLAYER_MIN_EXPERIENCE)
    private Integer experience;

    private Long teamId;
}
