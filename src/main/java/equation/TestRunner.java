package equation;

import java.io.IOException;
import java.util.Set;

import static equation.EquationJsonConverter.toEquation;
import static equation.EquationJsonConverter.toJson;
import static equation.Equations.solve;
import static equation.TestServerHttpConnector.getAssignmentFor;
import static equation.TestServerHttpConnector.submitSolutionFor;
import static java.util.stream.IntStream.range;

public class TestRunner {

    public static void main(String[] args) {
        range(1, 101).forEach(testCase -> {
            try {
                runTestCase(testCase);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void runTestCase(int testCase) throws IOException {
        String jsonAssignment = getAssignmentFor(testCase);
        Equation equation = toEquation(jsonAssignment);
        Set<Equation> solutions = solve(equation);
        String jsonSolution = toJson(solutions);
        submitSolutionFor(testCase, jsonSolution);
    }

}
