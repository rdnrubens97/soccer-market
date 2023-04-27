package com.home.soccermarket.response;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;

@Component
@Data
public class PlayerResponse {
    private String cpf;
    private String name;
    private LocalDate birthDate;
    private String position;

    public Integer getAge(){
        LocalDate today = LocalDate.now();
        Period age = Period.between(this.birthDate, today);
        Integer ageInYears = age.getYears();
        return ageInYears;
    }

    @Override
    public String toString(){
        return "\nName: " + this.name +
                "\nPosition: " + this.position +
                "\nAge: " + getAge() +
                "\n";
    }

}
