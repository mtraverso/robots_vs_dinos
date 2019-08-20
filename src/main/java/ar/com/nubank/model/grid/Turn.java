package ar.com.nubank.model.grid;


import ar.com.nubank.model.enums.TurnDirection;

public class Turn extends Location {
    private final TurnDirection turnDirection;

    public Turn(int row, int col, TurnDirection turnDirection){
        super(row,col);
        this.turnDirection = turnDirection;
    }

    public TurnDirection getTurnDirection() {
        return turnDirection;
    }


}
