package ar.com.nubank.unit.grid;

import ar.com.nubank.model.grid.Grid;
import ar.com.nubank.services.GridService;
import ar.com.nubank.utils.GridCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import sun.jvm.hotspot.utilities.Assert;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestGridService {

    @InjectMocks
    GridService gridService;

    @Mock
    GridCache gridCache;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateGrid(){




        doAnswer((Answer) invocation -> {
            Object arg0 = invocation.getArgument(0);


            Assert.that( arg0 instanceof Grid, "Not grid set");
            Grid g = (Grid)arg0;
            Assert.that(g.height() == 50, "Height ok");
            Assert.that(g.width() == 50, "Width ok");

            return null;
        }).when(gridCache).setGrid(any(Grid.class));

        gridService.createGrid(50,50);



    }

    @Test
    public void testGridOk(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        Assert.that(gridService.getGridStatusOk(), "Grid not created");
    }

    @Test
    public void testGridNOk(){
        when(gridCache.getGrid()).thenReturn(null);
        Assert.that(!gridService.getGridStatusOk(), "Grid created");
    }

    @Test
    public void testPrintGrid(){
        when(gridCache.getGrid()).thenReturn(new Grid(50,50));
        Assert.that(gridService.printGrid() != null, "Grid not printed" );
    }
}
