package ar.com.nubank.rest;

import ar.com.nubank.model.figures.Dinosaur;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.services.DinosaurService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/dinosaur")
public class DinosaurRestService {
    private DinosaurService dinosaurService = DinosaurService.instance();

    @POST
    public Response addDinosaur(Location location){
        return Response.ok().build();
    }
}
