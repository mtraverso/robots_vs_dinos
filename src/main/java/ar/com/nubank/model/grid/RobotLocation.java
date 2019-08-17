package ar.com.nubank.model.grid;

import ar.com.nubank.model.enums.Direction;

public class RobotLocation extends Location {
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
