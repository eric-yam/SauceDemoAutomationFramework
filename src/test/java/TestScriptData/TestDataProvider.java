package TestScriptData;

import com.google.gson.*;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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

    public static Object[][] validLoginTestDataProvider() {
        return validLogin("src/test/resources/TestData/login_data.json", "user");
    }

    public static Object[][] invalidLoginTestDataProvider() {
        return validLogin("src/test/resources/TestData/login_data.json", "invalid_user");
    }

    public static Object[][] validLogin(String filepath, String user) {
        JsonObject jsonObj = readJson(filepath);
        Object[][] result = new Object[1][1];
        Hashtable<String, String> loginMap = new Hashtable<String, String>();

        for (String key : jsonObj.get(user).getAsJsonObject().keySet()) {
            loginMap.put(key, jsonObj.get(user).getAsJsonObject().get(key).getAsString());
        }
        result[0][0] = loginMap;

        return result;
    }
    public static Stream<Arguments> homePageTestDataProvider() {
        JsonObject jsonObj = readJson("src/test/resources/TestData/home_page_data.json");

        Gson gson = new Gson();
        String jsonInString = jsonObj.toString();
        HomePageTestData hptd= gson.fromJson(jsonInString, HomePageTestData.class);

        return Stream.of(Arguments.arguments(hptd));
    }
}
