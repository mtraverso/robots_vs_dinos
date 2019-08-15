package ar.com.nubank.model.grid;

import ar.com.nubank.model.figures.Figure;

public class Grid {
    private Figure[][] grid;

    public Grid(int rows, int cols) {
        grid = new Figure[rows][cols];
    }

    public Figure getElementAt(int row, int col){
        return grid[row][col];
    }

    public boolean hasElementAt(int row, int col){
        return grid[row][col] != null;
    }

    public void setElementAt(Figure f, int row, int col){
        grid[row][col] = f;
    }

    public int width(){
        return grid[0].length;
    }

    public int height(){
        return grid.length;
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
