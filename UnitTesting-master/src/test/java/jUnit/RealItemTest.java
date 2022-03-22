package jUnit;

import org.junit.jupiter.api.Test;
import shop.RealItem;
import static org.junit.jupiter.api.Assertions.*;

class RealItemTest {

    private final RealItem car = new RealItem();

    @Test
    void toStringTest() {
        double weightUnderTheTest = 1400.5;
        car.setWeight(weightUnderTheTest);
        assertEquals(car.toString(), "Class: class shop.RealItem; Name: null; Price: 0.0; Weight: 1400.5");
    }

}