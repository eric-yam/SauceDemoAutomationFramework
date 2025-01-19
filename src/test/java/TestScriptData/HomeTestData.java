package TestScriptData;

import java.util.List;

public class HomeTestData {
    // This class is a Java object representation of it's corresponding JSON object
    private String username;
    private String password;
    private List<String> expectedProductNames;
    private List<String> productsToSelect;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getExpectedProductNames() {
        return expectedProductNames;
    }

    public List<String> getProductsToSelect() {
        return productsToSelect;
    }
}
