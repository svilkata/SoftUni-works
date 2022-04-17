package softuni.exam.models.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ImportFromJSONTownDTO {
    @Size(min = 2)
    private String townName;

    @Positive
    private int population;

    public ImportFromJSONTownDTO() {
    }

    public String getTownName() {
        return townName;
    }

    public int getPopulation() {
        return population;
    }
}
