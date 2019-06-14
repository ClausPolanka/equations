import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void convertToJsonReturnsJsonForGivenSolutions() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("1=1"));
        String json = TestRunner.convertToJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"1=1\"]}")));
    }

}
