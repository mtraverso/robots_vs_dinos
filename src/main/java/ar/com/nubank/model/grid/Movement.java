package ar.com.nubank.model.grid;

import ar.com.nubank.model.enums.MovementDirection;

public class Movement extends Location {
    private MovementDirection movementDirection;

    public MovementDirection getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(MovementDirection movementDirection) {
        this.movementDirection = movementDirection;
    }
}
