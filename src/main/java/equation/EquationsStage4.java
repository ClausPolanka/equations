package equation;

import java.util.Set;

public class EquationsStage4 implements Equations {

    @Override
    public Set<Equation> solve(Equation equation) {
        final Set<Equation> equations = new EquationsStage3().solve(equation);
        equations.addAll(new EquationsStage3().solve(equation.withFlippedLeftSideOperator()));
        return equations;
    }

}
