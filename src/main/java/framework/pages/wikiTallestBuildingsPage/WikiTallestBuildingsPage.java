package framework.pages.wikiTallestBuildingsPage;

import framework.execution_content.DriverFactory;
import framework.pages.GenericsPage;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static framework.pages.wikiTallestBuildingsPage.LocatorsWikiTallestBuildingsPage.*;

public class WikiTallestBuildingsPage extends GenericsPage {
    public WikiTallestBuildingsPage(DriverFactory driver) {
        super(driver);
    }

    public WebElement scrollToTallestBuildingsTableHeader() {
        return scrollToElement(TALLEST_BUILDING_TABLE_HEADER);
    }

    public void sortTableInDescendingOrder(String titleName) {
        scrollToTallestBuildingsTableHeader();
        while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME, titleName)
                .getAttribute("class").contains("headerSortDown")) {
            getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME, titleName).click();
        }
    }

    public void sortTableInAscendingOrder(String titleName) {
        scrollToTallestBuildingsTableHeader();
        while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME, titleName)
                .getAttribute("class").contains("headerSortUp")) {
            getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME, titleName).click();
        }
    }

    public void sortTableInInitialOrder(String titleName) {
        scrollToTallestBuildingsTableHeader();
        while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME, titleName)
                .getAttribute("title").contains("Sort ascending")) {
            getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME, titleName).click();
        }
    }

    public void sortTableByHeightInDescendingOrder(boolean sortByFeet) {
        scrollToTallestBuildingsTableHeader();
        if (sortByFeet == true) {
            while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET)
                    .getAttribute("class").contains("headerSortDown")) {
                getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET).click();
            }
        } else {
            while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS)
                    .getAttribute("class").contains("headerSortDown")) {
                getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS).click();
            }
        }
    }

    public void sortTableByHeightInAscendingOrder(boolean sortByFeet) {
        scrollToTallestBuildingsTableHeader();
        if (sortByFeet == true) {
            while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET)
                    .getAttribute("class").contains("headerSortUp")) {
                getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET).click();
            }
        } else {
            while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS)
                    .getAttribute("class").contains("headerSortUp")) {
                getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS).click();
            }
        }
    }

    public void sortTableByHeightInInInitialOrder(boolean sortByFeet) {
        scrollToTallestBuildingsTableHeader();
        if (sortByFeet == true) {
            while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET)
                    .getAttribute("title").contains("Sort ascending")) {
                getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET).click();
            }
        } else {
            while (!getPresentElement(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS)
                    .getAttribute("title").contains("Sort ascending")) {
                getVisible(TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS).click();
            }
        }
    }

    public List<String> getAllCountriesFromTable() {
        List<String> countries = new ArrayList<>();
        for (int i = 1; i <= getElements(TALLEST_BUILDING_TABLE_ROWS).size(); i++) {
            countries.add(getPresentElement(TALLEST_BUILDING_TABLE_COUNTRY_BY_ROW_INDEX, Integer.toString(i)).getText());
        }
        return countries;
    }

    public String getMostDuplicatedValueFromList(List<String> listOfCountries) {
        Collections.sort(listOfCountries);

        int maxCount = 1;
        String mostCommonCountry = listOfCountries.get(0);
        int currentCount = 1;

        // "for" loop started from "1" to avoid unnecessary additional calculations with indexes
        for (int i = 1; i < listOfCountries.size(); i++) {
            if (listOfCountries.get(i) == listOfCountries.get(i - 1))
                currentCount++;
            else {
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    mostCommonCountry = listOfCountries.get(i - 1);
                }
                currentCount = 1;
            }
        }

        // check if last element is most common
        if (currentCount > maxCount) {
            maxCount = currentCount;
            mostCommonCountry = listOfCountries.get(listOfCountries.size() - 1);
        }
        return mostCommonCountry;
    }
}