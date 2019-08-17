package ar.com.nubank.exceptions;

public class NoElementFoundInPosition extends Throwable {
    private final int col;
    private final int row;

    public NoElementFoundInPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
