package FootballBettingDatabase_6;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Date dateAndTime;
    private double homeTeamWinBetRate;
    private double awayTeamWinBetRate;
    private double drawGameBetRate;
    private Round round;
    private Competition competition;

    @OneToOne
    @JoinColumn(name = "home_team")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @OneToOne
    @JoinColumn(name = "away_team")
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Column(name = "home_team_goals")
    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    @Column(name = "away_team_goals")
    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Column(name = "date_tupe", columnDefinition = "TIMESTAMP")
    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Column(name = "home_team_win_bet_rate")
    public double getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    @Column(name = "away_team_win_bet_rate")
    public double getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    @Column(name = "draw_game_bet_rate")
    public double getDrawGameBetRate() {
        return drawGameBetRate;
    }

    public void setDrawGameBetRate(double drawGameBetRate) {
        this.drawGameBetRate = drawGameBetRate;
    }

    @ManyToOne
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @ManyToOne
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
