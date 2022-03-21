package FootballBettingDatabase_6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity {

    private String prediction;

    @Column(nullable = false)
    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
