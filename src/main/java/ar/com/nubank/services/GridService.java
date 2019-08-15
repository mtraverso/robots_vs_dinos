package ar.com.nubank.services;

import ar.com.nubank.exceptions.CannotMoveRobotException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.RobotNotFoundException;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.utils.GridCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service

public class GridService {
    @Autowired
    private GridCache gridCache;

    public GridService(){

    }

    public void createGrid(int x, int y){
        Grid grid = new Grid(x,y);
        gridCache.setGrid(grid);

    }

    public String printGrid(){
        return gridCache.getGrid().printGrid();
    }


    public boolean getGridStatusOk() {
        return gridCache.getGrid() != null;
    }
}
