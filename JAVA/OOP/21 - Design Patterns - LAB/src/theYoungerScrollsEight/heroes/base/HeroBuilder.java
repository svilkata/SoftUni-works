package theYoungerScrollsEight.heroes.base;

public class HeroBuilder {
    private String name;
    private int level;

    public HeroBuilder(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public HeroBuilder() {};

    public HeroBuilder build() {
        return new HeroBuilder(this.name, this.level);
    }

    public HeroBuilder withName(String name) {
        this.name = name;
        return this; //връща текущата инстанция на класа
    }

    public HeroBuilder withLevel(int level) {
        this.level = level;
        return this; //връща текущата инстанция на класа
    }
}
