package tests;

public class Config {

    public static String APP_URL = (System.getProperty("env") != null)
            ? System.getProperty("env") :
//            "someUrl";
            "https://en.wikipedia.org/wiki/List_of_tallest_buildings";

}
