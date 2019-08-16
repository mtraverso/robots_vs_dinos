package ar.com.nubank.services;

import ar.com.nubank.exceptions.CannotAddElementException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.GridNotInitializedException;
import ar.com.nubank.model.figures.Dinosaur;
import ar.com.nubank.model.figures.Entity;
import ar.com.nubank.utils.GridCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class DinosaurService {


    private int dinosaurIds = 0;

    @Autowired
    private GridCache gridCache;

    private Map<Integer, Dinosaur> dinosaurCache;

    public DinosaurService() {

        //this.grid = gridCache.getGrid();
        this.dinosaurCache = new HashMap<Integer, Dinosaur>();
    }


    public void addDinosaur(int row, int col) throws ElementAlreadyPresentException, GridNotInitializedException, CannotAddElementException {
        if (gridCache.getGrid() == null) {
            throw new GridNotInitializedException();
        }
        if (row < 0 || row >= gridCache.getGrid().height() || col < 0 || col >= gridCache.getGrid().width()) {
            throw new CannotAddElementException("Out of bounds");
        }
        if (!gridCache.getGrid().hasElementAt(row, col)) {
            int id = dinosaurIds++;
            Dinosaur d = new Dinosaur(id, row, col);
            gridCache.getGrid().setElementAt(d, row, col);
            dinosaurCache.put(id, d);
        } else {
            throw new ElementAlreadyPresentException(row, col);
        }
    }

    public void killDinosaur(int row, int col) {

        if (row >= 0 && row < gridCache.getGrid().height() && col >= 0 && col < gridCache.getGrid().width()) {
            Entity f = gridCache.getGrid().getElementAt(row, col);
            if (f instanceof Dinosaur) {
                Dinosaur d = (Dinosaur) f;
                gridCache.getGrid().setElementAt(null, row, col);
                dinosaurCache.remove(d.getId());

            }
        }
    }
}
