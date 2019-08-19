package ar.com.nubank.unit.robot;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.entities.Entity;
import ar.com.nubank.model.entities.Robot;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.GridCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sun.jvm.hotspot.utilities.Assert;

import static org.mockito.Mockito.when;

public class TestMovementDirection {

    @InjectMocks
    GameService gameService;

    @Mock
    GridCache gridCache;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void moveBackwardUp(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot

        Location init = new Location(1,1);

        try {
            gameService.addRobot(1,1, Direction.DOWN);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveBackward(1,1);
        } catch (CannotMoveElementException | NoElementFoundInPosition | ElementNotMovableException | ElementAlreadyPresentException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");
         f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveBackward(0,1);
        } catch (CannotMoveElementException | NoElementFoundInPosition | ElementNotMovableException | ElementAlreadyPresentException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveBackwardLeft(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(1,1, Direction.RIGHT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveBackward(1,1);
        } catch ( CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveBackward(1,0);
        } catch ( CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveBackwardRight(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(1,48, Direction.LEFT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveBackward(1,48);
        } catch ( CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,48);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveBackward(1,49);
        } catch ( CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveBackwardDown(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(48,1, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveBackward(48,1);
        } catch ( CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(48,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveBackward(49,1);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardUp(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(1,1, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveForward(1,1);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveForward(0,1);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardLeft(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(1,1, Direction.LEFT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveForward(1,1);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveForward(1,0);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardRight(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(1,48, Direction.RIGHT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveForward(1,48);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,48);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveForward(1,49);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardDown(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            gameService.addRobot(48,1, Direction.DOWN);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.moveForward(48,1);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(48,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            gameService.moveForward(49,1);
        } catch (CannotMoveElementException | ElementAlreadyPresentException | NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(e instanceof CannotMoveElementException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void turnLeft(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));

        try {
            gameService.addRobot(0,0, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.turnLeft(0,0);
        } catch ( NoElementFoundInPosition | ElementNotMovableException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(0,0);
        Assert.that(f instanceof Robot,"Not a robot");
        Robot r = (Robot)f;
        Assert.that(r.getFacingDirection() == Direction.LEFT,"Not facing correct direction");
    }

    @Test
    public void turnRight(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));

        try {
            gameService.addRobot(0,0, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.turnRight(0,0);
        } catch ( NoElementFoundInPosition | ElementNotMovableException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(0,0);
        Assert.that(f instanceof Robot,"Not a robot");
        Robot r = (Robot)f;
        Assert.that(r.getFacingDirection() == Direction.RIGHT,"Not facing correct direction");
    }

    @Test
    public void attack(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));

        try {
            gameService.addDinosaur(0,1);
            gameService.addDinosaur(1,0);
            gameService.addDinosaur(1,2);
            gameService.addDinosaur(2,1);

            gameService.addRobot(1,1, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            gameService.attack(1,1);
        } catch ( NoElementFoundInPosition | ElementNotMovableException | CannotClearElementAtPosition e) {
            Assert.that(false, "Exception thrown");
        }

        Assert.that(gridCache.getGrid().getElementAt(0,1) == null, "Dinosaur 1 not destroyed");
        Assert.that(gridCache.getGrid().getElementAt(1,0) == null, "Dinosaur 2 not destroyed");
        Assert.that(gridCache.getGrid().getElementAt(1,2) == null, "Dinosaur 3 not destroyed");
        Assert.that(gridCache.getGrid().getElementAt(2,1) == null, "Dinosaur 4 not destroyed");


    }
}
