import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EquationTest {
    @Test
    public void createNewEquation() {
        Equation equation = new Equation("0=1");
        assertThat(equation.getLeftSide(), is(equalTo("0")));
        assertThat(equation.getRightSide(), is(equalTo("1")));
    }

    @Test
    public void equalsReturnsFalseIfTwoEquationsAreDifferent() {
        Equation equation = new Equation("0=1");
        assertThat(equation, is(equalTo(equation)));
    }


}
