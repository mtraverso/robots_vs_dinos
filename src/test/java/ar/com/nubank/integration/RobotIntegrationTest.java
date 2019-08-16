package ar.com.nubank.integration;

import ar.com.nubank.integration.base.BaseIntegrationTest;
import ar.com.nubank.model.enums.Direction;
import ar.com.nubank.model.grid.RobotLocation;
import ar.com.nubank.services.GridService;
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
    GridService gridService;

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
        Assert.that(resp.getStatusCode().equals(HttpStatus.NOT_FOUND), "Grid not ok");
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
        Assert.that(resp3.getStatusCode().equals(HttpStatus.CONFLICT),"Error code different");

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

    //Test adding a robot out of bounds of grid.
    @Test
    public void testE_CreateGridAddRobotIllegalFacingDirection(){
        RobotLocation rl = new RobotLocation();
        rl.setRow(1);
        rl.setCol(1);
        rl.setDirection(6);

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/robot",rl,String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.BAD_REQUEST),"Error code different");


    }


}
