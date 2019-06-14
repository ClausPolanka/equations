import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestRunnerTest {

    @Test
    public void convertJsonToEquationReturnsEquationForGivenJson() {
        String json = "{  \"equation\" : \"0=9\"}";
        Equation equation = TestRunner.convertJsonToEquation(json);
        assertThat(equation, is(equalTo(new Equation("0=9"))));
    }

}
