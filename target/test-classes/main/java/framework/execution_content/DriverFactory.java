package framework.execution_content;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverFactory {

    private WebDriver webDriver = null;
    private int currentTab = 0;

    public DriverFactory() {
        launchBrowser();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

//    public DriverFactory(DriverFactory factory, int currentTab) {
//        this.webDriver = factory.getWebDriver();
//        this.currentTab = currentTab;
//        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
//        if (tabs.size() - 1 >= currentTab) {
//            webDriver.switchTo().window(tabs.get(currentTab));
//        } else {
//            webDriver.switchTo().window(tabs.get(tabs.size() - 1));
//            ((JavascriptExecutor) webDriver).executeScript("window.open;");
//            tabs = new ArrayList<>(webDriver.getWindowHandles());
//            webDriver.switchTo().window(tabs.get(currentTab));
//        }
//    }


    public DriverFactory setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
        return this;
    }

    public void loadUrl(String url) {
        if (webDriver != null) {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(currentTab));
            webDriver.get(url);
        }
    }


    public WebDriver getWebDriver() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        if (tabs.size() <= currentTab) {
            //check if current tab is already closed then switch to first tab
            return webDriver.switchTo().window(tabs.get(0));
        }
        return webDriver.switchTo().window(tabs.get(currentTab));
    }

    public void switchImplicitWaitOff() {
        switchImplicitWait(1);
    }

    public void switchImplicitWait(int sec) {
        webDriver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    private void launchBrowser() {
        LoggingPreferences logsPrefs = new LoggingPreferences();
        logsPrefs.enable(LogType.BROWSER, Level.ALL);

        //Create prefs map to store all prefs
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        //Create chrome options to set these prefs
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-fake-ui-media-stream");
        if (Content.BROWSER_MAX_MODE) {
            options.addArguments("--start-maximized");
            options.addArguments("--window-size=1280,768");
        }
        options.setCapability(CapabilityType.LOGGING_PREFS, logsPrefs);

        if (Content.BROWSER_MODE.equalsIgnoreCase("headless")) {
            options.addArguments("headless");
        }

        options.setExperimentalOption("prefs", prefs);

        //Initialize chromeDriver with chromeOptions with disabled notifications in browser
        if (Content.REMOTE_HOST != null) {
            try {
                URL host = new URL(Content.REMOTE_HOST);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            if (Content.GRID_MODE) {
                URL host = null;
                try {
                    host = new URL("http://local-ip:port/wd/hub");
                    webDriver =  new RemoteWebDriver(host, options);
                    ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector() {
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                webDriver = new RemoteWebDriver(host, options);
                ((RemoteWebDriver) webDriver).setFileDetector(new LocalFileDetector());
            } else {
                if (SystemUtils.IS_OS_WINDOWS) {
                    System.setProperty("webdriver.chrome.webDriver", "chromedriver.exe");
                    webDriver = new ChromeDriver(options);
                } else if (SystemUtils.IS_OS_LINUX) {
                    System.setProperty("webdriver.chrome.webDriver", "chromedriver");
                    webDriver = new ChromeDriver(options);
                }
            }
        }
    }
}
