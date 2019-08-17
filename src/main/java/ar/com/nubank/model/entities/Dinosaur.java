package ar.com.nubank.model.entities;

public class Dinosaur implements Entity {


    private int row;
    private int col;

    public Dinosaur(int row, int col) {

        this.row = row;
        this.col = col;
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
