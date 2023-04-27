package com.home.soccermarket.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UpdatePlayer {
    @NotBlank
    private String name;
    @NotBlank
    private LocalDate birthDate;
    @NotBlank
    private String position;

}
