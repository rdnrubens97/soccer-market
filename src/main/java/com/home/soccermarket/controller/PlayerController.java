package com.home.soccermarket.controller;

import com.home.soccermarket.exception.CustomException;
import com.home.soccermarket.request.PlayerRequest;
import com.home.soccermarket.request.UpdatePlayer;
import com.home.soccermarket.response.PlayerResponse;
import com.home.soccermarket.service.PlayerService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping(value = "/add-player-team/{id}")
    public ResponseEntity<String> addPlayer(@PathVariable Long id, @RequestBody PlayerRequest playerRequest) throws CustomException {
        return ResponseEntity.ok().body(playerService.addPlayer(id, playerRequest));
    }

    @PutMapping(value = "/edit-player/document-{cpf}")
    public ResponseEntity<PlayerResponse> editPlayer(@PathVariable String cpf, @RequestBody UpdatePlayer updatePlayer) throws CustomException {
        return ResponseEntity.ok().body(playerService.editPlayer(cpf, updatePlayer));
    }

    @GetMapping(value = "/get-player/document-{cpf}")
    public ResponseEntity<PlayerResponse> getPlayerByCpf(@PathVariable String cpf) throws CustomException {
        return ResponseEntity.ok().body(playerService.getPlayerByCpf(cpf));
    }

    @DeleteMapping(value = "/delete-player/document-{cpf}")
    public ResponseEntity<String> deletePlayer(@PathVariable String cpf) throws CustomException {
        return ResponseEntity.ok().body(playerService.deletePlayer(cpf));
    }

    @PostMapping(value = "/transfer-player/document-{cpf}/to-team{teamCode}")
    public ResponseEntity<String> transferPlayer(@PathVariable String cpf, @PathVariable Long teamCode) throws CustomException {
        return ResponseEntity.ok().body(playerService.transferPlayer(cpf, teamCode));
    }




}
