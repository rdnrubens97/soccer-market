package com.home.soccermarket.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
public class TeamResponse {
    private String name;
    private String state;
    private String stadium;
    private List<PlayerResponse> players;

    @Override
    public String toString(){
        return "\n" +
                "\n" +
                "Team name: " + this.name +
                "\nTeam state: " + this.state +
                "\nTeam stadium: " + this.stadium +
                "\nPlayers: " +
                "\n" + this.players;
    }

}
