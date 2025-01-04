package org.artemlebid.footballmanager.dtos.transfer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.artemlebid.footballmanager.constants.Messages;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferRequestDto {
    @NotNull(message = Messages.TRANSFER_PLAYER_ID_CANT_BE_NULL)
    private Long playerId;

    @NotNull(message = Messages.TRANSFER_FROM_TEAM_ID_CANT_BE_NULL)
    private Long fromTeamId;

    @NotNull(message = Messages.TRANSFER_TO_TEAM_ID_CANT_BE_NULL)
    private Long toTeamId;
}
