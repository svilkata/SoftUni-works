package FootballBettingDatabase_6;

import java.io.Serializable;
import java.util.Objects;

public abstract class PlayerStatisticsPK implements Serializable {

    private Game game;
    private Player player;

    public PlayerStatisticsPK(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public PlayerStatisticsPK(){

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStatisticsPK that = (PlayerStatisticsPK) o;
        return Objects.equals(game, that.getGame()) &&
                Objects.equals(player, that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, player);
    }
}
