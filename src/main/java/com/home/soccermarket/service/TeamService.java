package com.home.soccermarket.service;

import com.home.soccermarket.exception.CustomException;
import com.home.soccermarket.mapper.TeamMapper;
import com.home.soccermarket.model.Team;
import com.home.soccermarket.repository.TeamRepository;
import com.home.soccermarket.request.TeamRequest;
import com.home.soccermarket.response.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamMapper mapper;
    @Autowired
    private TeamRepository teamRepository;


    public String createTeam(TeamRequest teamRequest) throws CustomException {
        Team teamEntity = mapper.toEntity(teamRequest);
        if (verifyExistTeamByName(teamEntity.getName()) == false && verifyExistTeamByStadium(teamEntity.getStadium()) == false){
            teamRepository.save(teamEntity);
            return "Successfully team add!" +
                    "\nName: " + teamRequest.getName();
        }
        else{
            throw new CustomException("Something went wrong");
        }
    }

    public String editTeam(Long id, TeamRequest teamRequest) throws CustomException {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()){
            Team team = optionalTeam.get();
            team.setName(teamRequest.getName());
            team.setState(teamRequest.getState());
            team.setStadium(teamRequest.getStadium());
            teamRepository.save(team);
            return "The team has been successfully changed";
        }
        else{
            throw new CustomException("Team not found");
        }
    }

    public TeamResponse findTeamByName(String name) {
        Optional<Team> optionalTeam = teamRepository.findByName(name);
        if (optionalTeam.isPresent()) {
            TeamResponse teamResponse = mapper.toResponse(optionalTeam.get());
            return teamResponse;
        } else {
            throw new RuntimeException("Team not found");
        }
    }

    public String listAll() {
        List<TeamResponse> teams = mapper.toResponseList(teamRepository.findAll().stream().toList());
        String teamResponses = teams.toString();
        return teamResponses;
    }

    public String deleteTeam(Long id) throws CustomException {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()){
            teamRepository.deleteById(id);
            return "The team has been successfully deleted";
        }
        else{
            throw new CustomException("Team not found");
        }
    }



    private Boolean verifyExistTeamByName(String name){
        List<Team> teamList = teamRepository.findAll().stream().toList();
        Optional<Team> optionalTeam = teamList.stream().filter(e -> e.getName().equals(name)).findFirst();
        if (optionalTeam.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }

    private Boolean verifyExistTeamByStadium(String name){
        List<Team> teamList = teamRepository.findAll().stream().toList();
        Optional<Team> optionalTeam = teamList.stream().filter(e -> e.getStadium().equals(name)).findFirst();
        if (optionalTeam.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }

    private Team findTeamByStadium(String name){
        List<Team> teamList = teamRepository.findAll().stream().toList();
        Optional<Team> optionalTeam = teamList.stream().filter(e -> e.getStadium().equals(name)).findFirst();
        if (optionalTeam.isPresent()){
            return optionalTeam.get();
        }
        else{
            throw new RuntimeException("Team not found");
        }
    }

}
