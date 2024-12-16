package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.PageObjects.Pages.CheckoutPage.CheckoutFinishPage;
import org.PageObjects.Pages.CheckoutPage.CheckoutInformationPage;
import org.PageObjects.Pages.CheckoutPage.CheckoutOverviewPage;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {
    TestContext context;

    public CheckoutSteps(TestContext context) {
        this.context = context;
    }

    @And("Checkout Information Page Is Displayed")
    public void checkoutInfoPageDisplayed() {
        CheckoutInformationPage cip = new CheckoutInformationPage(this.context.driver);
        cip.waitForCheckoutInformationPage();
    }

    @And("Proceed From Checkout Information")
    public void continueCheckoutInfo() {
        CheckoutInformationPage cip = new CheckoutInformationPage(this.context.driver);
        cip.clickContinueButton();
    }

    @And ("Cancel Checkout Information")
    public void cancelCheckoutInfo() {
        CheckoutInformationPage cip = new CheckoutInformationPage(this.context.driver);
        cip.clickCancelButton();
    }

    @And("Validate Error Message Displayed On Checkout Information Page")
    public void validateErrorMsgCheckoutInfo(String isErrorDisplayed) {
        CheckoutInformationPage cip = new CheckoutInformationPage(this.context.driver);
        assert (cip.isErrorMsgDisplayed() == Boolean.parseBoolean(isErrorDisplayed));
    }

    @And("Fill Checkout Information Page")
    public void fillCheckoutInformationPage(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        CheckoutInformationPage cip = new CheckoutInformationPage(this.context.driver);
        cip.waitForCheckoutInformationPage();

        for (Map<String, String> row : table) {
            cip.setInputFirstName(row.get("First Name"));
            cip.setInputLastName(row.get("Last Name"));
            cip.setInputPostalCode(row.get("Postal Code"));
        }

        cip.clickContinueButton();
    }

    @And("Checkout Overview Page Is Displayed")
    public void checkoutOverviewDisplayed() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.context.driver);
        cop.waitForCheckoutOverviewPage();
    }

    @And("Validate Number Of Products In Cart On Overview Page")
    public void validateNumberOfProductsInCart() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.context.driver);
        assertEquals(cop.getNumberOfProductsInCart(), this.context.numPurchasedItems);
    }

    @And("Validate Sub-Total On Overview Page")
    public void validateSubTotal() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.context.driver);
        assertEquals(cop.getSubTotalLabel(), cop.calculateSubTotal());
    }

    @And("Validate Tax Total On Overview Page")
    public void validateSummaryTaxTotal() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.context.driver);
        assertEquals(cop.getSummaryTaxTotalLabel(), cop.calculateTaxAndSubTotal());
    }

    @And("Validate Total On Overview Page")
    public void validateTotal() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.context.driver);
        assertEquals(cop.getSummaryTotalLabel(), cop.calculateTotal());
    }

    @And("Finish On Checkout Overview Page")
    public void finishCheckoutOverview() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.context.driver);
        cop.clickFinishButton();
        CheckoutFinishPage cfp = new CheckoutFinishPage(this.context.driver);
        cfp.waitCheckoutFinishPage();

        assertTrue(cfp.verifyFinishPageDisplayed());
    }
}
