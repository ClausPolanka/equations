package test.equation;

import equation.Equation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class EquationTest {

    private static final String ANY_DIGIT = "0";
    private static final Equation ANY_EQUATION = new Equation("1+2=3");

    @Test
    public void createNewEquation() {
        Equation equation = new Equation("0=1");
        assertThat(equation.getLeftSide().toString(), is(equalTo(ANY_DIGIT)));
        assertThat(equation.getRightSide(), is(equalTo("1")));
    }

    @Test
    public void equalsReturnsTrueIfTwoEquationsAreSame() {
        Equation equation = new Equation("0=1");
        assertThat(equation, is(equalTo(equation)));
    }

    @Test
    public void equalsReturnsFalseIfTwoEquationsHaveDifferentRightSide() {
        Equation equation1 = new Equation("0=1");
        Equation equation2 = new Equation("0=2");
        assertThat(equation1, is(not(equalTo(equation2))));
    }

    @Test
    public void equalsReturnsFalseIfTwoEquationsHaveDifferentLeftSide() {
        Equation equation1 = new Equation("1=0");
        Equation equation2 = new Equation("2=0");
        assertThat(equation1, is(not(equalTo(equation2))));
    }

    @Test
    public void newCorrectEquationCreatesCorrectEquationFromOneDigit() {
        Equation equation = Equation.newCorrectEquation(ANY_DIGIT);
        assertThat(equation.getLeftSide().toString(), is(equalTo(ANY_DIGIT)));
        assertThat(equation.getRightSide(), is(equalTo(ANY_DIGIT)));
    }

    @Test
    public void equalsReturnsFalseIfGivenObjectIsNotOfTypeEquation() {
        assertThat(ANY_EQUATION.equals(new Object()), is(false));
    }

}
