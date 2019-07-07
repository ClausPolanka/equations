package equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Equations {

    private static final Map<String, List<String>> SOLUTION_SPACE = new HashMap<String, List<String>>() {{
        put("0", asList("0", "6", "9"));
        put("1", singletonList("1"));
        put("2", asList("2", "3"));
        put("3", asList("2", "3", "5"));
        put("4", singletonList("4"));
        put("5", asList("3", "5"));
        put("6", asList("0", "6", "9"));
        put("7", singletonList("7"));
        put("8", singletonList("8"));
        put("9", asList("0", "6", "9"));
    }};

    public static Set<Equation> solve(Equation equation) {
        return equation.hasSingleNumberLeftSide()
                ? solveStage1(equation)
                : solveStage2(equation);
    }

    private static Set<Equation> solveStage2(Equation equation) {
        Set<Equation> correctEquations = new HashSet<>();
        Expression oExp = equation.getLeftSideExpression();

        // [...] + 0 = 6
        SOLUTION_SPACE.get(oExp.leftOperand).forEach(s -> {
            Expression newExp = oExp.withNewLeftOperand(s);
            Equation newEquation = equation.withNewLeftSide(newExp.evaluate());
            if (newEquation.isCorrect()) {
                correctEquations.add(new Equation(newExp.toString(), equation.getRightSide()));
            }
        });

        // 0 + [...] = 6
        SOLUTION_SPACE.get(oExp.rightOperand).forEach(s -> {
            Expression newExp = oExp.withNewRightOperand(s);
            Equation newEquation = equation.withNewLeftSide(newExp.evaluate());
            if (newEquation.isCorrect()) {
                correctEquations.add(new Equation(newExp.toString(), equation.getRightSide()));
            }
        });

        // 6 + 3 = [...]
        SOLUTION_SPACE.get(equation.getRightSide()).forEach(s -> {
            String leftSide = oExp.evaluate();
            Equation newEquation = new Equation(leftSide + "=" + s);
            if (newEquation.isCorrect()) {
                correctEquations.add(new Equation(oExp + "=" + s));
            }
        });

        return correctEquations;
    }

    private static Set<Equation> solveStage1(Equation equation) {
        Set<Equation> correctEquations = new HashSet<>();
        if (SOLUTION_SPACE.get(equation.getLeftSide()).contains(equation.getRightSide())) {
            correctEquations.add(Equation.newCorrectEquation(equation.getLeftSide()));
            correctEquations.add(Equation.newCorrectEquation(equation.getRightSide()));
        }
        return correctEquations;
    }

}
