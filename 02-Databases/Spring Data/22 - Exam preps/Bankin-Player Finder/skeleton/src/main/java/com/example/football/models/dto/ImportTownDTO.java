package com.example.football.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportTownDTO {
    @Size(min = 2)
    private String name;

    @Positive
    @Min(value = 10000)
    private int population;

    @Size(min = 10)
    private String travelGuide;

    public ImportTownDTO() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }
}
