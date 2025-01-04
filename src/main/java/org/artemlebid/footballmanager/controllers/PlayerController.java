package org.artemlebid.footballmanager.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.artemlebid.footballmanager.dtos.player.CreatePlayerDto;
import org.artemlebid.footballmanager.dtos.player.PlayerDto;
import org.artemlebid.footballmanager.dtos.player.UpdatePlayerDto;
import org.artemlebid.footballmanager.dtos.responses.SuccessResponseDto;
import org.artemlebid.footballmanager.entities.Player;
import org.artemlebid.footballmanager.entities.Team;
import org.artemlebid.footballmanager.constants.Messages;
import org.artemlebid.footballmanager.mappers.PlayerMapper;
import org.artemlebid.footballmanager.services.PlayerService;
import org.artemlebid.footballmanager.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.artemlebid.footballmanager.controllers.PlayerController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class PlayerController {
    public static final String BASE_URL = "/api/players";
    public static final String PLAYER_URL = "/{playerId}";

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper, TeamService teamService) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player : players) {
            playerDtos.add(playerMapper.playerToPlayerDto(player));
        }

        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDto> savePlayer(@RequestBody @Valid CreatePlayerDto playerDto) {
        Player player = playerMapper.createPlayerDtoToPlayer(playerDto);
        Long playerId = playerService.saveOrUpdatePlayer(player);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.PLAYER_SAVED.formatted(playerId));
        responseDto.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping(PLAYER_URL)
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("playerId") Long playerId) {
        Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.PLAYER_NOT_FOUND.formatted(playerId)));
        PlayerDto playerDto = playerMapper.playerToPlayerDto(player);

        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @PatchMapping(PLAYER_URL)
    public ResponseEntity<SuccessResponseDto> updatePlayer(
            @PathVariable Long playerId,
            @RequestBody @Valid UpdatePlayerDto playerDto) {
        Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.PLAYER_NOT_FOUND.formatted(playerId)));

        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setPosition(playerDto.getPosition());
        player.setAge(playerDto.getAge());
        player.setExperience(playerDto.getExperience());

        Team team = teamService.getTeamById(playerDto.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(playerDto.getTeamId())));
        player.setTeam(team);

        playerService.saveOrUpdatePlayer(player);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.PLAYER_UPDATED.formatted(playerId));
        responseDto.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(PLAYER_URL)
    public ResponseEntity<SuccessResponseDto> deletePlayer(@PathVariable Long playerId) {
        Player player = playerService.getPlayerById(playerId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.PLAYER_NOT_FOUND.formatted(playerId)));

        playerService.deletePlayer(playerId);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.PLAYER_DELETED.formatted(playerId));
        responseDto.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
