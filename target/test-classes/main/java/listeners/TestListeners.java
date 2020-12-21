package listeners;

import framework.annotations.UseAlreadyOpenedBrowser;
import framework.assertions.Assertions;
import framework.execution_content.Content;
import framework.execution_content.DriverFactory;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

public class TestListeners implements IInvokedMethodListener {

    /**
     * Execute before each method
     *
     * @param method
     * @param testResult
     */
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            beforeTest(method);
        }

    }

    /**
     * Execute before each test
     *
     * @param method
     */
    public void beforeTest(IInvokedMethod method) {

        String maxRetryCount = method.getTestMethod().getXmlTest().getLocalParameters().get("rerunMode");

        if (maxRetryCount != null) {
            if (method.getTestMethod().getRetryAnalyzer() == null) {
                int retryCount = 3;
                try {
                    retryCount = Integer.parseInt(maxRetryCount);
                } catch (Exception e) {
                    //do nothing
                }
                method.getTestMethod().setRetryAnalyzer(new RetryAnalyzerImpl(retryCount));
            }
        }

        System.out.println("-----TEST: " + method.getTestMethod().getDescription() + " STARTED-----");

        Content.BROWSER_MODE = method.getTestMethod().getXmlTest().getLocalParameters().get("browserMode");
        if (Content.BROWSER_MODE == null) Content.BROWSER_MODE = "";
        Content.BROWSER_NAME = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");

        Assertions softAssert = new Assertions();
        Content.getSoftAssertions().set(softAssert);

        UseAlreadyOpenedBrowser useAlreadyOpenedBrowser = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(UseAlreadyOpenedBrowser.class);
        if (useAlreadyOpenedBrowser == null) {
            DriverFactory factoryDriver = new DriverFactory();
            Content.getFactoryDriversList().set(new ArrayList<>());
            Content.getFactoryDriversList().get().add(factoryDriver);
        } else {
            if (Content.getReusableDriver().get() == null) Content.getReusableDriver().set(new DriverFactory());
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            printResult(method, testResult);

            DriverFactory reusableDriver = Content.getReusableDriver().get();

            if (testResult.getStatus() == ITestResult.FAILURE) {
                if (reusableDriver != null && reusableDriver.getWebDriver() != null) {
                    ArrayList<String> tabs = new ArrayList<>(reusableDriver.getWebDriver().getWindowHandles());
                    for (int i = 0; i < tabs.size(); i++) {
                        reusableDriver.setCurrentTab(i);
                        takeScreenShot(reusableDriver.getWebDriver(), testResult.getName() + " Tab #" + (i + 1));
                    }
                    catchJSErrors(reusableDriver, "First browser. Tab #");
                }
            }

            if (Content.getFactoryDriversList() != null && Content.getFactoryDriversList().get() != null) {
                for (DriverFactory factoryDriver : Content.getFactoryDriversList().get()) {
                    if (testResult.getStatus() == ITestResult.FAILURE) {
                        if (factoryDriver != null && factoryDriver.getWebDriver() != null) {
                            ArrayList<String> tabs = new ArrayList<>(factoryDriver.getWebDriver().getWindowHandles());
                            for (int i = 0; i < tabs.size(); i++) {
                                factoryDriver.setCurrentTab(i);
                                takeScreenShot(factoryDriver.getWebDriver(), testResult.getName() + " Tab #" + (i + 1));
                            }
                            catchJSErrors(factoryDriver, "First browser. Tab #");
                        }
                    }

                    UseAlreadyOpenedBrowser useAlreadyOpenedBrowser = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(UseAlreadyOpenedBrowser.class);
                    if (useAlreadyOpenedBrowser == null) close(factoryDriver);
                }
            }

            Content.getFactoryDriversList().remove();
            Content.getSoftAssertions().remove();
        }

    }

    public void printResult(IInvokedMethod method, ITestResult testResult) {

        System.out.println("_________TEST RUN_________ Test '" + method.getTestMethod().getDescription() + "' was finished with result: " +
                ((testResult.getStatus() == 1) ? "SUCCESS" : (testResult.getStatus() == 2 ? "FAILURE" : "SKIP")));

        if (testResult.getStatus() == 1) {
            if (Boolean.parseBoolean(method.getTestMethod().getXmlTest().getLocalParameters().get("resultToLogFile"))) {
                try (PrintWriter writer = new PrintWriter("prodTestLog.txt", "UTF-8")) {
                    writer.println("Result: success");

                    //failed status is set in retry analyzer
//                    writer.println("Result: fail");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Attachment(value = "Screenshot: {1}", type = "image/png")
    private byte[] takeScreenShot(WebDriver driver, String testResultName) {
        byte[] result = null;
        if (driver != null) {
            try {
                File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                result = IOUtils.toByteArray(new FileInputStream(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @Attachment(value = "Console of {1}")
    public String catchJSErrors(DriverFactory factoryDriver, String prefix) {
        StringBuilder logs = new StringBuilder();
        if (factoryDriver != null && factoryDriver.getWebDriver() != null) {
            LogEntries entries = factoryDriver.getWebDriver().manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : entries) {
                if (entry.getLevel() != Level.INFO) {
                    logs.append(new Date(entry.getTimestamp())).append(" ").append(entry.getLevel()).append(" ").append(entry.getMessage()).append("\r");
                }
            }
        }
        return prefix + "\r" + logs.toString();
    }

    private void close(DriverFactory factoryDriver) {
        if (factoryDriver != null && factoryDriver.getWebDriver() != null) {
            factoryDriver.getWebDriver().quit();
        }
    }
}
