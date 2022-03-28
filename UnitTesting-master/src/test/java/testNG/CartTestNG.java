package testNG;

import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;

public class CartTestNG {

    private final Faker faker = new Faker();
    private String cartForTestName = faker.business().creditCardNumber();
    private final Cart cart = new Cart(cartForTestName);
    private final RealItem car = new RealItem();
    private final VirtualItem disk = new VirtualItem();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
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
    public void testDeleteVirtualItem() throws IOException {
        cart.deleteVirtualItem(disk);
        System.setOut(new PrintStream(output));
        cart.showItems();
        double expectedTotal = (car.getPrice() + car.getPrice() * 0.2);
        softAssert.assertEquals(cart.getTotalPrice(), expectedTotal);
        softAssert.assertEquals(output.toString(), "Class: class shop.RealItem; Name: Audi; Price: 32026.9; Weight: 1560.0\r\n");
    }

    @Test
    public void testShowItems() {
        SoftAssert softAssert = new SoftAssert();
        double expectedTotal = (disk.getPrice() + disk.getPrice() * 0.2) + (car.getPrice() + car.getPrice() * 0.2);
        System.setOut(new PrintStream(output));
        cart.showItems();
        softAssert.assertEquals(output.toString(), "Class: class shop.RealItem; Name: Audi; Price: 32026.9; Weight: 1560.0\r\nClass: class shop.VirtualItem; Name: Windows; Price: 11.0; Size on disk: 20000.0\r\n");
        softAssert.assertEquals(cart.getTotalPrice(), expectedTotal);
    }

    @AfterMethod
    public void afterTest() {
        softAssert.assertAll();
    }
}