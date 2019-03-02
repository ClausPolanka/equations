import org.junit.Test;

import static java.lang.String.valueOf;

public class FooTest {

    @Test
    public void evalReturnsCorrectedEquationsForGivenInput() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(i + "=" + j + ": ");
                System.out.println(Foo.solveEquation(valueOf(i), valueOf(j)));
            }
        }
    }
}
