package ar.com.nubank.unit.robot;

import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.entities.Robot;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.utils.GridCache;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.jvm.hotspot.utilities.Assert;

public class TestRobot {

    @Autowired
    private GridCache gridCache;

    @Test
    public void testString(){
        Robot robot = new Robot(new Location(0,0), Direction.UP, gridCache);

        Assert.that(robot.toString().equals("↑"),"Not ok facing direction");

         robot = new Robot(new Location(0,0),Direction.RIGHT, gridCache);

        Assert.that(robot.toString().equals("→"),"Not ok facing direction");

         robot = new Robot(new Location(0,0),Direction.DOWN, gridCache);

        Assert.that(robot.toString().equals("↓"),"Not ok facing direction");

         robot = new Robot(new Location(0,0),Direction.LEFT, gridCache);

        Assert.that(robot.toString().equals("←"),"Not ok facing direction");

    }
}
