package _00_EnumDemo;

import javax.persistence.*;

@Entity(name = "demos")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

//    @Enumerated(EnumType.ORDINAL) //по индекс
    @Enumerated(EnumType.STRING)  //по стойност value
    private AccountType type;

    public User() {
    }

    public User(String username, AccountType type) {
        this.username = username;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
