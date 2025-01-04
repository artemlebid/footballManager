package org.artemlebid.footballmanager.mappers;

import org.artemlebid.footballmanager.dtos.team.CreateTeamDto;
import org.artemlebid.footballmanager.dtos.team.TeamDto;
import org.artemlebid.footballmanager.dtos.team.UpdateTeamDto;
import org.artemlebid.footballmanager.entities.Player;
import org.artemlebid.footballmanager.entities.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team createTeamDtoToTeam(CreateTeamDto teamDto);
    TeamDto teamToTeamDto(Team team);
}
