package ar.com.nubank.model.grid;

import ar.com.nubank.model.enums.Movement;

public class RobotMovement extends Location {
    private Movement movement;

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }
}
