package ar.com.nubank.unit.robot;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.figures.Entity;
import ar.com.nubank.model.figures.Robot;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.DinosaurService;
import ar.com.nubank.services.RobotService;
import ar.com.nubank.utils.GridCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sun.jvm.hotspot.utilities.Assert;

import static org.mockito.Mockito.when;

public class TestRobotMovement {

    @InjectMocks
    RobotService robotService;

    @Mock
    DinosaurService dinosaurService;

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
        try {
            robotService.addRobot(1,1, Direction.DOWN);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");
         f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveBackwardLeft(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(1,1,Direction.RIGHT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveBackwardRight(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(1,48,Direction.LEFT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,48);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveBackwardDown(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(48,1,Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(48,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveBackward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardUp(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(1,1, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(0,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardLeft(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(1,1,Direction.LEFT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,0);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardRight(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(1,48,Direction.RIGHT);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(1,48);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(1,49);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void moveForwardDown(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        //Add robot
        try {
            robotService.addRobot(48,1,Direction.DOWN);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(false, "Exception thrown");
        }

        Entity f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");
        f = gridCache.getGrid().getElementAt(48,1);
        Assert.that(f == null, "Robot in unexpected position");

        try {
            robotService.moveForward(0);
        } catch (RobotNotFoundException | CannotMoveRobotException | ElementAlreadyPresentException e) {
            Assert.that(e instanceof CannotMoveRobotException, "Wrong exception thrown");
        }

        f = gridCache.getGrid().getElementAt(49,1);
        Assert.that(f != null, "Robot not in expected position");

    }

    @Test
    public void turnLeft(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));

        try {
            robotService.addRobot(0,0,Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.turnLeft(0);
        } catch (RobotNotFoundException e) {
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
            robotService.addRobot(0,0,Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.turnRight(0);
        } catch (RobotNotFoundException e) {
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
            dinosaurService.addDinosaur(0,1);
            dinosaurService.addDinosaur(1,0);
            dinosaurService.addDinosaur(1,2);
            dinosaurService.addDinosaur(3,1);

            robotService.addRobot(1,1,Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        try {
            robotService.attack(0);
        } catch (RobotNotFoundException e) {
            Assert.that(false, "Exception thrown");
        }

        Assert.that(gridCache.getGrid().getElementAt(0,1) == null, "Dinosaur 1 not destroyed");
        Assert.that(gridCache.getGrid().getElementAt(1,0) == null, "Dinosaur 2 not destroyed");
        Assert.that(gridCache.getGrid().getElementAt(1,2) == null, "Dinosaur 3 not destroyed");
        Assert.that(gridCache.getGrid().getElementAt(3,1) == null, "Dinosaur 4 not destroyed");


    }
}
