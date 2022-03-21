package FootballBettingDatabase_6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity {

    private double money;
    private Date dateAndTime;
    private User_6 user;

    @Column(name = "bet_money", nullable = false)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Column(name = "date_time", columnDefinition = "TIMESTAMP")
    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @ManyToOne
    public User_6 getUser() {
        return user;
    }

    public void setUser(User_6 user) {
        this.user = user;
    }
}
