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

    @Test
    public void toEquationReturnsEquationForGivenJson() {
        String json = "{  \"equation\" : \"0=9\"}";
        Equation equation = EquationJsonConverter.toEquation(json);
        assertThat(equation, is(equalTo(new Equation("0=9"))));
    }

    @TestWith({
            "0+6=9",
            "0-6=9"
    })
    public void toEquationReturnsEquationForGivenJsonInStage2Format(String e) {
        String json = "{  \"equation\" : \"" + e + "\"}";
        Equation equation = EquationJsonConverter.toEquation(json);
        assertThat(equation, is(equalTo(new Equation(e))));
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
    public void toJsonReturnsJsonInStage2FormatForGivenSingleSolution() {
        Set<Equation> solutions = new HashSet<>();
        solutions.add(new Equation("1+3=4"));
        String json = EquationJsonConverter.toJson(solutions);
        assertThat(json, is(equalTo("{ \"correctedEquations\": [\"1+3=4\"]}")));
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
