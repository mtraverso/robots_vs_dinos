package ar.com.nubank.services;

import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.utils.GridCache;

public class RobotService {

    private GridCache gridCache;

    private static RobotService instance;


    public static RobotService instance() {
        if(instance == null){
            instance = new RobotService();
        }
        return instance;
    }

    private RobotService(){
        gridCache  = GridCache.instance();
    }

    public void addRobot(int row, int col, int facingDirection) throws ElementAlreadyPresentException {
        gridCache.getGrid().addRobot(row,col,facingDirection);
    }

    public void turnLeft(int id) throws RobotNotFoundException {
        gridCache.getGrid().turnLeft(id);
    }

    public void turnRight(int id) throws RobotNotFoundException {
        gridCache.getGrid().turnRight(id);
    }

    public void moveForward(int id) throws RobotNotFoundException, CannotMoveRobotException,ElementAlreadyPresentException {
        gridCache.getGrid().moveForward(id);
    }

    public void moveBackward(int id) throws RobotNotFoundException, CannotMoveRobotException,ElementAlreadyPresentException {
        gridCache.getGrid().moveBackward(id);
    }

    public void attack(int id){
        gridCache.getGrid().attack(id);
    }
}
