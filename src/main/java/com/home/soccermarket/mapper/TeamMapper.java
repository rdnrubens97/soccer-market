package com.home.soccermarket.mapper;

import com.home.soccermarket.model.Team;
import com.home.soccermarket.request.TeamRequest;
import com.home.soccermarket.response.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", uses = PlayerMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper {

    Team toEntity(TeamRequest teamRequest);
    TeamResponse toResponse(Team team);
    List<Team> toEntityList(List<TeamRequest> teamRequestList);
    List<TeamResponse> toResponseList(List<Team> teamList);

}