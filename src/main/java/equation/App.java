package equation;

import java.util.Set;

public class App {

    public static void main(String[] args) {
        for (int testCase = 1; testCase <= 100; testCase++) {
            TestCaseRepository repo = new HttpTestCaseRepository();
            Equation equation = repo.getEquationFor(testCase);
            Set<Equation> correctedEquations = EquationSolver.solve(equation);
            repo.submitCorrectedEquationsFor(testCase, correctedEquations);
        }
    }
}
