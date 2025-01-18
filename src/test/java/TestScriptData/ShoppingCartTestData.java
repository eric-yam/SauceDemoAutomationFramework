package TestScriptData;

import java.util.List;

public class ShoppingCartTestData {
    String username;
    String password;
    List<String> productsToSelect;
    List<String> productsToRemove;
    double expectedSubTotal;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getProductsToSelect() {
        return productsToSelect;
    }

    public List<String> getProductsToRemove() {
        return productsToRemove;
    }

    public double getExpectedSubTotal() {
        return expectedSubTotal;
    }
}
