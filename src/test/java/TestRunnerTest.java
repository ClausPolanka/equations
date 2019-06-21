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
        Equation equation = EquationJsonConverter.toEquation(json);
        assertThat(equation, is(equalTo(new Equation("0=9"))));
    }

    @Test
    public void convertToJsonReturnsJsonForGivenSolutions1Equals1() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("1=1"));
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"1=1\"]}")));
    }

    @Test
    public void convertToJsonReturnsJsonForGivenSolution2Equals2() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("2=2"));
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"2=2\"]}")));
    }

    @Test
    public void convertToJsonReturnsJsonForGivenMultipleSolutions() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("0=0"));
        solutions.add(new Equation("6=6"));
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"0=0\", \"6=6\"]}")));
    }
}
