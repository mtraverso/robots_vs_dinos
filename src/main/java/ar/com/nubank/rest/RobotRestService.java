package ar.com.nubank.rest;

import ar.com.nubank.exceptions.CannotAddElementException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.GridNotInitializedException;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.model.grid.RobotLocation;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.ResponseErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/robot")
public class RobotRestService {

    private final GameService gameService;




    @Autowired
    public RobotRestService(GameService gameService) {
        this.gameService = gameService;

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create( RobotLocation value)  {


        try {
            gameService.addRobot(new Location(value.getRow(),value.getCol()), value.getDirection());
        } catch (ElementAlreadyPresentException e) {
            return ResponseErrors.elementAlreadyPresent(e.getRow(),e.getCol());
        } catch (GridNotInitializedException e) {
            return ResponseErrors.gridNotReady();
        } catch (CannotAddElementException e) {
            return ResponseErrors.cannotAddElement();
        }

        return Response.status(HttpStatus.CREATED.value()).build();
    }
}
