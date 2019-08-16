package ar.com.nubank.model.figures;

public class Dinosaur implements Entity {

    private int id;
    private int row;
    private int col;

    public Dinosaur(int id, int row, int col) {
        this.id = id;
        this.row = row;
        this.col = col;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "D";
    }
}
