package jUnit;

import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.*;

class VirtualItemTest {

    private final VirtualItem virtualItem = new VirtualItem();

    @Test
    void toStringTest() {
        double sizeUnderTheTest = 8500.0;
        virtualItem.setSizeOnDisk(sizeUnderTheTest);
        assertEquals(virtualItem.toString(), "Class: class shop.VirtualItem; Name: null; Price: 0.0; Size on disk: 8500.0");
    }
}