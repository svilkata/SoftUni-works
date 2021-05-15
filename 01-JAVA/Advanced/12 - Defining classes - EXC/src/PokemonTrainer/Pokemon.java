package PokemonTrainer;

public class Pokemon {
    private String namePokemon;
    private String element;
    private int healthPokemon;

    public Pokemon(String namePokemon, String element, int healthPokemon) {
        this.namePokemon = namePokemon;
        this.element = element;
        this.healthPokemon = healthPokemon;
    }

    public void setHealthPokemon(int healthPokemon) {
        this.healthPokemon = healthPokemon;
    }

    public String getNamePokemon() {
        return namePokemon;
    }

    public String getElement() {
        return element;
    }

    public int getHealthPokemon() {
        return healthPokemon;
    }
}
