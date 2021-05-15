package CarSalesman;

public class Engine {
    private String modelEngine;
    private int powerEngine;
    private String displacementEngine;
    private String efficiencyEngine;

    public String getModelEngine() {
        return modelEngine;
    }

    public int getPowerEngine() {
        return powerEngine;
    }

    public String getDisplacementEngine() {
        return displacementEngine;
    }

    public String getEfficiencyEngine() {
        return efficiencyEngine;
    }

    public Engine(String modelEngine, int powerEngine) {
        this(modelEngine, powerEngine, "n/a", "n/a");
    }

    public Engine EngineDisplacement(String modelEngine, int powerEngine, String displacementEngine) {
        return new Engine(modelEngine, powerEngine, displacementEngine, "n/a");
    }

    public Engine EngineEfficiency(String modelEngine, int powerEngine, String efficiencyEngine) {
        return new Engine(modelEngine, powerEngine, "n/a", efficiencyEngine);
    }

    public Engine(String modelEngine, int powerEngine, String displacementEngine, String efficiencyEngine) {
        this.modelEngine = modelEngine;
        this.powerEngine = powerEngine;
        this.displacementEngine = displacementEngine;
        this.efficiencyEngine = efficiencyEngine;
    }

    @Override
    public String toString() {
        return String.format("%s:%nPower: %d%nDisplacement: %s%nEfficiency: %s", this.modelEngine, this.powerEngine,
                this.displacementEngine, this.efficiencyEngine);
    }
}
