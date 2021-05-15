package CatLady2;

public class Cat {
    private String name;
    private String type;
    private Siam siam;
    private Cymric cymric;
    private StreetExtraordinaire streetExtraordinaire;

    public Cat(String name, String type) {
        this.name = name;
        this.type = type;
//        this.siam = new Siam();
//        this.cymric = new Cymric();
//        this.streetExtraordinaire = new StreetExtraordinaire();
    }

    public void setSiam(Siam siam) {
        this.siam = siam;
    }

    public void setCymric(Cymric cymric) {
        this.cymric = cymric;
    }

    public void setStreetExtraordinaire(StreetExtraordinaire streetExtraordinaire) {
        this.streetExtraordinaire = streetExtraordinaire;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Siam getSiam() {
        return siam;
    }

    public Cymric getCymric() {
        return cymric;
    }

    public StreetExtraordinaire getStreetExtraordinaire() {
        return streetExtraordinaire;
    }
}
