package com.home.soccermarket.controller;

import com.home.soccermarket.exception.CustomException;
import com.home.soccermarket.request.TeamRequest;
import com.home.soccermarket.response.TeamResponse;
import com.home.soccermarket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createTeam(@RequestBody TeamRequest teamRequest) throws CustomException {
        return ResponseEntity.ok().body(teamService.createTeam(teamRequest));
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<String> editTeam(@PathVariable Long id, @RequestBody TeamRequest teamRequest) throws CustomException {
        return ResponseEntity.ok().body(teamService.editTeam(id, teamRequest));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<TeamResponse> findTeamByName(@PathVariable String name){
        return ResponseEntity.ok().body(teamService.findTeamByName(name));
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<String> listAll(){
        return ResponseEntity.ok().body(teamService.listAll());
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok().body(teamService.deleteTeam(id));
    }




}
