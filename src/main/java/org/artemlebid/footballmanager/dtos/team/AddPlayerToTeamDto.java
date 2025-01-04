package org.artemlebid.footballmanager.dtos.team;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.artemlebid.footballmanager.constants.Messages;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddPlayerToTeamDto {
    @NotNull(message = Messages.PLAYER_IDENTIFIERS_CANT_BE_NULL)
    private List<Long> playerIds;
}
