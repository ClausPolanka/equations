package test.equation;

import equation.*;
import org.junit.*;
import org.junit.rules.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ExpressionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void createNewExpressionReturnsExpressionForAGivenValidExpressionString() {
        Expression e = new Expression("0+1");
        assertThat(e.operand1, is(equalTo("0")));
        assertThat(e.operand2, is(equalTo("1")));
        assertThat(e.operator, is(equalTo("+")));
    }

    @Test
    public void evaluateEvaluatesSingleDigitExpression() {
        Expression e = new Expression("1");
        assertThat(e.evaluate(), is(equalTo("1")));
    }

    @Test
    public void evaluateEvaluatesAddition() {
        Expression e = new Expression("1+1");
        assertThat(e.evaluate(), is(equalTo("2")));
    }

    @Test
    public void evaluateEvaluatesSubtraction() {
        Expression e = new Expression("1-1");
        assertThat(e.evaluate(), is(equalTo("0")));
    }

    @Test
    public void evaluateThrowsExceptionForExpressionWithUnsupportedOperator() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Operation Not Supported");

        Expression e = new Expression("1*1");
        e.evaluate();
    }
}
