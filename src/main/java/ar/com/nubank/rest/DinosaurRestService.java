package ar.com.nubank.rest;

import ar.com.nubank.exceptions.CannotAddElementException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.GridNotInitializedException;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.ResponseErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Controller
@Path("/dinosaur")
public class DinosaurRestService {

    private final GameService gameService;


    @Autowired
    public DinosaurRestService(GameService gameService) {
        this.gameService = gameService;

    }

    @POST
    public Response addDinosaur(Location location) {

        try {
            gameService.addDinosaur(location.getRow(), location.getCol());
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
