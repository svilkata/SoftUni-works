package enums;

public enum BakingTechniques {
    CRISPY(0.9),
    CHEWY(1.1),
    HOMEMADE(1.0);

    private double modifier;

    BakingTechniques(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return this.modifier;
    }
}
