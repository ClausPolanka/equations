package test.equation;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import equation.Equation;
import equation.EquationsStage1And2;
import equation.EquationsStage3;
import equation.EquationsStage4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ZohhakRunner.class)
public class EquationsTest {

    @TestWith({ "0=0", "1=1", "2=2", "3=3", "4=4", "5=5", "6=6", "7=7", "8=8", "9=9" })
    public void solveReturnsSolutionWithInputEquationWhereLeftSideIsSingleDigitAndEquationsIsAlreadyCorrect(
            String equation
    ) {
        Set<Equation> solution = new EquationsStage1And2().solve(new Equation(equation));

        assertThat(solution, is(equalTo(toSolution(new Equation(equation)))));
    }

    @TestWith({
            "0=1", "0=2", "0=3", "0=4", "0=5", "0=7", "0=8",
            "1=2", "1=3", "1=4", "1=5", "1=6", "1=7", "1=8", "1=9",
            "2=4", "2=5", "2=6", "2=7", "2=8", "2=9",
            "3=4", "3=6", "3=7", "3=8", "3=9",
            "4=5", "4=6", "4=7", "4=8", "4=9",
            "5=6", "5=7", "5=8", "5=9",
            "6=7", "6=8",
            "7=8", "7=9",
            "8=9"
    })
    public void solveReturnsSolutionWithEmptyListWhenThereIsNoSolution(String equation) {
        Set<Equation> solution = new EquationsStage1And2().solve(new Equation(equation));

        assertThat(solution.size(), is(0));
    }

    @TestWith({
            "0=6, 0=0, 6=6",
            "0=9, 0=0, 9=9",
            "2=3, 2=2, 3=3",
            "3=2, 2=2, 3=3",
            "3=5, 3=3, 5=5",
            "5=3, 3=3, 5=5",
            "6=0, 0=0, 6=6",
            "6=9, 6=6, 9=9",
            "9=0, 0=0, 9=9",
            "9=6, 6=6, 9=9"
    })
    public void solveReturnsSolutionWithCorrectEquations(
            String equation,
            String correctEquation1,
            String correctEquation2
    ) {
        Set<Equation> solution = new EquationsStage1And2().solve(new Equation(equation));

        assertThat(solution, is(equalTo(toSolution(new Equation(correctEquation1), new Equation(correctEquation2)))));
    }

    @Test
    public void solveReturnsSolutionsWithCorrectEquationsForEquationWithAddExpression() {
        Set<Equation> solution = new EquationsStage1And2().solve(new Equation("0+1=1"));

        assertThat(solution, is(equalTo(toSolution(new Equation("0+1=1")))));
    }

    @Test
    public void solveReturnsSolutionsWithCorrectEquationsForEquationWithSubtractExpression() {
        Set<Equation> solution = new EquationsStage1And2().solve(new Equation("2-1=1"));

        assertThat(solution, is(equalTo(toSolution(new Equation("2-1=1")))));
    }

    @Test
    public void solveReturnsSolutionsWithCorrectEquationsForIncorrectInputEquation() {
        Set<Equation> solution = new EquationsStage1And2().solve(new Equation("0+0=9"));

        assertThat(solution, is(equalTo(toSolution(
                new Equation("9+0=9"),
                new Equation("0+9=9"),
                new Equation("0+0=0")))));
    }

    @Test
    public void stage3AddOperand1RemoveOperand2() {
        Set<Equation> solution = new EquationsStage3().solve(new Equation("3-6=4"));

        assertThat(solution, is(equalTo(toSolution(new Equation("9-5=4")))));
    }

    @Test
    public void stage3RemoveOperand1AddOperand2() {
        Set<Equation> solution = new EquationsStage3().solve(new Equation("8-1=2"));

        assertThat(solution, is(equalTo(toSolution(new Equation("9-7=2")))));
    }

    @Test
    public void stage3AddOperand1RemoveRight() {
        Set<Equation> solution = new EquationsStage3().solve(new Equation("3-4=6"));

        assertThat(solution, is(equalTo(toSolution(new Equation("9-4=5")))));
    }

    @Test
    public void stage3RemoveOperand1AddRight() {
        Set<Equation> solution = new EquationsStage3().solve(new Equation("8-2=1"));

        assertThat(solution, is(equalTo(toSolution(new Equation("9-2=7")))));
    }

    @Test
    public void stage3AddOperand2RemoveRight() {
        Set<Equation> solution = new EquationsStage3().solve(new Equation("2+1=8"));

        assertThat(solution, is(equalTo(toSolution(new Equation("2+7=9")))));
    }

    @Test
    public void stage3RemoveOperand2AddRight() {
        Set<Equation> solution = new EquationsStage3().solve(new Equation("4+6=3"));

        assertThat(solution, is(equalTo(toSolution(new Equation("4+5=9")))));
    }

    @Test
    public void solveStage4() {
        Set<Equation> solution = new EquationsStage4().solve(new Equation("2-1=1"));

        assertThat(solution, is(equalTo(toSolution(new Equation("2-1=1")))));
    }

    @Test
    public void solveStage4FlippingOperator() {
        Set<Equation> solution = new EquationsStage4().solve(new Equation("1-1=2"));

        assertThat(solution, is(equalTo(toSolution(new Equation("1+1=2")))));
    }

    private HashSet<Equation> toSolution(Equation... equations) {
        return new HashSet<>(Arrays.asList(equations));
    }

}
