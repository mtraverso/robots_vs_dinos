package ar.com.nubank.model.grid;

import ar.com.nubank.model.enums.MovementDirection;

public class Movement extends Location {
    private final MovementDirection movementDirection;

    public MovementDirection getMovementDirection() {
        return movementDirection;
    }

    public Movement(int row, int col, MovementDirection movementDirection){
        super(row,col);
        this.movementDirection = movementDirection;
    }


}
