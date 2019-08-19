package ar.com.nubank.model.grid;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.entities.Entity;
import ar.com.nubank.model.entities.Movable;
import ar.com.nubank.model.entities.Robot;

public class Grid {
    private final Entity[][] grid;

    public Grid(int rows, int cols) {
        grid = new Entity[rows][cols];
    }

    public Entity getElementAt(Location loc ) {

        return grid[loc.getRow()][loc.getCol()];
    }

    public synchronized void clearElementAt(Location loc ) throws CannotClearElementAtPosition {
        if(isOutOfBounds(loc)){
            throw new CannotClearElementAtPosition("Out of bounds");
        }
        grid[loc.getRow()][loc.getCol()] = null;
    }

    public boolean hasElementAt(int row, int col){
        return hasElementAt(new Location(row,col));
    }

    public boolean hasElementAt(Location loc) {
        return grid[loc.getRow()][loc.getCol()] != null;
    }

    public synchronized void setElementAt(Entity f, Location loc) {
        grid[loc.getRow()][loc.getCol()] = f;
    }

    public synchronized void moveElement(Location from, Location to) throws CannotMoveElementException, NoElementFoundInPosition, ElementNotMovableException, ElementAlreadyPresentException, CannotClearElementAtPosition {
        if(getElementAt(from) == null){
            throw new NoElementFoundInPosition(from.getRow(),from.getCol());
        }
        if(! (getElementAt(from) instanceof Movable)){
            throw new ElementNotMovableException(from.getRow(),from.getCol());
        }

        if(isOutOfBounds(to)){
            throw new CannotMoveElementException("Out of bounds");
        }
        if(getElementAt(to) != null){
            throw new ElementAlreadyPresentException(to.getRow(),to.getCol());
        }
        Robot e = (Robot)getElementAt(from);

        clearElementAt(from);
        setElementAt(e,to);
    }

    private boolean isOutOfBounds(Location to) {
        return to.getRow() < 0 || to.getRow() >= height() || to.getCol()<0 || to.getCol() >= width();
    }

    public int width() {
        return grid[0].length;
    }

    public int height() {
        return grid.length;
    }

    public String printGrid() {
        StringBuilder buffer = new StringBuilder();
        for (Entity[] entities : grid) {
            for (Entity entity : entities) {
                buffer.append("|");
                if (entity != null)
                    buffer.append(" ").append(entity).append(" ");
                else
                    buffer.append("   ");
            }
            buffer.append("|");
            buffer.append("\n");
        }
        buffer.append("\n");
        return buffer.toString();

    }


    public Entity getElementAt(int row, int col) {
        return getElementAt(new Location(row,col));
    }
}
