package org.artemlebid.footballmanager.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.artemlebid.footballmanager.dtos.player.PlayerDto;
import org.artemlebid.footballmanager.dtos.team.AddPlayerToTeamDto;
import org.artemlebid.footballmanager.dtos.team.UpdatePlayerInTeamDto;
import org.artemlebid.footballmanager.dtos.team.CreateTeamDto;
import org.artemlebid.footballmanager.dtos.responses.SuccessResponseDto;
import org.artemlebid.footballmanager.dtos.team.TeamDto;
import org.artemlebid.footballmanager.dtos.team.UpdateTeamDto;
import org.artemlebid.footballmanager.entities.Player;
import org.artemlebid.footballmanager.entities.Team;
import org.artemlebid.footballmanager.constants.Messages;
import org.artemlebid.footballmanager.exceptions.NotAllowedOperation;
import org.artemlebid.footballmanager.mappers.PlayerMapper;
import org.artemlebid.footballmanager.mappers.TeamMapper;
import org.artemlebid.footballmanager.services.PlayerService;
import org.artemlebid.footballmanager.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.artemlebid.footballmanager.controllers.TeamController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class TeamController {
    public static final String BASE_URL = "/api/teams";
    public static final String TEAM_URL = "/{teamId}";
    public static final String TEAM_PLAYERS_URL = "/{teamId}/players";

    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public TeamController(TeamService teamService, TeamMapper teamMapper, PlayerService playerService, PlayerMapper playerMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        List<TeamDto> teamDtos = new ArrayList<>();
        for (Team team : teams) {
            teamDtos.add(teamMapper.teamToTeamDto(team));
        }

        return new ResponseEntity<>(teamDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDto> createTeam(@RequestBody @Valid CreateTeamDto teamDto) {
        Team team = teamMapper.createTeamDtoToTeam(teamDto);
        Long teamId = teamService.saveOrUpdateTeam(team);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.TEAM_SAVED.formatted(teamId));
        responseDto.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping(TEAM_URL)
    public ResponseEntity<TeamDto> getTeamById(@PathVariable Long teamId) {
        Team team = teamService.getTeamById(teamId).orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId)));
        TeamDto teamDto = teamMapper.teamToTeamDto(team);

        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @PatchMapping(TEAM_URL)
    public ResponseEntity<SuccessResponseDto> updateTeam(
            @PathVariable Long teamId,
            @RequestBody @Valid UpdateTeamDto teamDto){
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId)));

        team.setId(teamId);
        team.setName(teamDto.getName());
        team.setCountry(teamDto.getCountry());
        team.setBalance(teamDto.getBalance());
        team.setCommissionRate(teamDto.getCommissionRate());

        teamService.saveOrUpdateTeam(team);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.TEAM_UPDATED.formatted(teamId));
        responseDto.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(TEAM_URL)
    public ResponseEntity<SuccessResponseDto> deleteTeam(@PathVariable(name = "teamId") Long teamId) {
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId)));

        team.getPlayers().forEach(player -> {
            player.setTeam(null);
            playerService.saveOrUpdatePlayer(player);
        });

        teamService.deleteTeam(teamId);

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.TEAM_DELETED.formatted(teamId));
        responseDto.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(TEAM_PLAYERS_URL)
    public ResponseEntity<List<PlayerDto>> getAllPlayersByTeam(@PathVariable Long teamId) {
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId)));
        List<PlayerDto> playerDtos = team.getPlayers()
                .stream().map(playerMapper::playerToPlayerDto).toList();

        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @PostMapping(TEAM_PLAYERS_URL)
    public ResponseEntity<SuccessResponseDto> addPlayersToTeam(
            @PathVariable Long teamId,
            @RequestBody @Valid AddPlayerToTeamDto playersDto) {
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId)));

        List<Long> newPlayersIds = playersDto.getPlayerIds();

        for (Long playerId : newPlayersIds) {
            Player newPlayer = playerService.getPlayerById(playerId)
                    .orElseThrow(() -> new EntityNotFoundException(Messages.PLAYER_NOT_FOUND.formatted(playerId)));

            if(newPlayer.getTeam() == team) continue;

            if(newPlayer.getTeam() != null) {
                throw new NotAllowedOperation(Messages.PLAYER_ALREADY_EXIST.formatted(newPlayer.getId()));
            }

            newPlayer.setTeam(team);
            playerService.saveOrUpdatePlayer(newPlayer);
        }


        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.NEW_PLAYERS_SAVED.formatted(teamId));
        responseDto.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(TEAM_PLAYERS_URL)
    public ResponseEntity<SuccessResponseDto> updatePlayersToTeam(
            @PathVariable Long teamId,
            @RequestBody @Valid UpdatePlayerInTeamDto playersDto) {
        Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new EntityNotFoundException(Messages.TEAM_NOT_FOUND.formatted(teamId)));

        List<Player> oldPlayers = team.getPlayers();
        oldPlayers.forEach(item -> item.setTeam(null));
        oldPlayers.forEach(playerService::saveOrUpdatePlayer);

        List<Long> newPlayersIds = playersDto.getPlayerIds();

        for (Long playerId : newPlayersIds) {
            Player newPlayer = playerService.getPlayerById(playerId)
                    .orElseThrow(() -> new EntityNotFoundException(Messages.PLAYER_NOT_FOUND.formatted(playerId)));

            if(newPlayer.getTeam() != team && newPlayer.getTeam() != null) {
                throw new NotAllowedOperation(Messages.PLAYER_ALREADY_EXIST.formatted(newPlayer.getId()));
            }

            newPlayer.setTeam(team);
            playerService.saveOrUpdatePlayer(newPlayer);
        }

        SuccessResponseDto responseDto = new SuccessResponseDto();
        responseDto.setMessage(Messages.NEW_PLAYERS_UPDATED.formatted(teamId));
        responseDto.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
