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
        assertThat(e.leftOperand, is(equalTo("0")));
        assertThat(e.rightOperand, is(equalTo("1")));
        assertThat(e.operator, is(equalTo("+")));
    }

}
