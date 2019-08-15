package ar.com.nubank.services;

import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.model.figures.Dinosaur;
import ar.com.nubank.model.figures.Figure;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.utils.GridCache;

import java.util.HashMap;
import java.util.Map;

public class DinosaurService {
    private static DinosaurService instance;

    private Grid grid;

    private int dinosaurIds = 0;


    private Map<Integer, Dinosaur> dinosaurCache;

    private DinosaurService(){
        GridCache gridCache = GridCache.instance();
        this.grid = gridCache.getGrid();
        this.dinosaurCache = new HashMap<Integer, Dinosaur>();
    }

    public static DinosaurService instance(){
        if(instance == null){
            instance = new DinosaurService();
        }
        return instance;
    }

    public void addDinosaur(int row, int col) throws ElementAlreadyPresentException {
        if(!grid.hasElementAt(row,col)){
            int id = dinosaurIds++;
            Dinosaur d = new Dinosaur(id, row,col);
            grid.setElementAt(d,row,col);
            dinosaurCache.put(id,d);
        }else{
            throw new ElementAlreadyPresentException(row,col);
        }
    }

    public void killDinosaur(int row, int col) {

        if(row >=0 && row < grid.height() && col >= 0 && col < grid.width()) {
            Figure f = grid.getElementAt(row,col);
            if (f instanceof Dinosaur) {
                Dinosaur d = (Dinosaur) f;
                grid.setElementAt(null,row,col);
                dinosaurCache.remove(d.getId());

            }
        }
    }
}
