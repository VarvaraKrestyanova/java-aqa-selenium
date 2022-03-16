package jUnit;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    JsonParser jsonParser = new JsonParser();
    SoftAssert softAssert = new SoftAssert();

    Cart cart = new Cart("testCart");
    String cartUnderTheTestName = "andrew-cart";

    @BeforeEach
    public void beforeTest() {
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);
        cart.addRealItem(car);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);
        cart.addVirtualItem(disk);
    }

    @Test
    void writeToFileTest() {
        jsonParser.writeToFile(cart);
        softAssert.assertTrue(new File("src/main/resources/" + cart.getCartName() + ".json").exists());
        softAssert.assertTrue(new File("src/main/resources/" + cart.getCartName() + ".json").isFile());
    }

    @Test
    void readFromFileTest() {
        Cart cartUnderTheTest = jsonParser.readFromFile(new File("src/main/resources/" + cartUnderTheTestName + ".json"));
        softAssert.assertEquals(cart.getTotalPrice(), cartUnderTheTest.getTotalPrice());
        softAssert.assertEquals(cartUnderTheTestName, cartUnderTheTest.getCartName());
    }

    @Test
    public void expectedExceptionTest() {

        List<String> wrongFileNames = new ArrayList<>();
        wrongFileNames.add("wrongName");
        wrongFileNames.add("andrew-cat");
        wrongFileNames.add("eugen-cartjson");
        wrongFileNames.add(" ");
        wrongFileNames.add("json");

        for (String wrongFileName : wrongFileNames) {
            Exception exception = assertThrows(NoSuchFileException.class, () -> {
                jsonParser.readFromFile(new File("src/main/resources/" + wrongFileName));
            });
            softAssert.assertTrue(exception.getMessage().contentEquals("File src\\main\\resources\\" + wrongFileName + ".json not found!"));
        }

    }

    @AfterEach
    public void afterTest() {
        softAssert.assertAll();
    }

}