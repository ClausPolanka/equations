package equation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class EquationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructorThrowsWhenEquationHasWrongFormat() {
        expectedException.expect(Equation.EquationInvalidFormatException.class);
        expectedException.expectMessage("Equation must be in format: NR=NR");

        new Equation("y=1");
    }

    @Test
    public void leftSideReturnsLeftSideOfEquation() {
        Equation e = new Equation("1=2");
        String leftSide = e.leftSide();
        assertEquals("equation left side", "1", leftSide);
    }

    @Test
    public void rightSideReturnsRightSideOfEquation() {
        Equation e = new Equation("1=2");
        String rightSide = e.rightSide();
        assertEquals("equation right side", "2", rightSide);
    }

    @Test
    public void toStringReturnsStringRepresentationOfEquation() {
        Equation e = new Equation("1=2");
        assertEquals("equation", "1=2", e.toString());
    }
}
