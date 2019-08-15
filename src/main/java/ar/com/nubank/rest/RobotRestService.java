package ar.com.nubank.rest;

import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.model.grid.RobotLocation;
import ar.com.nubank.services.RobotService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/robot")
public class RobotRestService {
    private RobotService robotService = RobotService.instance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create( RobotLocation value) throws ElementAlreadyPresentException {
        if(value.getDirection()>3){
            return Response.status(400).build();
        }
        robotService.addRobot(value.getRow(),value.getCol(),value.getDirection());
        System.out.println(value);
        return Response.ok().build();
    }
}
