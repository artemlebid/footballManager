package org.artemlebid.footballmanager.services.impl;

import jakarta.transaction.Transactional;
import org.artemlebid.footballmanager.entities.Player;
import org.artemlebid.footballmanager.repositories.PlayerRepository;
import org.artemlebid.footballmanager.services.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    @Transactional
    public Long saveOrUpdatePlayer(Player player) {
       Player newPlayer = playerRepository.saveAndFlush(player);
       return newPlayer.getId();
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
