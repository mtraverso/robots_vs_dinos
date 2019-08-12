package ar.com.nubank.utils;

import ar.com.nubank.model.grid.Grid;

public class GridCache {

    private Grid grid;
    private static GridCache instance;

    private GridCache(){}

    public static GridCache instance(){
        if(instance == null){
            instance = new GridCache();
        }
        return instance;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid(){
        return this.grid;
    }
}
