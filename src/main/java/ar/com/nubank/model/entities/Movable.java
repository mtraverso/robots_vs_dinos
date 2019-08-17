package ar.com.nubank.model.entities;

import ar.com.nubank.exceptions.*;

public interface Movable  {
    void turnLeft();
    void turnRight();
    void moveForward() throws CannotMoveElementException, NoElementFoundInPosition, ElementNotMovableException, ElementAlreadyPresentException, CannotClearElementAtPosition;
    void moveBackward() throws CannotMoveElementException, NoElementFoundInPosition, ElementNotMovableException, ElementAlreadyPresentException, CannotClearElementAtPosition;
}
