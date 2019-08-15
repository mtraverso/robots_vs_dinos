package ar.com.nubank.rest;

import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.model.figures.Dinosaur;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.services.DinosaurService;
import ar.com.nubank.services.GridService;
import ar.com.nubank.utils.ResponseErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Controller
@Path("/dinosaur")
public class DinosaurRestService {
    @Autowired
    private DinosaurService dinosaurService;

    @Autowired
    private GridService gridService;

    @POST
    public Response addDinosaur(Location location){
        if(!gridService.getGridStatusOk()){
            return Response.status(500).entity("Grid not initialized").build();
        }
        try {
            dinosaurService.addDinosaur(location.getRow(),location.getCol());
        } catch (ElementAlreadyPresentException e) {
            return ResponseErrors.elementAlreadyPresent(e.getRow(),e.getCol());
        }
        return Response.ok().build();
    }
}
