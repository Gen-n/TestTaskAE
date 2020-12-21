package framework.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * USE DEFAULT DRIVER.
 * NOT USE IN PARALLEL
 * BROWSER DOESN'T CLOSE AUTOMATIC AFTER TEST WAS DONE
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface UseAlreadyOpenedBrowser {
}
