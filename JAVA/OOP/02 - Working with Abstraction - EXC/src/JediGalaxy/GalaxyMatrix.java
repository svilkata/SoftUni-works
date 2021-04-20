package JediGalaxy;

public class GalaxyMatrix {
    private int row;
    private int col;
    private Field field;

    public GalaxyMatrix(Field field) {
        this.field = field;
    }

    public int getLength() {
        return this.field.getLength();
    }

    public int getInnerLength(int dimention) {
        return this.field.getDimentionLength(dimention);
    }

    public void setStar(int row, int col, int newValue) {
        this.field.setCell(row, col, newValue);
    }

    public int getStar(int row, int col) {
        return this.field.getCell(row, col);
    }
}
