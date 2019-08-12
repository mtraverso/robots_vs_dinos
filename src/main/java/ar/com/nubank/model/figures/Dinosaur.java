package ar.com.nubank.model.figures;

public class Dinosaur implements  Figure {

    private int id;
    private int row;
    private int col;

    public Dinosaur(int id, int row, int col) {
        this.id = id;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "D";
    }
}
