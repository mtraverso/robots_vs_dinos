package ar.com.nubank.rest;

import ar.com.nubank.exceptions.*;
import ar.com.nubank.model.enums.Movement;
import ar.com.nubank.model.enums.Turn;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.model.grid.RobotLocation;
import ar.com.nubank.model.grid.RobotMovement;
import ar.com.nubank.model.grid.RobotTurn;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.ResponseErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/move")
    public Response move( RobotMovement value)  {


        try {
            if(value.getMovement().equals(Movement.FORWARD)){
                gameService.moveForward(value.getRow(),value.getCol());
            }else{
                gameService.moveBackward(value.getRow(),value.getCol());
            }

        } catch (ElementAlreadyPresentException e) {
            return ResponseErrors.elementAlreadyPresent(e.getRow(), e.getCol());
        } catch (NoElementFoundInPosition noElementFoundInPosition) {
            return ResponseErrors.elementNotFoundInPosition(noElementFoundInPosition.getRow(),noElementFoundInPosition.getCol());
        } catch (CannotClearElementAtPosition cannotClearElementAtPosition) {
            return ResponseErrors.cannotClearElement();
        } catch (CannotMoveElementException | ElementNotMovableException e) {
            return ResponseErrors.cannotMoveElement();
        }

        return Response.status(HttpStatus.CREATED.value()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/turn")
    public Response turn( RobotTurn value)  {


        try {
            if (value.getTurn().equals(Turn.LEFT)) {
                gameService.turnLeft(value.getRow(), value.getCol());
            } else {
                gameService.turnRight(value.getRow(), value.getCol());
            }

        } catch (NoElementFoundInPosition noElementFoundInPosition) {
            return ResponseErrors.elementNotFoundInPosition(noElementFoundInPosition.getRow(),noElementFoundInPosition.getCol());
        } catch (ElementNotMovableException e) {
            return ResponseErrors.cannotMoveElement();
        }

        return Response.status(HttpStatus.OK.value()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/attack")
    public Response attack( Location value)  {


        try {

                gameService.attack(value.getRow(),value.getCol());


        } catch (NoElementFoundInPosition noElementFoundInPosition) {
            return ResponseErrors.elementNotFoundInPosition(noElementFoundInPosition.getRow(),noElementFoundInPosition.getCol());
        } catch (CannotClearElementAtPosition cannotClearElementAtPosition) {
            return ResponseErrors.cannotClearElement();
        } catch (ElementNotMovableException e) {
            return ResponseErrors.cannotMoveElement();
        }

        return Response.status(HttpStatus.CREATED.value()).build();
    }
}
