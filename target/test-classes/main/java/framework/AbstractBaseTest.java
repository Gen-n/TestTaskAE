package framework;

import framework.execution_content.DriverFactory;
import listeners.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import framework.execution_content.Content;

@Listeners(TestListeners.class)
public abstract class AbstractBaseTest {

    public DriverFactory getDriver() {
        if (Content.getFactoryDriversList().get().get(0) != null) {
            return Content.getFactoryDriversList().get().get(0);
        } else return Content.getReusableDriver().get();
    }

    public SoftAssert softAssert() {
        return Content.getSoftAssertions().get();
    }

    public DriverFactory getDriver(int factoryDriverNumber) {
        if (factoryDriverNumber < 1) throw new IllegalStateException("Not valid browser number");
        if (Content.getFactoryDriversList().get().get(factoryDriverNumber - 1) != null) {
            return Content.getFactoryDriversList().get().get(factoryDriverNumber - 1);
        } else throw new IllegalStateException("FactoryDriver with such number not exists");
    }
}
