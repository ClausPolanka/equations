package equation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static equation.EquationJsonConverter.toEquation;
import static equation.EquationJsonConverter.toJson;
import static equation.TestServerHttpConnector.getAssignmentFor;
import static equation.TestServerHttpConnector.submitSolutionFor;
import static java.util.stream.IntStream.range;

public class TestRunner {

    private static final Map<Integer, Equations> EQUATIONS = new HashMap<Integer, Equations>() {{
        put(1, new EquationsStage1And2());
        put(2, new EquationsStage1And2());
        put(3, new EquationsStage3());
    }};

    public static void main(String[] args) {
        runTestCases(1, 101);
        runTestCases(2, 151);
        runTestCases(3, 151);
    }

    private static void runTestCases(int stage, int lastTestCase) {
        range(1, lastTestCase).forEach(testCase -> {
            try {
                runTestCase(stage, testCase);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void runTestCase(int stage, int testCase) throws IOException {
        String jsonAssignment = getAssignmentFor(stage, testCase);
        Equation equation = toEquation(jsonAssignment);
        Set<Equation> solutions = EQUATIONS.get(stage).solve(equation);
        String jsonSolution = toJson(solutions);
        submitSolutionFor(stage, testCase, jsonSolution);
    }

}
