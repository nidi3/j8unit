package guru.nidi.j8unit;

import org.junit.runner.RunWith;

import static guru.nidi.j8unit.J8Assert.assertThrows;
import static guru.nidi.j8unit.J8Unit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(J8UnitRunner.class)
public class SimpleTest {
    {
        describe("The first test", () -> {
            int value = 42;

            it("should be executed", () -> {
                assertEquals(42, value);
            });

            describe("sub", () -> {
                it("should be nested and throw an Exception", () -> {
                    assertThrows(ArithmeticException.class, () -> {
                        int sub = value / 0;
                    });
                });
            });
        });

        describe("pedro", () -> {
            String p = "pedro";
            it("should be pedro", () -> {
                assertEquals("pedro", p);
            });
            it("should have a length of 5", () -> {
                assertEquals(5, p.length());
            });
        });
    }

}
