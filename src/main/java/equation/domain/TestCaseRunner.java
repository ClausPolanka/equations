package equation.domain;

import java.util.Set;
import java.util.stream.IntStream;

public class TestCaseRunner {

    private TestCaseRepository repo;

    public TestCaseRunner(TestCaseRepository repo) {
        this.repo = repo;
    }

    public void run(int firstTestCase, int lastTestCase) {
        IntStream.range(firstTestCase, lastTestCase + 1).forEach(testCase -> {
            Equation equation = repo.getEquationFor(testCase);
            Set<Equation> correctedEquations = EquationSolver.solve(equation);
            repo.submitCorrectedEquationsFor(testCase, correctedEquations);
        });
    }
}
