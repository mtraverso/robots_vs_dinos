package ar.com.nubank.model.grid;


import ar.com.nubank.model.enums.Turn;

public class RobotTurn extends Location {
    private Turn turn;

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
