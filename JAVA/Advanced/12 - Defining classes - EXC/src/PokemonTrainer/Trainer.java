package PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String nameTrainer;
    private int numberBadges;
    private List<Pokemon> pokemonsListPerTrainer;

    public Trainer() {
    }

    public Trainer(String nameTrainer) {
        this.nameTrainer = nameTrainer;
        this.numberBadges = 0;
        this.pokemonsListPerTrainer = new ArrayList<>();
    }

    public void setNumberBadges(int numberBadges) {
        this.numberBadges = numberBadges;
    }

    public void setPokemonsListPerTrainer(List<Pokemon> pokemonsListPerTrainer) {
        this.pokemonsListPerTrainer = pokemonsListPerTrainer;
    }

    public String getNameTrainer() {
        return nameTrainer;
    }

    public int getNumberBadges() {
        return numberBadges;
    }

    public List<Pokemon> getPokemonsListPerTrainer() {
        return pokemonsListPerTrainer;
    }
}
