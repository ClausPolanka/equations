package equation;

import java.io.IOException;
import java.util.Set;

import static equation.EquationJsonConverter.toEquation;
import static equation.EquationJsonConverter.toJson;
import static equation.Equations.solve;
import static equation.Equations.solveStage3;
import static equation.TestServerHttpConnector.getAssignmentFor;
import static equation.TestServerHttpConnector.submitSolutionFor;
import static java.util.stream.IntStream.range;

public class TestRunner {

    private static int failed;

    public static void main(String[] args) {
        runTestCases(1, 101);
        runTestCases(2, 151);
        runTestCases(3, 151);
        System.out.println(failed);
    }

    private static void runTestCases(int stage, int lastTestCase) {
        range(1, lastTestCase).forEach(testCase -> {
            try {
                runTestCase(stage, testCase);
            } catch (IOException e) {
                //                throw new RuntimeException(e);
            }
        });
    }

    private static void runTestCase(int stage, int testCase) throws IOException {
        String jsonAssignment = getAssignmentFor(stage, testCase);
        Equation equation = toEquation(jsonAssignment);
        Set<Equation> solutions;
        if (stage != 3) {
            solutions = solve(equation);
        } else {
            solutions = solveStage3(equation);
        }
        String jsonSolution = toJson(solutions);
        try {
            submitSolutionFor(stage, testCase, jsonSolution);
        } catch (IOException e) {
            failed++;
            System.out.println(testCase + ": " + equation + " => " + jsonSolution);
        }
    }

}
