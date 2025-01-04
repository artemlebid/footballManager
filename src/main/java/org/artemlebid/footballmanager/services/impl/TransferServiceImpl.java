package org.artemlebid.footballmanager.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.artemlebid.footballmanager.constants.Messages;
import org.artemlebid.footballmanager.dtos.transfer.TransferRequestDto;
import org.artemlebid.footballmanager.entities.Player;
import org.artemlebid.footballmanager.entities.Team;
import org.artemlebid.footballmanager.exceptions.NotAllowedOperation;
import org.artemlebid.footballmanager.services.PlayerService;
import org.artemlebid.footballmanager.services.TeamService;
import org.artemlebid.footballmanager.services.TransferService;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {
    private final TeamService teamService;
    private final PlayerService playerService;

    public TransferServiceImpl(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    @Transactional
    public void transfer(TransferRequestDto transferRequestDto) {
        Player player = playerService.getPlayerById(transferRequestDto.getPlayerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        Messages.PLAYER_NOT_FOUND.formatted(transferRequestDto.getPlayerId())
                ));

        Team fromTeam = teamService.getTeamById(transferRequestDto.getFromTeamId())
                .orElseThrow(() -> new EntityNotFoundException(
                        Messages.TEAM_NOT_FOUND.formatted(transferRequestDto.getFromTeamId())
                ));

        Team toTeam = teamService.getTeamById(transferRequestDto.getToTeamId())
                .orElseThrow(() -> new EntityNotFoundException(
                        Messages.TEAM_NOT_FOUND.formatted(transferRequestDto.getToTeamId())
                ));

        if(player.getTeam() == null) {
            throw new NotAllowedOperation(Messages.NOT_ALLOWED_TRANSFER_1);
        }

        if(!fromTeam.getPlayers().contains(player)) {
            throw new NotAllowedOperation(Messages.NOT_ALLOWED_TRANSFER_2);
        }

        double totalCost = getTotalCost(fromTeam, player, toTeam);

        toTeam.setBalance(toTeam.getBalance() - totalCost);
        fromTeam.setBalance(fromTeam.getBalance() + totalCost);

        fromTeam.getPlayers().remove(player);
        toTeam.getPlayers().add(player);
        player.setTeam(toTeam);

        teamService.saveOrUpdateTeam(toTeam);
        teamService.saveOrUpdateTeam(fromTeam);
        playerService.saveOrUpdatePlayer(player);
    }

    private static double getTotalCost(Team fromTeam, Player player, Team toTeam) {
        double transferCost = (player.getExperience() * 100000.0) / player.getAge();
        double commission = transferCost * (fromTeam.getCommissionRate() / 100.0);
        double totalCost = transferCost + commission;

        if (toTeam.getBalance() < totalCost) {
            throw new NotAllowedOperation(Messages.NOT_ALLOWED_TRANSFER_3.formatted(totalCost));
        }
        return totalCost;
    }
}
