package com.home.soccermarket.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class PlayerRequest {
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    private String name;
    @NotBlank
    private LocalDate birthDate;
    @NotBlank
    private String position;

}
