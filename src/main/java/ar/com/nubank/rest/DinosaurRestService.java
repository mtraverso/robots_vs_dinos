package ar.com.nubank.rest;

import ar.com.nubank.exceptions.CannotAddElementException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.GridNotInitializedException;
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
    private final DinosaurService dinosaurService;

    @Autowired
    private final GridService gridService;

    public DinosaurRestService(DinosaurService dinosaurService, GridService gridService) {
        this.dinosaurService = dinosaurService;
        this.gridService = gridService;
    }

    @POST
    public Response addDinosaur(Location location) {

        try {
            dinosaurService.addDinosaur(location.getRow(), location.getCol());
        } catch (ElementAlreadyPresentException e) {
            return ResponseErrors.elementAlreadyPresent(e.getRow(), e.getCol());
        } catch (GridNotInitializedException e) {
            return ResponseErrors.gridNotReady();
        } catch (CannotAddElementException e) {
            return ResponseErrors.cannotAddElement();
        }
        return Response.ok().build();
    }
}
