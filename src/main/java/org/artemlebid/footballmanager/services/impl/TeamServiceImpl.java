package org.artemlebid.footballmanager.services.impl;

import jakarta.transaction.Transactional;
import org.artemlebid.footballmanager.entities.Team;
import org.artemlebid.footballmanager.repositories.TeamRepository;
import org.artemlebid.footballmanager.services.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    @Transactional
    public Long saveOrUpdateTeam(Team team) {
        Team newTeam = teamRepository.save(team);
        return newTeam.getId();
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
