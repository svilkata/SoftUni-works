package JediGalaxy;

public class Player {
    private GalaxyMatrix galaxyMatrix;
    private long points;

    public Player(GalaxyMatrix galaxyMatrix) {
        this.galaxyMatrix = galaxyMatrix;
    }

    public void collectStars(int row, int col) {
        while (row >= 0 && col < this.galaxyMatrix.getInnerLength(1)) {
            if (row < this.galaxyMatrix.getLength() && col >= 0 && col < this.galaxyMatrix.getInnerLength(0)) {
                this.points += this.galaxyMatrix.getStar(row, col);
            }

            row--;
            col++;
        }
    }

    public long getPoints() {
        return this.points;
    }
}
