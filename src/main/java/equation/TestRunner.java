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
                runTestCase(1, testCase);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        range(1, 151).forEach(testCase -> {
            try {
                runTestCase(2, testCase);
            } catch (IOException e) {
                /*throw new RuntimeException(e);*/
            }
        });
    }

    private static void runTestCase(int stage, int testCase) throws IOException {
        String jsonAssignment = getAssignmentFor(stage, testCase);
        Equation equation = toEquation(jsonAssignment);
        Set<Equation> solutions = solve(equation);
        String jsonSolution = toJson(solutions);
        submitSolutionFor(stage, testCase, jsonSolution);
    }

}
