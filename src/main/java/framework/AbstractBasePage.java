package framework;

import framework.execution_content.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractBasePage {

    private static int timeoutInSeconds = 15;
    public DriverFactory factoryDriver = null;

    protected AbstractBasePage(DriverFactory factoryDriver) {
        this.factoryDriver = factoryDriver;
    }

    public WebElement getVisible(String xPathLocator){
        return this.getVisible(xPathLocator, null, null);
    }

    public WebElement getVisible(String xPathLocator, String I){
        return this.getVisible(xPathLocator, I, null);
    }

    public WebElement getVisible(String xPathLocator, String I, String II) {
        String xPath = getXpath(xPathLocator, I, II);
        return new FluentWait<>(factoryDriver.getWebDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath(xPath))));
    }

    public WebElement getClickable(String xPathLocator, String I, String II) {
        String xPath = getXpath(xPathLocator, I, II);
        return new FluentWait<>(factoryDriver.getWebDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(new By.ByXPath(xPath))));
    }

    public String getXpath(String xPathLocator) {
        return generateXpath(xPathLocator, null, null);
    }

    public String getXpath(String xPathLocator, String I) {
        return generateXpath(xPathLocator, I, null);
    }

    public String getXpath(String xPathLocator, String I, String II) {
        return generateXpath(xPathLocator, I, II);
    }

    public String firstParameter = null; //for some position by number

    public String generateXpath(String xPathLocator, String I, String II) {
        String xPathLocatorString = xPathLocator;
        if (I == null && firstParameter != null) {
            I = firstParameter;
        }
        if (xPathLocatorString == null) {
            xPathLocatorString = "";
        }
        if (I != null) {
            xPathLocatorString = xPathLocatorString.replace("{I}", I);
        }
        if (II != null) {
            xPathLocatorString = xPathLocatorString.replace("{II}", II);
        }

        return xPathLocatorString;
    }

    public WebElement getPresentElement(String xPathLocatorName) {
        return getPresentElement(xPathLocatorName, null, null);
    }

    public WebElement getPresentElement(String xPathLocatorName, String I) {
        return getPresentElement(xPathLocatorName, I, null);
    }

    public WebElement getPresentElement(String xPathLocatorName, String I, String II) {
        String xPath = getXpath(xPathLocatorName, I, II);
        return new FluentWait<>(factoryDriver.getWebDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(xPath))));
    }

    public WebElement scrollToElement(String xPathLocatorName) {
        return scrollToElement(xPathLocatorName, null, null);
    }

    public WebElement scrollToElement(String xPathLocatorName, String I) {
        return scrollToElement(xPathLocatorName, I, null);
    }

    public WebElement scrollToElement(String xPathLocatorName, String I, String II) {
        WebElement element = getPresentElement(xPathLocatorName, I, II);
        Actions actions = new Actions(factoryDriver.getWebDriver());
        actions.moveToElement(element);
        actions.perform();
        return element;
    }

    public List<WebElement> getElements(String xPathLocatorName) {
        return getElements(xPathLocatorName, null, null);
    }

    public List<WebElement> getElements(String xPathLocatorName, String I) {
        return getElements(xPathLocatorName, I, null);
    }

    public List<WebElement> getElements(String xPathLocatorName, String I, String II) {
        factoryDriver.switchImplicitWaitOff();
        String xPath = getXpath(xPathLocatorName, I, II);

        List<WebElement> elements;
        try {
            elements = factoryDriver.getWebDriver().findElements(new By.ByXPath(xPath));
        } catch (StaleElementReferenceException ex) {
            elements = factoryDriver.getWebDriver().findElements(new By.ByXPath(xPath));
        }

        return elements;
    }
}
