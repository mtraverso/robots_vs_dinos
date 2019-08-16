package ar.com.nubank.unit.dinosaur;

import ar.com.nubank.model.figures.Dinosaur;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

public class TestDinosaur {

    @Test
    public void testToString(){
        Dinosaur d = new Dinosaur(0,0,0);
        Assert.that(d.toString().equals("D"),"NOK");
    }

}
