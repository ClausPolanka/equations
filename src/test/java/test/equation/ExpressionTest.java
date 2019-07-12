package test.equation;

import equation.Expression;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
