package JediGalaxy;

public class Enemy {
    private GalaxyMatrix galaxyMatrix;

    public Enemy(GalaxyMatrix galaxyMatrix) {
        this.galaxyMatrix = galaxyMatrix;
    }

    public void destroyStars(int row, int col) {
        while (row >= 0 && col >= 0) {
            if (row < this.galaxyMatrix.getLength() && col < this.galaxyMatrix.getInnerLength(row)) {
                this.galaxyMatrix.setStar(row, col, 0);
            }
            row--;
            col--;
        }
    }
}
