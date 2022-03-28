package testNG;

import org.testng.annotations.Test;
import shop.VirtualItem;

import static org.testng.Assert.*;

public class VirtualItemTestNG {

    private final VirtualItem virtualItem = new VirtualItem();

    @Test(groups = {"items"})
    public void testToString() {
        double sizeUnderTheTest = 8500.0;
        virtualItem.setSizeOnDisk(sizeUnderTheTest);
        assertEquals(virtualItem.toString(), "Class: class shop.VirtualItem; Name: null; Price: 0.0; Size on disk: 8500.0");
    }
}