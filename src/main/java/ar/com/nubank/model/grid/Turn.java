package ar.com.nubank.model.grid;


import ar.com.nubank.model.enums.TurnDirection;

public class Turn extends Location {
    private TurnDirection turnDirection;

    public TurnDirection getTurnDirection() {
        return turnDirection;
    }

    public void setTurnDirection(TurnDirection turnDirection) {
        this.turnDirection = turnDirection;
    }
}
