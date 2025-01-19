package TestScriptData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public class TestDataProvider {

    public static JsonObject readJson(String filepath) {
        File file = new File(filepath);
        JsonObject jsonObj = null;
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            jsonObj = jsonElement.getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
//        JsonObject jsonObj = jsonElement.getAsJsonObject();
//        jsonObj.get("invalid_user").getAsJsonArray().get(0).getAsJsonObject().get("username");

        return jsonObj;
    }

    public static Stream<Arguments> loginTestDataProvider() {
        JsonObject jsonObj = readJson("src/test/resources/TestData/login_page_data.json");
        LoginPageTestData lptd = mapJsonToClass(jsonObj, LoginPageTestData.class);

        return Stream.of(Arguments.arguments(lptd));
    }

    public static Stream<Arguments> homePageTestDataProvider() {
        JsonObject jsonObj = readJson("src/test/resources/TestData/home_page_data.json");
        HomePageTestData hptd = mapJsonToClass(jsonObj, HomePageTestData.class);

        return Stream.of(Arguments.arguments(hptd));
    }

    public static Stream<Arguments> shoppingCartTestDataProvider() {
        JsonObject jsonObj = readJson("src/test/resources/TestData/shopping_cart_page_data.json");
        ShoppingCartTestData sctd = mapJsonToClass(jsonObj, ShoppingCartTestData.class);

        return Stream.of(Arguments.arguments(sctd));
    }

    public static Stream<Arguments> checkoutTestDataProvider() {
        JsonObject jsonObj = readJson("src/test/resources/TestData/checkout_page_data.json");
        CheckoutPageTestData cptd = mapJsonToClass(jsonObj, CheckoutPageTestData.class);

        return Stream.of(Arguments.arguments(cptd));
    }

    private static <T> T mapJsonToClass(JsonObject jsonObj, Class<T> currentClass) {
        Gson gson = new Gson();
        String jsonInString = jsonObj.toString();
        return gson.fromJson(jsonInString, currentClass);
    }
}
