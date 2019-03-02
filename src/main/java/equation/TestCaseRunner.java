package equation;

import java.util.Set;

public class TestCaseRunner {

    private TestCaseRepository repo;

    public TestCaseRunner(TestCaseRepository repo) {
        this.repo = repo;
    }

    public void run(int firstTestCase, int lastTestCase) {
        for (int testCase = firstTestCase; testCase <= lastTestCase; testCase++) {
            Equation equation = repo.getEquationFor(testCase);
            Set<Equation> correctedEquations = EquationSolver.solve(equation);
            repo.submitCorrectedEquationsFor(testCase, correctedEquations);
        }
    }
}
