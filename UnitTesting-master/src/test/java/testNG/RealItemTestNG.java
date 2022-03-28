package testNG;

import org.testng.annotations.Test;
import shop.RealItem;

import static org.testng.Assert.*;

public class RealItemTestNG {

    private final RealItem car = new RealItem();

    @Test(groups = {"items"})
    public void testToString() {
        double weightUnderTheTest = 1400.5;
        car.setWeight(weightUnderTheTest);
        assertEquals(car.toString(), "Class: class shop.RealItem; Name: null; Price: 0.0; Weight: 1400.5");
    }
}