package equation;

import org.junit.Test;

import static equation.EquationSolver.solveEquation;

public class EquationSolverTest {

    @Test
    public void evalReturnsCorrectedEquationsForGivenInput() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(i + "=" + j + ": ");
                System.out.println(solveEquation(i + "=" + j));
            }
        }
    }
}
