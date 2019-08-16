package ar.com.nubank.model.figures;

import static ar.com.nubank.model.enums.Direction.*;

public class Robot implements Entity {
    private int id;
    private int row;
    private int col;
    private int facingDirection;

    public Robot(int id, int row, int col, int facingDirection) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.facingDirection = facingDirection;
    }

    public void turnLeft() {
        facingDirection = facingDirection == 0 ? 3 : facingDirection - 1;
    }

    public void turnRight() {
        facingDirection = facingDirection == 3 ? 0 : facingDirection + 1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getFacingDirection() {
        return facingDirection;
    }

    @Override
    public String toString() {
        String s = "";
        switch (facingDirection) {
            case UP:
                s = "↑";
                break;
            case RIGHT:
                s = "→";
                break;
            case DOWN:
                s = "↓";
                break;
            case LEFT:
                s = "←";
                break;
        }
        return s;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
