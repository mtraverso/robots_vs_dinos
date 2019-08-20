package ar.com.nubank.model.grid;

import ar.com.nubank.model.entities.Robot;
import ar.com.nubank.model.enums.Direction;

public class RobotLocation extends Location {
    private final Direction direction;

    public RobotLocation (int row, int col, Direction direction){
        super(row,col);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    /*public void setDirection(Direction direction) {
        this.direction = direction;
    }*/
}
