import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class ExtensionBaseTest implements AfterTestExecutionCallback{
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            Allure.addAttachment("screenshot", new ByteArrayInputStream(takeScreenshot()));
        }
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BYTES);
    }
}
