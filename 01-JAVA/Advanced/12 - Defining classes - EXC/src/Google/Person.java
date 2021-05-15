package Google;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private Car car;
    //null
    private List<Pokemon> pokemons;
    private List<Child> childrens;
    private List<Parent> parents;

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    public Car getCar() {
        return car;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Child> getChildrens() {
        return childrens;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void setChildrens(List<Child> childrens) {
        this.childrens = childrens;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public Person(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.childrens = new ArrayList<>();
        this.parents = new ArrayList<>();
    }
}
