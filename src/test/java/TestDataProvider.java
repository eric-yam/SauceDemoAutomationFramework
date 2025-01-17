import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;

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

//    public static Object[][] loginTestDataProvider() {
//        JsonObject jsonObj = readJson();
//        Object[][] result = new Object[jsonObj.keySet().size()][1];
//
//        for (int i = 0; i < jsonObj.keySet().size(); i++) {
//            Hashtable<String, String> loginMap = new Hashtable<String, String>();
//
//            String user = (String) jsonObj.keySet().toArray()[i];
//
//            for (String key : jsonObj.get(user).getAsJsonObject().keySet()) {
//                loginMap.put(key, jsonObj.get(user).getAsJsonObject().get(key).getAsString());
//            }
//            result[i][0] = loginMap;
//        }
//
////        jsonObj.get("user").getAsJsonObject().keySet()
////        jsonObj.keySet()
//        return result;
//    }
}
