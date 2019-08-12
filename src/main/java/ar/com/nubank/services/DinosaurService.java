package ar.com.nubank.services;

import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.utils.GridCache;

public class DinosaurService {
    private static DinosaurService instance;
    private GridCache gridCache;

    private DinosaurService(){
        gridCache = GridCache.instance();
    }

    public static DinosaurService instance(){
        if(instance == null){
            instance = new DinosaurService();
        }
        return instance;
    }

    public void addDinosaur(int x, int y) throws ElementAlreadyPresentException {
        gridCache.getGrid().addDinosaur(x,y);
    }

}
