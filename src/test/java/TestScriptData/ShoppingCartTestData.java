package TestScriptData;

import java.util.List;

public class ShoppingCartTestData {
    private String username;
    private String password;
    private List<String> productsToSelect;
    private List<String> productsToRemove;
    private double expectedSubTotal;

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
