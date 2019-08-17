package ar.com.nubank.unit.robot;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.entities.Entity;
import ar.com.nubank.model.entities.Robot;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.GridCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import sun.jvm.hotspot.utilities.Assert;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)

public class TestRobotAddition {

    @InjectMocks
    GameService robotService;

    @Mock
    GridCache gridCache;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void addRobotOk(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        try {
            robotService.addRobot(5,5, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false,"Exception thrown");
        }
        Assert.that(gridCache.getGrid().hasElementAt(5,5),"Element not found at 5,5");
        Assert.that(!gridCache.getGrid().hasElementAt(5,6),"Found element at 5,6");

        Entity fig = gridCache.getGrid().getElementAt(5,5);
        Assert.that(fig instanceof Robot,"Element isn't robot");
        assert fig instanceof Robot;
        Robot r = (Robot)fig;
        Assert.that(r.getFacingDirection() == Direction.UP,"Robot facing direction not ok");

    }

    @Test
    public void addRobotElementAlreadyPresent(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        try {
            robotService.addRobot(5,5, Direction.UP);
            robotService.addRobot(5,5, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(e instanceof ElementAlreadyPresentException,"Exception not correct");
        }
        Assert.that(gridCache.getGrid().hasElementAt(5,5),"Element not found at 5,5");
        Assert.that(!gridCache.getGrid().hasElementAt(5,6),"Found element at 5,6");

        Entity fig = gridCache.getGrid().getElementAt(5,5);
        Assert.that(fig instanceof Robot,"Element isn't robot");
        assert fig instanceof Robot;
        Robot r = (Robot)fig;
        Assert.that(r.getFacingDirection() == Direction.UP,"Robot facing direction not ok");

    }

    @Test
    public void addRobotGridNotInit(){
        //when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        try {
            robotService.addRobot(5,5, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(e instanceof GridNotInitializedException,"Exception not correct");
        }
    }

    @Test
    public void addRobotOutOfBounds(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        try {
            robotService.addRobot(0,-1, Direction.UP);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(e instanceof CannotAddElementException,"Exception not correct");
        }
    }





}
