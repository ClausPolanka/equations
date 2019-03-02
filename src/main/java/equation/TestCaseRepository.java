package equation;

import java.util.Set;

public interface TestCaseRepository {

    Equation getEquationFor(int testCase);

    void submitCorrectedEquationsFor(int testCase, Set<Equation> correctedEquations);
}
