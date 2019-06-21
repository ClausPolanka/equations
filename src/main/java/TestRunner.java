import java.io.IOException;
import java.util.Set;

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
        String jsonResponse = TestServerHttpConnector.executeGetRequest(testcase);
        Equation equation = EquationJsonConverter.toEquation(jsonResponse);
        Set<Equation> solutions = Equations.solve(equation);
        String json = EquationJsonConverter.toJson(solutions);
        TestServerHttpConnector.executePostRequest(testcase, json);
    }

}
