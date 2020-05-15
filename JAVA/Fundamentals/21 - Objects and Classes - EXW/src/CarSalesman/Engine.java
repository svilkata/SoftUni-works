package CarSalesman;

public class Engine {
//    model, power, displacement and efficiency
    private String modelEngine;
    private String powerEngine;
    private String displacementEngine;
    private String efficiencyEngine;

    public Engine(String modelEngine, String powerEngine) {
        this.modelEngine = modelEngine;
        this.powerEngine = powerEngine;
        this.displacementEngine = "n/a";
        this.efficiencyEngine = "n/a";
    }

    public void setDisplacementEngine(String displacementEngine) {
        this.displacementEngine = displacementEngine;
    }

    public void setEfficiencyEngine(String efficiencyEngine) {
        this.efficiencyEngine = efficiencyEngine;
    }

    @Override
    public String toString() {
        String result = String.format("%s:%nPower: %s%nDisplacement: %s%nEfficiency: %s", this.modelEngine, this.powerEngine, this.displacementEngine, this.efficiencyEngine);
        return result;
    }

    public String getModelEngine() {
        return modelEngine;
    }


}
