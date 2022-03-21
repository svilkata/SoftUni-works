package FootballBettingDatabase_6;

import java.io.Serializable;
import java.util.Objects;

public abstract class BetGamePK implements Serializable {

    private Game game;
    private Bet bet;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetGamePK that = (BetGamePK) o;
        return Objects.equals(game, that.getGame()) &&
                Objects.equals(bet, that.getBet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, bet);
    }
}
