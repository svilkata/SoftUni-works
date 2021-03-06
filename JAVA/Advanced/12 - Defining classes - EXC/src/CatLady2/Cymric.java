package CatLady2;

public class Cymric {
    private String catType;
    private double furLength;

    public Cymric(String catType, double furLength) {
        this.catType = catType;
        this.furLength = furLength;
    }

    public String getCatType() {
        return catType;
    }

    public double getFurLength() {
        return furLength;
    }
}
