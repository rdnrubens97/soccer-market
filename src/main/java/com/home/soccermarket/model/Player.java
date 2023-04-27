package com.home.soccermarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player {
    @Id
    private String cpf;
    private String name;
    private LocalDate birthDate;
    private String position;

}
