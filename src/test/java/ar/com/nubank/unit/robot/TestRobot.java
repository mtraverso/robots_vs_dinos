package ar.com.nubank.unit.robot;

import ar.com.nubank.model.figures.Robot;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

public class TestRobot {

    @Test
    public void testString(){
        Robot robot = new Robot(0,0,0,0);

        Assert.that(robot.toString().equals("↑"),"Not ok facing direction");

         robot = new Robot(0,0,0,1);

        Assert.that(robot.toString().equals("→"),"Not ok facing direction");

         robot = new Robot(0,0,0,2);

        Assert.that(robot.toString().equals("↓"),"Not ok facing direction");

         robot = new Robot(0,0,0,3);

        Assert.that(robot.toString().equals("←"),"Not ok facing direction");

    }
}
