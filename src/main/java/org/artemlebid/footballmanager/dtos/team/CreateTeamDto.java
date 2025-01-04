package org.artemlebid.footballmanager.dtos.team;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.artemlebid.footballmanager.constants.Messages;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTeamDto {
    @NotEmpty(message = Messages.TEAM_NAME_NOT_EMPTY)
    private String name;

    @NotEmpty(message = Messages.TEAM_COUNTRY_NOT_EMPTY)
    private String country;

    @NotNull(message = Messages.TEAM_BALANCES_NOT_EMPTY)
    @Min(value = 0, message = Messages.TEAM_MIN_BALANCE)
    private Double balance;

    @NotNull(message = Messages.TEAM_COMMISSIONS_NOT_EMPTY)
    @Min(value = 0, message = Messages.TEAM_COMMISSION_VALUE)
    @Max(value = 10, message = Messages.TEAM_COMMISSION_VALUE)
    private Integer commissionRate;
}
