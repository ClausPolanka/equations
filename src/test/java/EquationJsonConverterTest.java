import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EquationJsonConverterTest {

    @Test
    public void toEquationReturnsEquationForGivenJson() {
        String json = "{  \"equation\" : \"0=9\"}";
        Equation equation = EquationJsonConverter.toEquation(json);
        assertThat(equation, is(equalTo(new Equation("0=9"))));
    }

    @Test
    public void toJsonReturnsJsonForGivenSingleSolution() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("1=1"));
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"1=1\"]}")));
    }

    @Test
    public void toJsonReturnsJsonForGivenMultipleSolutions() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("0=0"));
        solutions.add(new Equation("6=6"));
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"0=0\", \"6=6\"]}")));
    }
}
