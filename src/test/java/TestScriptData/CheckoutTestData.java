package TestScriptData;

import java.util.List;

public class CheckoutTestData {
    private String username;
    private String password;
    private double expectedSubTotal;
    private String firstName;
    private String lastName;
    private String postalCode;

    private List<String> productsToSelect;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getExpectedSubTotal() {
        return expectedSubTotal;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public List<String> getProductsToSelect() {
        return productsToSelect;
    }
}
