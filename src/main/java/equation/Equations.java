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
        Set<Equation> correctEquations = new HashSet<>();
        if (equation.getLeftSide().length() > 1) {
            // equation.getLeftSide() => parse => Expression
            Expression e = new Expression(equation.getLeftSide());

            SOLUTION_SPACE.get(e.operand1).forEach(s -> {
                Expression newLeftSide = new Expression(s, e.operator, e.operand2);
                Equation newEquation = equation.with(newLeftSide);
                if (newEquation.isCorrect()) {
                    correctEquations.add(newEquation);
                }
            });

            SOLUTION_SPACE.get(e.operand2).forEach(s -> {
                Expression newLeftSide = new Expression(e.operand1, e.operator, s);
                Equation newEquation = equation.with(newLeftSide);
                if (newEquation.isCorrect()) {
                    correctEquations.add(newEquation);
                }
            });

            SOLUTION_SPACE.get(equation.getRightSide()).forEach(s -> {
                Equation newEquation = new Equation(e, s);
                if (newEquation.isCorrect()) {
                    correctEquations.add(newEquation);
                }
            });

            return correctEquations;
        }
        if (SOLUTION_SPACE.get(equation.getLeftSide()).contains(equation.getRightSide())) {
            correctEquations.add(Equation.newCorrectEquation(equation.getLeftSide()));
            correctEquations.add(Equation.newCorrectEquation(equation.getRightSide()));
        }
        return correctEquations;
    }

}
