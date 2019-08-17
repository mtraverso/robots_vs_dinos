package ar.com.nubank.services;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.entities.Dinosaur;
import ar.com.nubank.model.entities.Entity;
import ar.com.nubank.model.entities.Robot;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.utils.GridCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class GameService {



    @Autowired
    private GridCache gridCache;


    public GameService() {



    }

    public void addRobot(int row, int col, Direction direction) throws ElementAlreadyPresentException, GridNotInitializedException, CannotAddElementException {
        addRobot(new Location(row,col), direction);
    }

    public void addRobot(Location location, Direction direction) throws ElementAlreadyPresentException, GridNotInitializedException, CannotAddElementException {
        if (gridCache.getGrid() == null) {
            throw new GridNotInitializedException();
        }
        if (location.getRow() < 0 || location.getRow() >= gridCache.getGrid().height() || location.getCol() < 0 || location.getCol() >= gridCache.getGrid().width()) {
            throw new CannotAddElementException("Out of bounds");
        }
        if (!gridCache.getGrid().hasElementAt(location)) {

            Robot r = new Robot(location, direction,gridCache);
            gridCache.getGrid().setElementAt(r, location);

        } else {
            throw new ElementAlreadyPresentException(location);
        }
    }

    public void turnLeft(int row, int col) throws NoElementFoundInPosition, ElementNotMovableException {
        Robot r = getRobotAtPosition(row, col);
        r.turnLeft();
    }

    public void turnRight(int row, int col) throws NoElementFoundInPosition, ElementNotMovableException {
        Robot r = getRobotAtPosition(row, col);

        r.turnRight();
    }

    private Robot getRobotAtPosition(int row, int col) throws NoElementFoundInPosition, ElementNotMovableException {
        Entity e = gridCache.getGrid().getElementAt(new Location(row, col));
        if (e == null) {
            throw new NoElementFoundInPosition(row, col);
        }
        if (!(e instanceof Robot)) {
            throw new ElementNotMovableException(row, col);
        }
        return (Robot) e;
    }


    public void moveForward(int row, int col) throws NoElementFoundInPosition, ElementNotMovableException, CannotMoveElementException, ElementAlreadyPresentException, CannotClearElementAtPosition {
        Robot r = getRobotAtPosition(row, col);

        r.moveForward();

    }

    public void moveBackward(int row, int col) throws NoElementFoundInPosition, ElementNotMovableException, CannotMoveElementException, ElementAlreadyPresentException, CannotClearElementAtPosition {
        Robot r = getRobotAtPosition(row, col);
        r.moveBackward();
    }

    public void attack(int row, int col) throws NoElementFoundInPosition, ElementNotMovableException, CannotClearElementAtPosition {
        Robot r = getRobotAtPosition(row, col);
        r.attack();


    }

    public void addDinosaur(int row, int col) throws ElementAlreadyPresentException, GridNotInitializedException, CannotAddElementException {
        if (gridCache.getGrid() == null) {
            throw new GridNotInitializedException();
        }
        if (row < 0 || row >= gridCache.getGrid().height() || col < 0 || col >= gridCache.getGrid().width()) {
            throw new CannotAddElementException("Out of bounds");
        }
        if (!gridCache.getGrid().hasElementAt(new Location(row, col))) {

            Dinosaur d = new Dinosaur( row, col);
            gridCache.getGrid().setElementAt(d, new Location(row,col));

        } else {
            throw new ElementAlreadyPresentException(row, col);
        }
    }

    public void createGrid(int x, int y) {
        Grid grid = new Grid(x, y);
        gridCache.setGrid(grid);

    }

    public String printGrid() {
        return gridCache.getGrid().printGrid();
    }


    public boolean getGridStatusOk() {
        return gridCache.getGrid() != null;
    }

}
