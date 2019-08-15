package ar.com.nubank.rest;

import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.GridService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/grid")
public class GridRestService {

    private GridService gridService = GridService.instance();


    @POST
    public Response create(){
        gridService.createGrid(50,50);
        return Response.ok().build();
    }

    @GET
    public Response get(){
        return Response.ok(gridService.printGrid()).build();

    }
}
