package com.home.soccermarket.mapper;

import com.home.soccermarket.model.Player;
import com.home.soccermarket.request.PlayerRequest;
import com.home.soccermarket.response.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerMapper {

    Player toEntity(PlayerRequest playerRequest);
    PlayerResponse toResponse(Player player);
    List<Player> toEntityList(List<PlayerRequest> playerRequestList);
    List<PlayerResponse> toResponseList(List<Player> playerList);

}