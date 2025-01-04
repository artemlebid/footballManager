package org.artemlebid.footballmanager.mappers;

import jakarta.persistence.EntityNotFoundException;
import org.artemlebid.footballmanager.constants.Messages;
import org.artemlebid.footballmanager.entities.Team;
import org.artemlebid.footballmanager.repositories.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapperHelper {
    private final TeamRepository teamRepository;

    public PlayerMapperHelper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team mapTeamIdToTeam(Long teamId) {
        return teamId != null ? teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId))) : null;
    }
}
