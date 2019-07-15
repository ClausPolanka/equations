package equation;

import java.io.*;
import java.util.*;

import static equation.EquationJsonConverter.*;
import static equation.TestServerHttpConnector.*;
import static java.util.stream.IntStream.*;

public class TestRunner {

    private static final Map<Integer, Equations> SOLVE = new HashMap<Integer, Equations>() {{
        put(1, new EquationsStage1And2());
        put(2, new EquationsStage1And2());
        put(3, new EquationsStage3());
    }};

    public static void main(String[] args) {
        runTestCases(1, 100);
        runTestCases(2, 150);
        runTestCases(3, 150);

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
        Set<Equation> solutions = SOLVE.get(testStage).solve(equation);
        String jsonSolution = toJson(solutions);
        submitSolutionFor(testStage, testCase, jsonSolution);
    }

}
