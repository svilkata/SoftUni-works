package PokemonTrainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        List<Trainer> trainers = new ArrayList<>();

        while (!(line = reader.readLine()).equals("Tournament")) {
            String[] tokens = line.split("\\s+");
//            "<TrainerName> <PokemonName> <PokemonElement> <PokemonHealth>"
            String trainerName = tokens[0];
            String pokemonNameToAdd = tokens[1];
            String pokemonElToAdd = tokens[2];
            int pokemonHealthToAdd = Integer.parseInt(tokens[3]);

            Trainer trainer = new Trainer();
            boolean newTrainer = true;
            for (Trainer tr : trainers) {
                if (tr.getNameTrainer().equals(trainerName)) { //при условие, че всеки треньор няма покемони с еднакви имена!!!
                    Pokemon pokemon = new Pokemon(pokemonNameToAdd, pokemonElToAdd, pokemonHealthToAdd);
                    tr.getPokemonsListPerTrainer().add(pokemon);
                    newTrainer = false;
                    break;
                }
            }

            if (newTrainer) {
                trainer = new Trainer(trainerName);
                Pokemon pokemon = new Pokemon(pokemonNameToAdd, pokemonElToAdd, pokemonHealthToAdd);
                trainer.getPokemonsListPerTrainer().add(pokemon);
                trainers.add(trainer);
            }
        }

        while (!(line = reader.readLine()).equals("End")) {
            switch (line) {
                case "Fire":
                    tour(line, trainers);
                    break;
                case "Water":
                    tour(line, trainers);
                    break;
                case "Electricity":
                    tour(line, trainers);
                    break;
            }
        }

        trainers.stream()
                .sorted((tr1, tr2) -> Integer.compare(tr2.getNumberBadges(), tr1.getNumberBadges()))
                .forEach(tr -> {
                    System.out.println(String.format("%s %d %d", tr.getNameTrainer(),
                            tr.getNumberBadges(), tr.getPokemonsListPerTrainer().size()));
                });



    }

    private static void tour(String line, List<Trainer> trainers) {
        for (Trainer trainer : trainers) {
            boolean hasFire = false;
            for (Pokemon pokemon : trainer.getPokemonsListPerTrainer()) {
                if (pokemon.getElement().equals(line)) {
                    hasFire = true;
                    break;
                }
            }

            if (hasFire) {
                trainer.setNumberBadges(trainer.getNumberBadges() + 1);
            } else {
                List<Pokemon> pokemonsToDelete = new ArrayList<>();
                for (Pokemon pokemon : trainer.getPokemonsListPerTrainer()) {
                    pokemon.setHealthPokemon(pokemon.getHealthPokemon() - 10);
                    if (pokemon.getHealthPokemon() <= 0) {
                        pokemonsToDelete.add(pokemon);
                    }
                }

                for (Pokemon pok : pokemonsToDelete) {
                    trainer.getPokemonsListPerTrainer().remove(pok);
                }
            }
        }
    }
}
