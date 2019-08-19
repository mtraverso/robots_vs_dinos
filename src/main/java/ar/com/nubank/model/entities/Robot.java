package ar.com.nubank.model.entities;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.utils.GridCache;

public class Robot implements Entity,Movable {

    private int row;
    private int col;
    private Direction facingDirection;
    private GridCache gridCache;

    public Robot(Location loc, Direction facingDirection, GridCache gridCache) {

        this.row = loc.getRow();
        this.col = loc.getCol();
        this.facingDirection = facingDirection;
        this.gridCache = gridCache;
    }



    public synchronized void turnLeft() {
        facingDirection = facingDirection.left();
    }

    public synchronized void turnRight() {
        facingDirection = facingDirection.right();
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    @Override
    public String toString() {
        String s = facingDirection.getValue();

        return s;
    }


    public synchronized void moveForward() throws CannotMoveElementException, NoElementFoundInPosition, ElementNotMovableException, ElementAlreadyPresentException, CannotClearElementAtPosition {
        int rowDest = this.row;
        int colDest = this.col;
        switch (facingDirection){
            case UP:
                rowDest--;
                break;
            case DOWN:
                rowDest++;
                break;
            case LEFT:
                colDest--;
                break;
            case RIGHT:
                colDest++;
                break;
        }
        moveTo(new Location(rowDest,colDest));
        this.row = rowDest;
        this.col = colDest;
    }

    public synchronized void moveBackward() throws CannotMoveElementException, NoElementFoundInPosition, ElementNotMovableException, ElementAlreadyPresentException, CannotClearElementAtPosition {
        int rowDest = this.row;
        int colDest = this.col;
        switch (facingDirection){
            case UP:
                rowDest++;
                break;
            case DOWN:
                rowDest--;
                break;
            case LEFT:
                colDest++;
                break;
            case RIGHT:
                colDest--;
                break;
        }
        moveTo(new Location(rowDest,colDest));
        this.row = rowDest;
        this.col = colDest;
    }

    public synchronized void attack() throws CannotClearElementAtPosition {
        gridCache.getGrid().clearElementAt(new Location(row-1,col));
        gridCache.getGrid().clearElementAt(new Location(row,col-1));
        gridCache.getGrid().clearElementAt(new Location(row+1,col));
        gridCache.getGrid().clearElementAt(new Location(row,col+1));

    }

    private void moveTo(Location to) throws CannotMoveElementException, NoElementFoundInPosition, ElementNotMovableException, ElementAlreadyPresentException, CannotClearElementAtPosition {
        gridCache.getGrid().moveElement(new Location(this.row, this.col),to);

    }
}
