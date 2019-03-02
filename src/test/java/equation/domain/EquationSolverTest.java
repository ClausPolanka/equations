package equation.domain;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EquationSolverTest {

    @Test
    public void solveReturnsCorrectedEquationsForGivenEquation() {
        Set<Equation> correctedEquations = EquationSolver.solve(new Equation("0=0"));
        assertEquals("correctd equations size", 1, correctedEquations.size());
        assertEquals("correctd equations", new Equation("0=0"), correctedEquations.iterator().next());
    }
}
