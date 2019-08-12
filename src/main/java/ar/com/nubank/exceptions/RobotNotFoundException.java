package ar.com.nubank.exceptions;

public class RobotNotFoundException extends Exception {
    public RobotNotFoundException(String message){
        super(message);
    }

    public RobotNotFoundException() {
        super();
    }
}
