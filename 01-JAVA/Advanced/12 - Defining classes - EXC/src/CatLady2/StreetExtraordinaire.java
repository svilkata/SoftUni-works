package CatLady2;

public class StreetExtraordinaire {
    private String catType;
    private double decibelsOfMeows;

    public StreetExtraordinaire(String catType, double decibelsOfMeows) {
        this.catType = catType;
        this.decibelsOfMeows = decibelsOfMeows;
    }

    public StreetExtraordinaire() {
    }

    public String getCatType() {
        return catType;
    }

    public double getDecibelsOfMeows() {
        return decibelsOfMeows;
    }
}
