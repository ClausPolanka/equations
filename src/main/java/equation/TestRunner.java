package equation;

import java.io.*;
import java.util.*;

import static equation.EquationJsonConverter.*;
import static equation.Equations.*;
import static equation.TestServerHttpConnector.*;
import static java.util.stream.IntStream.*;

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
