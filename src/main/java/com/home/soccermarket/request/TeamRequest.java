package com.home.soccermarket.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
public class TeamRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String state;
    @NotBlank
    private String stadium;
    private List<PlayerRequest> players;


}
