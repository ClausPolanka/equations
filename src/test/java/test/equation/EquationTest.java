package test.equation;

import equation.Equation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class EquationTest {

    public static final String ANY_OPERAND2 = "0";
    private static final String ANY_DIGIT = ANY_OPERAND2;
    public static final String ANY_RIGHT_SIDE = "1";
    private static final Equation ANY_EQUATION = new Equation(ANY_RIGHT_SIDE + "+2=3");

    @Test
    public void createNewEquation() {
        Equation equation = new Equation(ANY_OPERAND2 + "=" + ANY_RIGHT_SIDE);
        assertThat(equation.getLeftSide().toString(), is(equalTo(ANY_DIGIT)));
        assertThat(equation.getRightSide(), is(equalTo(ANY_RIGHT_SIDE)));
    }

    @Test
    public void equalsReturnsTrueIfTwoEquationsAreSame() {
        Equation equation = new Equation(ANY_OPERAND2 + "=" + ANY_RIGHT_SIDE);
        assertThat(equation, is(equalTo(equation)));
    }

    @Test
    public void equalsReturnsFalseIfTwoEquationsHaveDifferentRightSide() {
        Equation equation1 = new Equation(ANY_OPERAND2 + "=" + ANY_RIGHT_SIDE);
        Equation equation2 = new Equation(ANY_OPERAND2 + "=2");
        assertThat(equation1, is(not(equalTo(equation2))));
    }

    @Test
    public void equalsReturnsFalseIfTwoEquationsHaveDifferentLeftSide() {
        Equation equation1 = new Equation(ANY_RIGHT_SIDE + "=" + ANY_OPERAND2);
        Equation equation2 = new Equation("2=" + ANY_OPERAND2);
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

    @Test
    public void flipReturnsEquationWithAdditionGivenEquationWithSubstraction() {
        Equation equation = new Equation("1-0=1");
        Equation actual = equation.flip();
        assertThat(actual.getLeftSide().getOperator(), is(equalTo("+")));
    }

    @Test
    public void flipReturnsEquationWithSubstractionGivenEquationWithAddition() {
        Equation equation = new Equation("1+0=1");
        Equation actual = equation.flip();
        assertThat(actual.getLeftSide().getOperator(), is(equalTo("-")));
    }

}
