package test.equation;

import equation.Expression;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionTest {

    @Test
    public void createNewExpressionReturnsExpressionForAGivenValidExpressionString() {
        Expression e = new Expression("0+1");
        assertThat(e.operand1, is(equalTo("0")));
        assertThat(e.operand2, is(equalTo("1")));
        assertThat(e.operator, is(equalTo("+")));
    }

    @Test
    public void evaluateAddsOperandsForExpressionWithPlusOperator() {
        Expression e = new Expression("1+1");
        assertThat(e.evaluate(), is(equalTo("2")));
    }

    @Test
    public void evaluateSubtractsOperandsForExpressionWithMinusOperator() {
        Expression e = new Expression("1-1");
        assertThat(e.evaluate(), is(equalTo("0")));
    }

    @Test(expected = RuntimeException.class)
    public void evaluateThrowsExceptionForExpressionWithUnsupportedOperator() {
        Expression e = new Expression("1*1");
        e.evaluate();
    }
}
