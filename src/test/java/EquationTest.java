import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class EquationTest {
    @Test
    public void createNewEquation() {
        Equation equation = new Equation("0=1");
        assertThat(equation.getLeftSide(), is(equalTo("0")));
        assertThat(equation.getRightSide(), is(equalTo("1")));
    }

    @Test
    public void equalsReturnsTrueIfTwoEquationsAreSame() {
        Equation equation = new Equation("0=1");
        assertThat(equation, is(equalTo(equation)));
    }

    @Test
    public void equalsReturnsFalseIfTwoEquationsAreDifferent() {
        Equation equation1 = new Equation("0=1");
        Equation equation2 = new Equation("0=2");
        assertThat(equation1, is(not(equalTo(equation2))));
    }

}
