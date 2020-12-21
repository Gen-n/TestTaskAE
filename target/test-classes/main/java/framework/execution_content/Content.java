package framework.execution_content;

import framework.assertions.Assertions;

import java.util.List;

public class Content {

    public static final ThreadLocal<DriverFactory> REUSABLE_DRIVER = new ThreadLocal<>();
    public static final ThreadLocal<Assertions> SOFT_ASSERTIONS = new ThreadLocal<>();
    public static final ThreadLocal<List<DriverFactory>> factoryDriversList = new ThreadLocal<>();
    public static String BROWSER_MODE;
    public static String BROWSER_NAME;
    public static String REMOTE_HOST;
    public static boolean GRID_MODE;
    public static boolean BROWSER_MAX_MODE = true;

    public static ThreadLocal<DriverFactory> getReusableDriver(){
        return REUSABLE_DRIVER;
    }

    public static ThreadLocal<Assertions> getSoftAssertions(){
        return SOFT_ASSERTIONS;
    }

    public static ThreadLocal<List<DriverFactory>> getFactoryDriversList(){
        return factoryDriversList;
    }

}
