package org.artemlebid.footballmanager.mappers;

import org.artemlebid.footballmanager.dtos.player.CreatePlayerDto;
import org.artemlebid.footballmanager.dtos.player.PlayerDto;
import org.artemlebid.footballmanager.entities.Player;
import org.artemlebid.footballmanager.entities.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = PlayerMapperHelper.class)
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "teamId", target = "team")
    Player createPlayerDtoToPlayer(CreatePlayerDto playerDto);
    @Mapping(source = "team", target = "teamId")
    PlayerDto playerToPlayerDto(Player player);

    default Long teamToTeamId(Team team){
        if(team == null) return null;

        return team.getId();
    }
}
