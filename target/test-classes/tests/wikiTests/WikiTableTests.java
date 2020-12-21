package tests.wikiTests;

import framework.pages.wikiTallestBuildingsPage.WikiTallestBuildingsPage;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

import static framework.pages.wikiTallestBuildingsPage.LocatorsWikiTallestBuildingsPage.TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX;
import static org.testng.Assert.assertEquals;

public class WikiTableTests extends BaseTest {

    @Test(description = "Assert sorting by Name and Height (m) columns")
    public void verifySortingByNameAndHeight() {
        WikiTallestBuildingsPage page = openInitPage();
        page.sortTableInInitialOrder("Name");
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "Burj Khalifa");

        page.sortTableInDescendingOrder("Name");
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "Zifeng Tower");

        page.sortTableInAscendingOrder("Name");
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "23 Marina");

        page.sortTableByHeightInDescendingOrder(false);
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "Burj Khalifa");

        page.sortTableByHeightInInInitialOrder(true);
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "Burj Khalifa");

        page.sortTableByHeightInAscendingOrder(true);
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "Xi An Glory International Financial Center");

        softAssert().assertAll();
    }

    @Test(description = "Assert oldest building")
    public void verifyOldestBuilding() {
        WikiTallestBuildingsPage page = openInitPage();
        page.sortTableInAscendingOrder("Year");
        softAssert().assertEquals(page.getPresentElement(TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX, "1").getText(),
                "Empire State Building");
    }

    @Test(description = "Assert country with the most tallest buildings")
    public void verifyCountryWithMostTallestBuildings() {
        WikiTallestBuildingsPage page = openInitPage();

        //sorting table to split merged cells by country:
        page.sortTableInDescendingOrder("Country");

        // saving all country names to a list
        List list = page.getAllCountriesFromTable();

        assertEquals(page.getMostDuplicatedValueFromList(list), "China");
    }
}
