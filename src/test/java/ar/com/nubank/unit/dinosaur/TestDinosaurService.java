package ar.com.nubank.unit.dinosaur;

import ar.com.nubank.exceptions.CannotAddElementException;
import ar.com.nubank.exceptions.ElementAlreadyPresentException;
import ar.com.nubank.exceptions.GridNotInitializedException;
import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.model.grid.Location;
import ar.com.nubank.services.GameService;
import ar.com.nubank.utils.GridCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sun.jvm.hotspot.utilities.Assert;

import static org.mockito.Mockito.when;

public class TestDinosaurService {

    @Mock
    GridCache gridCache;

    @InjectMocks
    GameService gameService;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void addDinosaur(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));

        try {
            gameService.addDinosaur(0,0);
        } catch (ElementAlreadyPresentException | GridNotInitializedException | CannotAddElementException e) {
            Assert.that(false, "Exception thrown");
        }

        Assert.that(gridCache.getGrid().getElementAt(new Location(0,0)) != null, "Dinosaur not present");
    }



}
