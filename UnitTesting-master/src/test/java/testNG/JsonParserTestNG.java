package testNG;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;

import static org.testng.Assert.*;

public class JsonParserTestNG {

    private final JsonParser jsonParser = new JsonParser();

    private static final Faker faker = new Faker();
    private static final String funnyCartName = faker.funnyName().name();
    private static final String funnyCartName2 = faker.funnyName().name();
    private final Cart cart = new Cart(funnyCartName);
    private final Cart cart2 = new Cart(funnyCartName2);
    private final Gson gson = new Gson();

    @BeforeMethod
    public void beforeTest() {
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560.0);
        cart.addRealItem(car);
        cart2.addRealItem(car);

        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);
        cart.addVirtualItem(disk);
        cart2.addVirtualItem(disk);
    }

    @Test
    public void testWriteToFile() throws IOException {
        jsonParser.writeToFile(cart);
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");

        String expectedJsonLine = gson.toJson(cart);
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + cart.getCartName() + ".json"));
        String actualJsonLine = br.readLine();

        assertTrue(file.exists());
        assertTrue(file.isFile());
        assertEquals(expectedJsonLine, actualJsonLine);
    }

    @Test(enabled = false)
    public void testReadFromFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + funnyCartName2 + ".json"));
        bw.write(gson.toJson(cart2));
        bw.close();
        Cart cartUnderTheTest = jsonParser.readFromFile(new File("src/main/resources/" + funnyCartName2 + ".json"));
        assertEquals(cart.getTotalPrice(), cartUnderTheTest.getTotalPrice());
        assertEquals(funnyCartName2, cartUnderTheTest.getCartName());
    }

    @Parameters("wrongNameKey")
    @Test
    public void testExpectedException(String wrongFileName) {

        Throwable exception = expectThrows(NoSuchFileException.class, () -> {
            jsonParser.readFromFile(new File("src/main/resources/" + wrongFileName));
        });
        assertTrue(exception.getMessage().contentEquals("File src\\main\\resources\\" + wrongFileName + ".json not found!"));
    }
}