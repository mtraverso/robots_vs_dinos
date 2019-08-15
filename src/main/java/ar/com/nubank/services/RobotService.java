package ar.com.nubank.services;

import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.model.figures.Dinosaur;
import ar.com.nubank.model.figures.Figure;
import ar.com.nubank.model.figures.Robot;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.utils.GridCache;

import java.util.HashMap;
import java.util.Map;

import static ar.com.nubank.model.enums.Direction.*;
import static ar.com.nubank.model.enums.Direction.DOWN;

public class RobotService {

    private Map<Integer, Robot> robotsCache;

    private static RobotService instance;
    private DinosaurService dinosaurService;

    private Grid grid;

    private int robotIds = 0;


    public static RobotService instance() {
        if(instance == null){
            instance = new RobotService();
        }
        return instance;
    }

    private RobotService(){
        GridCache gridCache = GridCache.instance();
        this.grid = gridCache.getGrid();
        this.robotsCache = new HashMap<Integer, Robot>();
        this.dinosaurService = DinosaurService.instance();
    }

    public void addRobot(int row, int col, int direction) throws ElementAlreadyPresentException {
        if(!grid.hasElementAt(row,col)){
            int id = robotIds++;
            Robot r = new Robot(id, row,col,direction);
            grid.setElementAt(r,row,col);
            robotsCache.put(id,r);
        }else{
            throw new ElementAlreadyPresentException(row,col);
        }
    }

    public void turnLeft(int idRobot) throws RobotNotFoundException {
        checkExistingRobot(idRobot);
        robotsCache.get(idRobot).turnLeft();
    }

    public void turnRight(int idRobot) throws RobotNotFoundException {
        checkExistingRobot(idRobot);
        robotsCache.get(idRobot).turnRight();
    }

    private void checkExistingRobot(int idRobot) throws RobotNotFoundException {
        if (robotsCache.get(idRobot) == null)
            throw new RobotNotFoundException();
    }

    public void moveForward(int id) throws RobotNotFoundException, CannotMoveRobotException,ElementAlreadyPresentException {
        checkExistingRobot(id);
        Robot r = robotsCache.get(id);
        switch (r.getFacingDirection()){
            case UP:
                moveRobotUp(id);
                break;
            case RIGHT:
                moveRobotRight(id);
                break;
            case LEFT:
                moveRobotLeft(id);
                break;
            case DOWN:
                moveRobotDown(id);
                break;
        }

    }

    public void moveBackward(int id) throws RobotNotFoundException, CannotMoveRobotException,ElementAlreadyPresentException {
        checkExistingRobot(id);
        Robot r = robotsCache.get(id);
        switch (r.getFacingDirection()){
            case UP:
                moveRobotDown(id);
                break;
            case RIGHT:
                moveRobotLeft(id);
                break;
            case LEFT:
                moveRobotRight(id);
                break;
            case DOWN:
                moveRobotUp(id);
                break;
        }

    }

    public void attack(int id) {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();

        Figure up = null, down = null, left = null, right = null;

        dinosaurService.killDinosaur(row-1,col);
        dinosaurService.killDinosaur(row+1,col);
        dinosaurService.killDinosaur(row,col-1);
        dinosaurService.killDinosaur(row,col+1);

    }

    private void moveRobotUp(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();
        if((row-1) < 0 ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid.hasElementAt(row-1,col)){
            throw  new ElementAlreadyPresentException(row-1,col);
        }

        r.setRow(row-1);
        grid.setElementAt(null,row,col);
        grid.setElementAt(r,row-1,col);
    }

    private void moveRobotRight(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();
        if((col+1) >= grid.width() ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid.hasElementAt(row,col+1)){
            throw  new ElementAlreadyPresentException(row,col+1);
        }

        r.setCol(col+1);
        grid.setElementAt(null,row,col);
        grid.setElementAt(r,row,col+1);
    }

    private void moveRobotLeft(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();

        if((col-1) < 0 ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid.hasElementAt(row,col-1)){
            throw  new ElementAlreadyPresentException(row,col-1);
        }

        r.setCol(col-1);
        grid.setElementAt(null,row,col);
        grid.setElementAt(r,row,col-1);
    }

    private void moveRobotDown(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();

        if((row+1) >= grid.height() ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid.hasElementAt(row+1,col) ){
            throw  new ElementAlreadyPresentException(row+1,col);
        }

        r.setRow(row+1);
        grid.setElementAt(null,row,col);
        grid.setElementAt(r,row+1,col);
    }


}
