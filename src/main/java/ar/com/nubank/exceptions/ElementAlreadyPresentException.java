package ar.com.nubank.exceptions;

public class ElementAlreadyPresentException extends Exception {
    public ElementAlreadyPresentException(int x, int y){
        super("Element present at "+x+","+y);
    }
}
