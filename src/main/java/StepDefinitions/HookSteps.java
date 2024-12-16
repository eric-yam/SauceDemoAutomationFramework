package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class HookSteps {
    TestContext context;

    public HookSteps(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        this.context.setup();
    }

    /**
     * Note that Scenario Before and After hooks can declare the Scenario parameter,
     * and will receive an instance of the current scenario running
     */
    @After
    public void tearDown(Scenario scenario) {
        this.context.cleanUp(scenario);
    }
}
