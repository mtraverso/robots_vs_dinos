package ar.com.nubank.integration;

import ar.com.nubank.integration.base.BaseIntegrationTest;
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
public class GridIntegrationTest extends BaseIntegrationTest {

    @Autowired
    GridService gridService;

    @Autowired
    GridCache gridCache;

    @After
    public void tearDown(){
        gridCache.setGrid(null);
    }

    @Test

    public void testA_PrintGrid(){


        ResponseEntity<String> resp = testRestTemplate.getForEntity("/grid",String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.NOT_FOUND), "Grid not ok");
        Assert.that(resp.getBody() != null, "Response body not ok");
    }

    @Test
    public void testB_CreateGrid(){

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");

        Assert.that(gridService.getGridStatusOk(),"Grid not initialized");

    }

    @Test
    public void testC_CreateGrid2(){

        ResponseEntity<String> resp = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.CREATED),"Error code different");


        ResponseEntity<String> resp2 = testRestTemplate.postForEntity("/grid",null, String.class);
        Assert.that(resp2.getStatusCode().equals(HttpStatus.CONFLICT),"Error code different");

    }

    @Test

    public void testD_PrintGrid(){


        ResponseEntity<String> resp = testRestTemplate.getForEntity("/grid",String.class);
        Assert.that(resp.getStatusCode().equals(HttpStatus.OK), "Grid not ok");
        Assert.that(resp.getBody() != null, "Response body not ok");
    }

}
