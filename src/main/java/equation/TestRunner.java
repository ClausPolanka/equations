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
        runTestCases(1, 100);
        runTestCases(2, 150);

    }

    private static void runTestCases(int testStage, int lastTestCase) {
        range(1, lastTestCase + 1).forEach(testCase -> {
            try {
                runTestCase(testStage, testCase);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void runTestCase(int testStage, int testCase) throws IOException {
        String jsonAssignment = getAssignmentFor(testStage, testCase);
        Equation equation = toEquation(jsonAssignment);
        Set<Equation> solutions = solve(equation);
        String jsonSolution = toJson(solutions);
        submitSolutionFor(testStage, testCase, jsonSolution);
    }

}
