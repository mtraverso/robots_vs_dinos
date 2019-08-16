package ar.com.nubank.rest;

import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
           return Response.status(HttpStatus.CONFLICT.value()).entity("Grid already initialized").build();
        }
        gridService.createGrid(50,50);
        return Response.status(HttpStatus.CREATED.value()).build();
    }

    @GET
    public Response get(){
        if(!gridService.getGridStatusOk()){
            return Response.status(HttpStatus.NOT_FOUND.value()).entity("Grid not initialized").build();
        }
        return Response.ok(gridService.printGrid()).build();

    }
}
