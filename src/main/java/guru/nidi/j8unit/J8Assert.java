package guru.nidi.j8unit;

import org.junit.Assert;

/**
 *
 */
public class J8Assert {
    private J8Assert() {
    }

    public static void assertThrows(Class<? extends Exception> ex, Runnable test) {
        try {
            test.run();
            Assert.fail();
        } catch (Exception e) {
            if (!ex.isAssignableFrom(e.getClass())) {
                Assert.fail(e + " expected to be a " + ex);
            }
        }
    }
}
