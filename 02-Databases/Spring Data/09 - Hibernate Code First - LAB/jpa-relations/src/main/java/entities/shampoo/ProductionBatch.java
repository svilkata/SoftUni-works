package entities.shampoo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    //един batch да знае, че има много шампоани
    @OneToMany (mappedBy = "batch", targetEntity = BasicShampoo.class,
            fetch = FetchType.LAZY,        //не ги вземай/зареждай всички шампоани всеки път //FetchType.EAGER е обратното
            cascade = CascadeType.ALL)    //ако изтриеш batch какво да прави със свързаните неща/записи
    private Set<BasicShampoo> shampoos;

    public ProductionBatch() {
    }

    public ProductionBatch(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BasicShampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
