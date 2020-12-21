package tests;

import framework.AbstractBaseTest;
import framework.pages.wikiTallestBuildingsPage.WikiTallestBuildingsPage;
import ru.yandex.qatools.allure.annotations.Step;


import static tests.Config.APP_URL;

public class BaseTest extends AbstractBaseTest {

    @Step
    public WikiTallestBuildingsPage openInitPage() {
        getDriver().loadUrl(APP_URL);
        return new WikiTallestBuildingsPage(getDriver());
    }
}
