package com.example.travel.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HotelResponse {
    private String name;
    private String address;
    private String phoneNumber;

    @Length(max = 100000)
    private String description;

    private double avg;
}
