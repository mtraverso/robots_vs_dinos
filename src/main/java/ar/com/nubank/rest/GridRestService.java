package ar.com.nubank.rest;

import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.GridService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/grid")
public class GridRestService {

    private GridService gridService = GridService.instance();


    @POST
    public String create(){
        gridService.createGrid(50,50);
        return "OK";
    }

    @GET
    public String get(){
        return gridService.printGrid();
    }

    @GET
    public String getImage(){
        return gridService.printGrid();
    }
}
