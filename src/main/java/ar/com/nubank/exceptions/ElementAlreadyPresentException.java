package ar.com.nubank.exceptions;

public class ElementAlreadyPresentException extends Exception {
    private int row;
    private int col;

    public ElementAlreadyPresentException(int x, int y){
        super("Element present at "+x+","+y);
        this.row = x;
        this.col = y;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
