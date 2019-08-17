package ar.com.nubank.exceptions;

import ar.com.nubank.model.grid.Location;

public class ElementAlreadyPresentException extends Exception {
    private int row;
    private int col;

    public ElementAlreadyPresentException(int x, int y){
        super("Element present at "+x+","+y);
        this.row = x;
        this.col = y;

    }

    public ElementAlreadyPresentException(Location location) {
        super("Element present at "+location.getRow()+","+location.getCol());
        this.row = location.getRow();
        this.col = location.getCol();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
