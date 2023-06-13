package com.example.travel.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServicesDTO {
    private String startedTime;
    private String endedTime;
    private int numberOfPeople;
    private String promotion;
}
