package jUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart cart = new Cart("cartForTest");
    RealItem car = new RealItem();
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void beforeTest() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560.0);
        cart.addRealItem(car);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);
        cart.addVirtualItem(disk);
    }


    @Test
    void deleteRealItemTest() {
        cart.deleteRealItem(car);
        System.setOut(new PrintStream(output));
        cart.showItems();
        assertEquals("Class: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0\r\n", output.toString());

    }

    @Test
    void showItemsTest() {
        System.setOut(new PrintStream(output));
        cart.showItems();
        assertEquals("Class: class shop.RealItem; Name: Audi; Price: 32026.9; Weight: 1560.0\r\nClass: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0\r\n", output.toString());
    }



}