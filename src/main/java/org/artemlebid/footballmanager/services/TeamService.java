package org.artemlebid.footballmanager.services;

import org.artemlebid.footballmanager.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Optional<Team> getTeamById(Long id);
    List<Team> getAllTeams();
    Long saveOrUpdateTeam(Team team);
    void deleteTeam(Long id);
}
