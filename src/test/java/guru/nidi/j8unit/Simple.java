package guru.nidi.j8unit;

import org.junit.Test;
import org.junit.runner.RunWith;

import static guru.nidi.j8unit.J8unit.describe;
import static guru.nidi.j8unit.J8unit.it;

/**
 *
 */
@RunWith(J8UnitRunner.class)
public class Simple {
    {
        describe("first", () -> {
            it("should be cool", () -> {
                System.out.println("cool");
            });
            describe("sub", () -> {
                it("should be nested", () -> {
                    System.out.println("nested");
                });
            });
        });
        describe("pedro", () -> {
            it("should be pedro", () -> {
                System.out.println("pedro");
            });
        });
    }

    @Test
    public void bla() {
        System.out.println("bla");
    }
}
