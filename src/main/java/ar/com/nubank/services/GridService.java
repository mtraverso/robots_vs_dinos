package ar.com.nubank.services;

import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.utils.GridCache;

public class GridService {
    private GridCache gridCache;
    private static GridService instance;


    public static GridService instance() {
        if(instance == null){
            instance = new GridService();
        }
        return instance;
    }

    private GridService(){
        gridCache = GridCache.instance();
    }

    public void createGrid(int x, int y){
        Grid grid = new Grid(x,y);
        gridCache.setGrid(grid);

    }

    public String printGrid(){
        return gridCache.getGrid().printGrid();
    }


}
