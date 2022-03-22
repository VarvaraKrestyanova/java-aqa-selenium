package jUnit;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private static final Faker faker = new Faker();
    public static String cartForTestName = faker.business().creditCardNumber();
    private final Cart cart = new Cart(cartForTestName);
    private final RealItem car = new RealItem();
    private final VirtualItem disk = new VirtualItem();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void beforeTest() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560.0);
        cart.addRealItem(car);

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
        double expectedTotal = (disk.getPrice() + disk.getPrice() * 0.2);
        assertAll("Cart With Removed Item",
                () -> assertEquals("Class: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0\r\n", output.toString()),
                () -> assertEquals(expectedTotal, cart.getTotalPrice())
        );
    }

    @Test
    void showItemsTest() {
        System.setOut(new PrintStream(output));
        cart.showItems();
        double expectedTotal = (disk.getPrice() + disk.getPrice() * 0.2) + (car.getPrice() + car.getPrice() * 0.2);
        assertAll("Cart Items",
                () -> assertEquals("Class: class shop.RealItem; Name: Audi; Price: 32026.9; Weight: 1560.0\r\nClass: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0\r\n", output.toString()),
                () -> assertEquals(expectedTotal, cart.getTotalPrice())
        );
    }



}