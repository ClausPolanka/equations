package test.equation;

import equation.Expression;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionTest {

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
    public void withFlippedOperatorReturnsAdditionForSubstraction() {
        final Expression e = new Expression("1-1");
        assertThat(e.withFlippedOperator().getOperator(), is(equalTo("+")));
    }

    @Test
    public void withFlippedOperatorReturnsSubstractionForAddition() {
        final Expression e = new Expression("1+1");
        assertThat(e.withFlippedOperator().getOperator(), is(equalTo("-")));
    }
}
