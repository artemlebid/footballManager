package org.artemlebid.footballmanager.services;

import org.artemlebid.footballmanager.entities.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Optional<Player> getPlayerById(Long id);
    List<Player> getAllPlayers();
    Long saveOrUpdatePlayer(Player player);
    void deletePlayer(Long id);
}
