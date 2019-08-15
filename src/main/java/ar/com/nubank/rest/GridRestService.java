package ar.com.nubank.rest;

import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Controller
@Path("/grid")
public class GridRestService {

    @Autowired
    private GridService gridService;


    @POST
    public Response create(){
        if(gridService.getGridStatusOk()){
           return Response.status(400).entity("Grid already initialized").build();
        }
        gridService.createGrid(50,50);
        return Response.ok().build();
    }

    @GET
    public Response get(){
        return Response.ok(gridService.printGrid()).build();

    }
}
