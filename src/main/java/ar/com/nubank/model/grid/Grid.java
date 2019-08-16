package ar.com.nubank.model.grid;

import ar.com.nubank.model.figures.Entity;

public class Grid {
    private final Entity[][] grid;

    public Grid(int rows, int cols) {
        grid = new Entity[rows][cols];
    }

    public Entity getElementAt(int row, int col) {
        return grid[row][col];
    }

    public boolean hasElementAt(int row, int col) {
        return grid[row][col] != null;
    }

    public void setElementAt(Entity f, int row, int col) {
        grid[row][col] = f;
    }

    public int width() {
        return grid[0].length;
    }

    public int height() {
        return grid.length;
    }

    public String printGrid() {
        StringBuilder buffer = new StringBuilder();
        for (Entity[] entities : grid) {
            for (Entity entity : entities) {
                buffer.append("|");
                if (entity != null)
                    buffer.append(" ").append(entity).append(" ");
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
