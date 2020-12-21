package framework.pages.wikiTallestBuildingsPage;

public class LocatorsWikiTallestBuildingsPage {

    public static final String TALLEST_BUILDING_TABLE = "//table[@class='wikitable sortable jquery-tablesorter'][1]";
    public static final String TALLEST_BUILDING_TABLE_HEADER = TALLEST_BUILDING_TABLE + "/thead";
    public static final String TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_NAME = TALLEST_BUILDING_TABLE_HEADER +
            "//th[contains(text(),'{I}')]";
    public static final String TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_METERS = TALLEST_BUILDING_TABLE_HEADER +
            "/tr[2]//th[contains(text(),'m')]";
    public static final String TALLEST_BUILDING_TABLE_HEADER_TITLE_BY_HEIGHT_FEET = TALLEST_BUILDING_TABLE_HEADER +
            "/tr[2]//th[contains(text(),'ft')]";
    public static final String TALLEST_BUILDING_TABLE_ROWS = TALLEST_BUILDING_TABLE + "/tbody/tr";
    public static final String TALLEST_BUILDING_TABLE_ROW_BY_INDEX = TALLEST_BUILDING_TABLE_ROWS + "[{I}]";
    public static final String TALLEST_BUILDING_TABLE_NAME_BY_ROW_INDEX = TALLEST_BUILDING_TABLE_ROW_BY_INDEX + "/td[2]//a";
    public static final String TALLEST_BUILDING_TABLE_COUNTRY_BY_ROW_INDEX = TALLEST_BUILDING_TABLE_ROW_BY_INDEX + "/td[5]//a";
}
