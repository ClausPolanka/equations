package test.equation;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import equation.Equation;
import equation.EquationJsonConverter;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ZohhakRunner.class)
public class EquationJsonConverterTest {

    @TestWith({
            "0=9",
            "5-0=3",
            "5+0=3"
    })
    public void toEquationReturnsEquationForGivenJson(String equation) {
        String json = "{  \"equation\" : \"" + equation + "\"}";
        Equation e = EquationJsonConverter.toEquation(json);
        assertThat(e, is(equalTo(new Equation(equation))));
    }

    @Test
    public void toJsonReturnsJsonForGivenEmptySolution() {
        Set<Equation> solutions = new HashSet<>();
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": []}")));
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
