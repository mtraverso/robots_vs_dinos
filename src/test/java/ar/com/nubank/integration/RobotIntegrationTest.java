package ar.com.nubank.integration;

import ar.com.nubank.integration.base.BaseIntegrationTest;
import ar.com.nubank.model.entities.Entity;
import ar.com.nubank.model.entities.Robot;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.enums.MovementDirection;
import ar.com.nubank.model.enums.TurnDirection;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.model.grid.RobotLocation;
import ar.com.nubank.model.grid.Movement;
import ar.com.nubank.model.grid.Turn;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.GridCache;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sun.jvm.hotspot.utilities.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RobotIntegrationTest extends BaseIntegrationTest {

    @Autowired
    GameService gameService;

    @Autowired
    GridCache gridCache;

    @After
    public void tearDown(){
        gridCache.setGrid(null);
    }

    //Test adding a robot when grid is not initialized
    @Test
    public void testA_AddRobot(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(1);
        rl.setCol(1);
        rl.setDirection(Direction.UP);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.FORBIDDEN), "Grid not ok");
        Assert.that(resp.getBody() != null, "Response body not ok");
    }

    //Test add robot after grid initialized
    @Test
    public void testB_CreateGridAddRobot(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(1);
        rl.setCol(1);
        rl.setDirection(Direction.UP);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.CREATED),"Error code different");



    }

    //Test adding a robot on same position than any other element.
    @Test
    public void testC_CreateGridAddRobot(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(1);
        rl.setCol(1);
        rl.setDirection(Direction.UP);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp3 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp3.getStatusCode().equals(HttpStatus.FORBIDDEN),"Error code different");

    }

    //Test adding a robot out of bounds of grid.
    @Test
    public void testD_CreateGridAddRobotIllegalPosition(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(-1);
        rl.setCol(1);
        rl.setDirection(Direction.UP);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.FORBIDDEN),"Error code different");


    }

    @Test
    public void testE_MoveRobotAround(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(5);
        rl.setCol(5);
        rl.setDirection(Direction.UP);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.CREATED),"Error code different");


    }

    @Test
    public void testF_MoveRobotAround(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(5);
        rl.setCol(5);
        rl.setDirection(Direction.UP);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        Turn rt = new Turn();
        rt.setCol(5);
        rt.setRow(5);
        rt.setTurnDirection(TurnDirection.RIGHT);

        Entity e1  =gridCache.getGrid().getElementAt(5,5);
        Assert.that(e1 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e1).getFacingDirection().equals(Direction.UP),"Orientation not correct");
        ResponseEntity<String> turnResp = testRestTemplate.postForEntity("/robot/turn",rt,String.class);
        Assert.that(turnResp.getStatusCode().equals(HttpStatus.OK),"Executed ok");
        Entity e2  =gridCache.getGrid().getElementAt(5,5);
        Assert.that(e2 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e2).getFacingDirection().equals(Direction.RIGHT),"Orientation not correct");

        Movement rm = new Movement();
        rm.setCol(5);
        rm.setRow(5);
        rm.setMovementDirection(MovementDirection.FORWARD);

        ResponseEntity<String> respMove = testRestTemplate.postForEntity("/robot/move",rm,String.class);
        Assert.that(respMove.getStatusCode().equals(HttpStatus.OK),"Executed ok");
        Entity e3  =gridCache.getGrid().getElementAt(5,6);
        Assert.that(e3 != null, "Elem not in position");
        Assert.that(e3 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e3).getFacingDirection().equals(Direction.RIGHT),"Orientation not correct");


        Turn rt2 = new Turn();
        rt2.setCol(6);
        rt2.setRow(5);
        rt2.setTurnDirection(TurnDirection.LEFT);

        Entity e12  =gridCache.getGrid().getElementAt(5,6);
        Assert.that(e12 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e12).getFacingDirection().equals(Direction.RIGHT),"Orientation not correct");
        turnResp = testRestTemplate.postForEntity("/robot/turn",rt2,String.class);
        Assert.that(turnResp.getStatusCode().equals(HttpStatus.OK),"Executed ok");
        Entity e22  =gridCache.getGrid().getElementAt(5,6);
        Assert.that(e22 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e22).getFacingDirection().equals(Direction.UP),"Orientation not correct");

        Movement rm2 = new Movement();
        rm2.setCol(6);
        rm2.setRow(5);
        rm2.setMovementDirection(MovementDirection.BACKWARD);

        respMove = testRestTemplate.postForEntity("/robot/move",rm2,String.class);
        Assert.that(respMove.getStatusCode().equals(HttpStatus.OK),"Executed ok");
        Entity e23  =gridCache.getGrid().getElementAt(6,6);
        Assert.that(e23 != null, "Elem not in position");
        Assert.that(e23 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e23).getFacingDirection().equals(Direction.UP),"Orientation not correct");

    }

    @Test
    public void testG_RobotAttack(){
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");


        Location dLoc = new Location(1,1);
        ResponseEntity<String> resp1 = testRestTemplate.postForEntity("/dinosaur",dLoc,String.class);
        Assert.that(resp1.getStatusCode().equals(HttpStatus.CREATED),"Code not correct");
        dLoc = new Location(5,1);
        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/dinosaur",dLoc,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.CREATED),"Code not correct");
        RobotLocation locRob = new RobotLocation();
        locRob.setRow(2);
        locRob.setCol(1);
        locRob.setDirection(Direction.UP);
        ResponseEntity<String> resp3 = testRestTemplate.postForEntity("/robot",locRob,String.class);
        Assert.that(resp3.getStatusCode().equals(HttpStatus.CREATED),"Code not correct");

        Location loc = new Location(2,1);
        testRestTemplate.postForEntity("/robot/attack",loc,String.class);
        Assert.that(gridCache.getGrid().getElementAt(1,1) == null,"Dino not destroyed");

        Turn turn = new Turn();
        turn.setCol(1);
        turn.setRow(2);
        turn.setTurnDirection(TurnDirection.RIGHT);
        testRestTemplate.postForEntity("/robot/turn",turn,String.class);
        testRestTemplate.postForEntity("/robot/turn",turn,String.class);
        Entity e22  =gridCache.getGrid().getElementAt(2,1);
        Assert.that(e22 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e22).getFacingDirection().equals(Direction.DOWN),"Orientation not correct");

        Movement movement = new Movement();
        movement.setMovementDirection(MovementDirection.FORWARD);
        movement.setRow(2);
        movement.setCol(1);
        testRestTemplate.postForEntity("/robot/move",movement,String.class);
        Entity e23  =gridCache.getGrid().getElementAt(3,1);
        Assert.that(e23 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e23).getFacingDirection().equals(Direction.DOWN),"Orientation not correct");

        movement.setRow(3);
        testRestTemplate.postForEntity("/robot/move",movement,String.class);
        Entity e33  =gridCache.getGrid().getElementAt(4,1);
        Assert.that(e33 instanceof Robot,"Not a robot");
        Assert.that(((Robot)e33).getFacingDirection().equals(Direction.DOWN),"Orientation not correct");

        loc = new Location(4,1);
        testRestTemplate.postForEntity("/robot/attack",loc,String.class);
        Assert.that(gridCache.getGrid().getElementAt(5,1) == null,"Dino not destroyed");
    }


}
