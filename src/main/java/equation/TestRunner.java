package equation;

import java.io.IOException;
import java.util.Set;

import static equation.EquationJsonConverter.*;
import static equation.Equations.*;
import static equation.TestServerHttpConnector.executeGetRequest;
import static equation.TestServerHttpConnector.executePostRequest;

public class TestRunner {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            try {
                runTestCase(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void runTestCase(int testcase) throws IOException {
        String jsonResponse = executeGetRequest(testcase);
        Equation equation = toEquation(jsonResponse);
        Set<Equation> solutions = solve(equation);
        String json = toJson(solutions);
        executePostRequest(testcase, json);
    }

}
