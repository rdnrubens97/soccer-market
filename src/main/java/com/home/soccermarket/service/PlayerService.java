package com.home.soccermarket.service;

import com.home.soccermarket.exception.CustomException;
import com.home.soccermarket.mapper.PlayerMapper;
import com.home.soccermarket.model.Player;
import com.home.soccermarket.model.Team;
import com.home.soccermarket.repository.PlayerRepository;
import com.home.soccermarket.repository.TeamRepository;
import com.home.soccermarket.request.PlayerRequest;
import com.home.soccermarket.request.UpdatePlayer;
import com.home.soccermarket.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerMapper mapper;


    public String addPlayer(Long id, PlayerRequest playerRequest) throws CustomException {
        Optional<Player> optionalPlayer = playerRepository.findByCpf(playerRequest.getCpf());
        if (optionalPlayer.isEmpty()) {
            Optional<Team> optionalTeam = teamRepository.findById(id);
            if (optionalTeam.isPresent()) {
                Team team = optionalTeam.get();
                Player player = mapper.toEntity(playerRequest);
                team.getPlayers().add(player);
                teamRepository.save(team);
                playerRepository.save(player);
                return "Player add successfully";
            } else {
                throw new CustomException("Something went wrong");
            }
        }
        else{
            throw new CustomException("The player already registered");
        }
    }

    public PlayerResponse editPlayer(String cpf, UpdatePlayer updatePlayer) throws CustomException {
        Optional<Player> playerOptional = playerRepository.findByCpf(cpf);
        if (playerOptional.isPresent()){
            Player player = playerOptional.get();
            updateAttributes(updatePlayer, player);
            playerRepository.save(player);
            PlayerResponse playerResponse = mapper.toResponse(player);
            return playerResponse;
        }
        else{
            throw new CustomException("Something went wrong");
        }
    }

    public PlayerResponse getPlayerByCpf(String cpf) throws CustomException {
        Optional<Player> playerOptional = playerRepository.findByCpf(cpf);
        if (playerOptional.isPresent()){
            PlayerResponse playerResponse = mapper.toResponse(playerOptional.get());
            return playerResponse;
        }
        else{
            throw new CustomException("Player not found");
        }
    }

    public String deletePlayer(String cpf) throws CustomException {
        Player player = playerRepository.findByCpf(cpf).orElseThrow(() -> new CustomException("Player not found"));
        playerRepository.delete(player);
        return "Successfully deleted player";
    }

    public String transferPlayer(String cpf, Long teamCode) throws CustomException{
        Player player = playerRepository.findByCpf(cpf).orElseThrow(() -> new CustomException("Player not found"));
        Team team = teamRepository.findById(teamCode).orElseThrow(() -> new CustomException("Team not found"));
        team.getPlayers().add(player);
        teamRepository.save(team);
        return "Successful transfer";
    }

/*    public String deletePlayer(String cpf) throws CustomException {
        Optional<Player> playerOptional = playerRepository.findByCpf(cpf);
        if (playerOptional.isPresent()){
            playerRepository.delete(playerOptional.get());
            return "Successfully deleted player";
        }
        else{
            throw new CustomException("Player not found");
        }
    }*/

    private void updateAttributes(UpdatePlayer updatePlayer, Player playerToUpdate){
        playerToUpdate.setName(updatePlayer.getName());
        playerToUpdate.setBirthDate(updatePlayer.getBirthDate());
        playerToUpdate.setPosition(updatePlayer.getPosition());
    }
}
