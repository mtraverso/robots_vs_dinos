package ar.com.nubank.utils;

import ar.com.nubank.model.grid.Grid;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service("gridCache")

public class GridCache {

    private Grid grid;


    private GridCache(){}



    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid(){
        return this.grid;
    }
}
