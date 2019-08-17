package ar.com.nubank.rest;

import ar.com.nubank.services.GameService;
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
    public GridRestService(GameService gameService) {
        this.gameService = gameService;
    }



    private final GameService gameService;


    @POST
    public Response create(){
        if(gameService.getGridStatusOk()){
           return Response.status(HttpStatus.CONFLICT.value()).entity("Grid already initialized").build();
        }
        gameService.createGrid(50,50);
        return Response.status(HttpStatus.CREATED.value()).build();
    }

    @GET
    public Response get(){
        if(!gameService.getGridStatusOk()){
            return Response.status(HttpStatus.NOT_FOUND.value()).entity("Grid not initialized").build();
        }
        return Response.ok(gameService.printGrid()).build();

    }
}
