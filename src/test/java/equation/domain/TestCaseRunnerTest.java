package equation.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class TestCaseRunnerTest {

    @Test
    public void runRunsTestCasesForStageOne() {
        TestCaseRepository repo = mock(TestCaseRepository.class);
        TestCaseRunner runner = new TestCaseRunner(repo);
        int testCase = 1;

        when(repo.getEquationFor(testCase)).thenReturn(new Equation("0=0"));

        runner.run(testCase, testCase);

        verify(repo).submitCorrectedEquationsFor(testCase, toSet(new Equation("0=0")));
    }

    private Set<Equation> toSet(Equation equation) {
        Set<Equation> set = new HashSet<>();
        set.add(equation);
        return set;
    }
}
