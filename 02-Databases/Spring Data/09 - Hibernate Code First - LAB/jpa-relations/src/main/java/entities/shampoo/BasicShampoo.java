package entities.shampoo;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class BasicShampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    //name е при нашата таблица, referencedColumnName е отсрещната таблица
    private BasicLabel label;

    //много шампони да принадлеждат на една партида
    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    //Ако ни трябват група от елементи, които нямат отсрещна таблица
    @ElementCollection
    private List<String> someNames;

    public void setSomeNames(List<String> someNames) {
        this.someNames = someNames;
    }


    public BasicShampoo() {
    }

    public BasicShampoo(String name, BasicLabel label, ProductionBatch batch) {
        this.name = name;
        this.label = label;
        this.batch = batch;
        this.ingredients = new HashSet<>();
    }

    public void addIngredient(BasicIngredient basicIngredient) {
        this.ingredients.add(basicIngredient);
    }

    public Set<BasicIngredient> getIngredients() {
        return Collections.unmodifiableSet(this.ingredients);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicLabel getLabel() {
        return label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    public ProductionBatch getBatch() {
        return batch;
    }

    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }
}



