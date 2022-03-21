package FootballBettingDatabase_6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users_6")
public class User_6 extends BaseEntity{

    private String username;
    private String password;
    private String email;
    private String fullName;
    private double balance;
//    private Set<Bet> bets;

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false, unique = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    @OneToMany(mappedBy = "user")
//    public Set<Bet> getBets() {
//        return bets;
//    }
//
//    public void setBets(Set<Bet> bets) {
//        this.bets = bets;
//    }
}
