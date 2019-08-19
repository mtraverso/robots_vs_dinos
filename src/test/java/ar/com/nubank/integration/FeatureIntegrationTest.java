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
import ar.com.nubank.utils.GridCache;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sun.jvm.hotspot.utilities.Assert;

import javax.xml.ws.Response;
import java.util.Objects;

public class FeatureIntegrationTest extends BaseIntegrationTest {

/*
    * Be able to create an empty simulation space - an empty 50 x 50 grid;
    * Be able to create a robot in a certain position and facing direction;
    * Be able to create a dinosaur in a certain position;
    * Issue instructions to a robot - a robot can turn left, turn right, move forward, move backwards, and attack;
    * A robot attack destroys dinosaurs around it (in front, to the left, to the right or behind);
    * The attack does not affect robot facing direction;
    * No need to worry about the dinosaurs - dinosaurs don't move;
    * Display the simulation's current state;
    * Two or more entities (robots or dinosaurs) cannot occupy the same position;
    * Attempting to move a robot outside the simulation space is an invalid operation.
*/

    //Destroy grid
    @After
    public void tearDown(){
        gridCache.setGrid(null);
    }

    //Used only for validations
    @Autowired
    GridCache gridCache;

    //* Be able to create an empty simulation space - an empty 50 x 50 grid;
    @Test
    public void createSpace(){
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null,String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Not created ok");
        Assert.that(gridCache.getGrid().width() == 50,"Grid isn't 50x50");
        Assert.that(gridCache.getGrid().height() == 50,"Grid isn't 50x50");

    }

    //* Be able to create a robot in a certain position and facing direction;
    @Test
    public void createRobot(){

        testRestTemplate.postForEntity("/grid",null,String.class);

        RobotLocation robotLocation = new RobotLocation();
        robotLocation.setDirection(Direction.UP);
        robotLocation.setRow(5);
        robotLocation.setCol(5);
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/robot",robotLocation,String.class);


        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Not created ok");
        Assert.that(gridCache.getGrid().getElementAt(5,5) != null,"Not in position");
        Assert.that(((Robot)gridCache.getGrid().getElementAt(5,5)).getFacingDirection().equals(Direction.UP),"Not correct facing direction");

    }

    //* Be able to create a dinosaur in a certain position;
    @Test
    public void createDinosaur(){
        testRestTemplate.postForEntity("/grid",null,String.class);

        Location loc = new Location();
        loc.setRow(5);
        loc.setCol(4);


        ResponseEntity<String> resp = testRestTemplate.postForEntity("/dinosaur",loc,String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Not created ok");
        Assert.that(gridCache.getGrid().getElementAt(5,4) != null,"Not in position");

    }

    //* Issue instructions to a robot - a robot can turn left, turn right, move forward, move backwards, and attack;
    @Test
    public void move(){
        testRestTemplate.postForEntity("/grid",null,String.class);

        RobotLocation robotLocation = new RobotLocation();
        robotLocation.setDirection(Direction.UP);
        robotLocation.setRow(5);
        robotLocation.setCol(5);
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/robot",robotLocation,String.class);

        //Turn left
        Turn turnLeft = new Turn();
        turnLeft.setTurnDirection(TurnDirection.LEFT);
        turnLeft.setRow(5);
        turnLeft.setCol(5);

        ResponseEntity<String> respTurn = testRestTemplate.postForEntity("/robot/turn",turnLeft,String.class);
        Assert.that(respTurn.getStatusCode().equals(HttpStatus.OK),"Executed ok");

        Entity e1 = gridCache.getGrid().getElementAt(5,5);
        Assert.that(e1 instanceof Robot, "Not a robot");
        Robot r1 = (Robot)e1;
        Assert.that(r1.getFacingDirection().equals(Direction.LEFT),"Direction not ok");

        //Turn right
        Turn turnRight = new Turn();
        turnRight.setTurnDirection(TurnDirection.RIGHT);
        turnRight.setRow(5);
        turnRight.setCol(5);

        respTurn = testRestTemplate.postForEntity("/robot/turn",turnRight,String.class);
        Assert.that(respTurn.getStatusCode().equals(HttpStatus.OK),"Executed ok");

        Entity e2 = gridCache.getGrid().getElementAt(5,5);
        Assert.that(e2 instanceof Robot, "Not a robot");
        Robot r2 = (Robot)e2;
        Assert.that(r2.getFacingDirection().equals(Direction.UP),"Direction not ok");

        //Move forward
        Movement moveForward = new Movement();
        moveForward.setMovementDirection(MovementDirection.FORWARD);
        moveForward.setRow(5);
        moveForward.setCol(5);

        ResponseEntity<String> respMove = testRestTemplate.postForEntity("/robot/move",moveForward,String.class);
        Assert.that(respMove.getStatusCode().equals(HttpStatus.OK),"Executed ok");

        Entity prev = gridCache.getGrid().getElementAt(5,5);
        Entity e3 = gridCache.getGrid().getElementAt(4,5);
        Assert.that(e3 != null, "Robot not moved");
        Assert.that(prev == null, "Robot not moved");

        Assert.that(e3 instanceof Robot, "Not a robot");

        //Move backward
        Movement moveBackward = new Movement();
        moveBackward.setMovementDirection(MovementDirection.BACKWARD);
        moveBackward.setRow(4);
        moveBackward.setCol(5);

        respMove = testRestTemplate.postForEntity("/robot/move",moveBackward,String.class);
        Assert.that(respMove.getStatusCode().equals(HttpStatus.OK),"Executed ok");

        Entity prev2 = gridCache.getGrid().getElementAt(4,5);
        Entity e4 = gridCache.getGrid().getElementAt(5,5);
        Assert.that(e4 != null, "Robot not moved");
        Assert.that(prev2 == null, "Robot not moved");
        Assert.that(e4 instanceof Robot, "Not a robot");




    }

    //* A robot attack destroys dinosaurs around it (in front, to the left, to the right or behind);
    @Test
    public void attack(){
        testRestTemplate.postForEntity("/grid",null,String.class);

        RobotLocation robotLocation = new RobotLocation();
        robotLocation.setDirection(Direction.UP);
        robotLocation.setRow(5);
        robotLocation.setCol(5);
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/robot",robotLocation,String.class);

        //Add dinosaur
        Location dino1 = new Location();
        dino1.setRow(4);
        dino1.setCol(5);

        Location dino2 = new Location();
        dino2.setRow(5);
        dino2.setCol(4);

        Location dino3 = new Location();
        dino3.setRow(5);
        dino3.setCol(6);

        Location dino4 = new Location();
        dino4.setRow(6);
        dino4.setCol(5);

        Location attack = new Location();
        attack.setCol(5);
        attack.setRow(5);

        testRestTemplate.postForEntity("/dinosaur",dino1,String.class);
        testRestTemplate.postForEntity("/dinosaur",dino2,String.class);
        testRestTemplate.postForEntity("/dinosaur",dino3,String.class);
        testRestTemplate.postForEntity("/dinosaur",dino4,String.class);

        Entity eD1Bef = gridCache.getGrid().getElementAt(dino1);
        Assert.that(eD1Bef != null, "Dino not added");

        Entity eD2Bef = gridCache.getGrid().getElementAt(dino2);
        Assert.that(eD2Bef != null, "Dino not added");

        Entity eD3Bef = gridCache.getGrid().getElementAt(dino3);
        Assert.that(eD3Bef != null, "Dino not added");

        Entity eD4Bef = gridCache.getGrid().getElementAt(dino4);
        Assert.that(eD4Bef != null, "Dino not added");

        ResponseEntity<String> attackResp = testRestTemplate.postForEntity("/robot/attack",attack,String.class);
        Assert.that(attackResp.getStatusCode().equals(HttpStatus.OK),"Executed ok");

        Entity e5 = gridCache.getGrid().getElementAt( attack);
        Assert.that(e5 != null, "Robot not moved");
        Assert.that(e5 instanceof Robot, "Not a robot");

        //* The attack does not affect robot facing direction;
        Robot r = (Robot) e5;
        Assert.that(r.getFacingDirection().equals(Direction.UP),"Facing direction changed");

        Entity eD1Aft = gridCache.getGrid().getElementAt(dino1);
        Assert.that(eD1Aft == null, "Dino not destroyed");

        Entity eD2Aft = gridCache.getGrid().getElementAt(dino2);
        Assert.that(eD2Aft == null, "Dino not destroyed");

        Entity eD3Aft = gridCache.getGrid().getElementAt(dino3);
        Assert.that(eD3Aft == null, "Dino not destroyed");

        Entity eD4Aft = gridCache.getGrid().getElementAt(dino4);
        Assert.that(eD4Aft == null, "Dino not destroyed");


    }

    //* No need to worry about the dinosaurs - dinosaurs don't move;
    @Test
    public void dinosaurMovement(){
        testRestTemplate.postForEntity("/grid",null,String.class);
        Location loc = new Location(5,5);

        testRestTemplate.postForEntity("/dinosaur",loc,String.class);

        Movement movement = new Movement();
        movement.setRow(5);
        movement.setCol(5);
        movement.setMovementDirection(MovementDirection.FORWARD);

        ResponseEntity<String> respMove = testRestTemplate.postForEntity("/robot/move",movement,String.class);

        Assert.that(respMove.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong status code");

        Turn turn = new Turn();
        turn.setRow(5);
        turn.setCol(5);
        turn.setTurnDirection(TurnDirection.LEFT);

        ResponseEntity<String> respTurn = testRestTemplate.postForEntity("/robot/turn",turn,String.class);

        Assert.that(respTurn.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong status code");
    }

    //* Display the simulation's current state;
    @Test
    public void currentState(){
        testRestTemplate.postForEntity("/grid",null,String.class);

        ResponseEntity<String> grid = testRestTemplate.getForEntity("/grid",String.class);
        Assert.that(grid != null,"grid not ok");
        assert grid != null;
        String[] splitted = Objects.requireNonNull(grid.getBody()).split("\n");
        int rows = splitted.length;
        int cols = splitted[0].substring(1,splitted[0].length()).split("\\|").length;

        Assert.that(rows == 50,"Grid rows not ok");
        Assert.that(cols == 50,"Grid cols not ok");


    }

    //* Two or more entities (robots or dinosaurs) cannot occupy the same position;
    @Test
    public void conflict(){
        testRestTemplate.postForEntity("/grid",null,String.class);

        RobotLocation robotLocation = new RobotLocation();
        robotLocation.setRow(0);
        robotLocation.setCol(0);
        robotLocation.setDirection(Direction.UP);

        //Adding first robot
        testRestTemplate.postForEntity("/robot",robotLocation,String.class);

        //Adding first dinosaur
        Location dinoLoc = new Location(1,0);
        testRestTemplate.postForEntity("/dinosaur",dinoLoc,String.class);

        //Adding dinosaur in robot position
        dinoLoc = new Location(0,0);
        ResponseEntity<String> firstDinoConflict = testRestTemplate.postForEntity("/dinosaur",dinoLoc,String.class);

        //Adding robot in robot position
        ResponseEntity<String> firstRobotConflict = testRestTemplate.postForEntity("/robot",robotLocation,String.class);

        //Adding robot in dinosaur position
        robotLocation.setRow(1);
        ResponseEntity<String> secondRobotConflict = testRestTemplate.postForEntity("/robot",robotLocation,String.class);

        //Adding dinosaur in dinosaur position
        dinoLoc = new Location(1,0);
        ResponseEntity<String> secondDinoConflict = testRestTemplate.postForEntity("/dinosaur",dinoLoc,String.class);


        Assert.that(firstDinoConflict.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong code for 1st");
        Assert.that(firstRobotConflict.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong code for 2nd");
        Assert.that(secondDinoConflict.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong code for 3rd");
        Assert.that(secondRobotConflict.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong code for 4th");
    }

    @Test
    public void outOfBounds(){
        testRestTemplate.postForEntity("/grid",null,String.class);

        RobotLocation upper = new RobotLocation();
        upper.setRow(0);
        upper.setCol(0);
        upper.setDirection(Direction.UP);

        RobotLocation lower = new RobotLocation();
        lower.setRow(49);
        lower.setCol(0);
        lower.setDirection(Direction.DOWN);

        RobotLocation left = new RobotLocation();
        left.setRow(49);
        left.setCol(0);
        left.setDirection(Direction.LEFT);

        RobotLocation right = new RobotLocation();
        right.setRow(0);
        right.setCol(49);
        right.setDirection(Direction.RIGHT);

        testRestTemplate.postForEntity("/robot",upper,String.class);
        testRestTemplate.postForEntity("/robot",lower,String.class);
        testRestTemplate.postForEntity("/robot",left,String.class);
        testRestTemplate.postForEntity("/robot",right,String.class);

        Movement moveUp = new Movement();
        moveUp.setMovementDirection(MovementDirection.FORWARD);
        moveUp.setRow(upper.getRow());
        moveUp.setCol(upper.getCol());

        Movement moveDown = new Movement();
        moveDown.setMovementDirection(MovementDirection.FORWARD);
        moveDown.setRow(lower.getRow());
        moveDown.setCol(lower.getCol());

        Movement moveRight = new Movement();
        moveRight.setMovementDirection(MovementDirection.FORWARD);
        moveRight.setRow(right.getRow());
        moveRight.setCol(right.getCol());

        Movement moveLeft = new Movement();
        moveLeft.setMovementDirection(MovementDirection.FORWARD);
        moveLeft.setRow(left.getRow());
        moveLeft.setCol(left.getCol());

        ResponseEntity<String> respUp = testRestTemplate.postForEntity("/robot/move",moveUp,String.class);
        ResponseEntity<String> respDown = testRestTemplate.postForEntity("/robot/move",moveDown,String.class);
        ResponseEntity<String> respLeft = testRestTemplate.postForEntity("/robot/move",moveLeft,String.class);
        ResponseEntity<String> respRight = testRestTemplate.postForEntity("/robot/move",moveRight,String.class);

        Assert.that(respUp.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong status code 1st");
        Assert.that(respDown.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong status code 2nd");
        Assert.that(respLeft.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong status code 3rd");
        Assert.that(respRight.getStatusCode().equals(HttpStatus.FORBIDDEN),"Wrong status code 4th");



    }

}
