package ar.com.nubank.exceptions;

public class ElementNotMovableException extends Throwable {
    private final int col;
    private final int row;

    public ElementNotMovableException(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
