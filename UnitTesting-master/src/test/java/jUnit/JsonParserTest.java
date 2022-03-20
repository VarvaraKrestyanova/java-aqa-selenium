package jUnit;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    private final JsonParser jsonParser = new JsonParser();

    private static final Faker faker = new Faker();
    private static final String funnyCartName = faker.funnyName().name();
    private static final String funnyCartName2 = faker.funnyName().name();
    private final Cart cart = new Cart(funnyCartName);
    private final Cart cart2 = new Cart(funnyCartName2);
    private final Gson gson = new Gson();

    @BeforeEach
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
    void writeToFileTest() throws IOException {
        jsonParser.writeToFile(cart);
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");

        String expectedJsonLine = "{\"cartName\":\"" + funnyCartName + "\",\"realItems\":[{\"weight\":1560.0,\"name\":\"Audi\",\"price\":32026.9}],\"virtualItems\":[{\"sizeOnDisk\":20000.0,\"name\":\"Windows\",\"price\":11.0}],\"total\":38445.479999999996}";
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + cart.getCartName() + ".json"));
        String actualJsonLine = br.readLine();

        assertAll("file",
                () -> assertTrue(file.exists()),
                () -> assertTrue(file.isFile()),
                () -> assertEquals(expectedJsonLine, actualJsonLine)
        );
    }

    @Test
    void readFromFileTest() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + funnyCartName2 + ".json"));
        bw.write(gson.toJson(cart2));
        bw.close();
        Cart cartUnderTheTest = jsonParser.readFromFile(new File("src/main/resources/" + funnyCartName2 + ".json"));
        assertEquals(cart.getTotalPrice(), cartUnderTheTest.getTotalPrice());
        assertEquals(funnyCartName2, cartUnderTheTest.getCartName());
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"wrongName.json", "andrew-cat.json", "eugen-cartjson", " ", "json"})
    public void expectedExceptionTest(String wrongFileName) {
        Exception exception = assertThrows(NoSuchFileException.class, () -> {
            jsonParser.readFromFile(new File("src/main/resources/" + wrongFileName));
        });
        assertTrue(exception.getMessage().contentEquals("File src\\main\\resources\\" + wrongFileName + ".json not found!"));
    }

    @AfterAll
    public static void deleteTestFile() {
        new File("src/main/resources/" + funnyCartName + ".json").delete();
        new File("src/main/resources/" + funnyCartName2 + ".json").delete();
    }

}