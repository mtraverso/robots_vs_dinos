package ar.com.nubank.model.grid;

import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.model.figures.Dinosaur;
import ar.com.nubank.model.figures.Figure;
import ar.com.nubank.model.figures.Robot;


import java.util.HashMap;
import java.util.Map;

import static ar.com.nubank.model.enums.Direction.*;

public class Grid {
    private Figure[][] grid;

    private int robotIds = 0;
    private int dinosaurIds = 0;

    private Map<Integer, Robot> robotsCache;
    private Map<Integer, Dinosaur> dinosaurCache;

    public Grid(int rows, int cols) {
        grid = new Figure[rows][cols];
        robotsCache = new HashMap<Integer, Robot>();
        dinosaurCache = new HashMap<Integer, Dinosaur>();
    }

    public void addRobot(int row, int col, int direction) throws ElementAlreadyPresentException {
        if(grid[row][col] == null){
            int id = robotIds++;
            Robot r = new Robot(id, row,col,direction);
            grid[row][col] = r;
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

        if (row > 0) {
            up = grid[row-1][col];
            if(up instanceof Dinosaur){
                System.out.println("Dinosaur at "+(row-1)+","+col+" destroyed");
                grid[row-1][col] = null;
            }
        }
        if (row <= grid.length) {
            down = grid[row+1][col];
            if(down instanceof Dinosaur){
                System.out.println("Dinosaur at "+(row+1)+","+col+" destroyed");
                grid[row+1][col] = null;
            }
        }
        if (col > 0) {
            left = grid[row][col-1];
            if(left instanceof Dinosaur){
                System.out.println("Dinosaur at "+(row)+","+(col-1)+" destroyed");
                grid[row][col-1] = null;
            }
        }
        if (col <= grid[0].length) {
            right = grid[row][col+1];
            if(right instanceof Dinosaur){
                System.out.println("Dinosaur at "+(row)+","+(col+1)+" destroyed");
                grid[row][col+1] = null;
            }
        }



        System.out.println();

    }

    private void moveRobotUp(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();
        if((row-1) < 0 ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid[row-1][col] != null ){
            throw  new ElementAlreadyPresentException(row-1,col);
        }

        r.setRow(row-1);
        grid[row][col] = null;
        grid[row-1][col] = r;
    }

    private void moveRobotRight(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();
        if((col+1) >= grid[0].length ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid[row][col+1] != null ){
            throw  new ElementAlreadyPresentException(row,col+1);
        }

        r.setCol(col+1);
        grid[row][col] = null;
        grid[row][col+1] = r;
    }

    private void moveRobotLeft(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();

        if((col-1) < 0 ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid[row][col-1] != null ){
            throw  new ElementAlreadyPresentException(row,col-1);
        }

        r.setCol(col-1);
        grid[row][col] = null;
        grid[row][col-1] = r;
    }

    private void moveRobotDown(int id) throws CannotMoveRobotException, ElementAlreadyPresentException {
        Robot r = robotsCache.get(id);
        int row = r.getRow();
        int col = r.getCol();

        if((row+1) >= grid.length ){
            throw new CannotMoveRobotException("Out of bounds");
        }
        if(grid[row+1][col] != null ){
            throw  new ElementAlreadyPresentException(row+1,col);
        }

        r.setRow(row+1);
        grid[row][col] = null;
        grid[row+1][col] = r;
    }


    public void addDinosaur(int row, int col) throws ElementAlreadyPresentException {
        if(grid[row][col] == null){
            int id = dinosaurIds++;
            Dinosaur d = new Dinosaur(id, row,col);
            grid[row][col] = d;
            dinosaurCache.put(id,d);
        }else{
            throw new ElementAlreadyPresentException(row,col);
        }
    }


    public String printGrid()
    {
        StringBuilder buffer = new StringBuilder();
        for (Figure[] figures : grid) {
            for (Figure figure : figures) {
                buffer.append("|");
                if (figure != null)
                    buffer.append(" ").append(figure).append(" ");
                else
                    buffer.append("   ");
            }
            buffer.append("|");
            buffer.append("\n");
        }
        buffer.append("\n");
        return buffer.toString();

    }



}
