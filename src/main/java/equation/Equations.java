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
        if (!equation.getLeftSide().isSingleDigit()) {
            SOLUTION_SPACE.get(equation.getLeftSideOperand1()).forEach(newOperand1 -> {
                Equation newEquation = equation.withLeftSideOperand1(newOperand1);
                if (newEquation.isCorrect()) {
                    correctEquations.add(newEquation);
                }
            });
            SOLUTION_SPACE.get(equation.getLeftSideOperand2()).forEach(newOperand2 -> {
                Equation newEquation = equation.withLeftSideOperand2(newOperand2);
                if (newEquation.isCorrect()) {
                    correctEquations.add(newEquation);
                }
            });
            SOLUTION_SPACE.get(equation.getRightSide()).forEach(newRightSide -> {
                Equation newEquation = equation.with(newRightSide);
                if (newEquation.isCorrect()) {
                    correctEquations.add(newEquation);
                }
            });

            return correctEquations;
        }
        if (SOLUTION_SPACE.get(equation.getLeftSide().toString()).contains(equation.getRightSide())) {
            correctEquations.add(Equation.newCorrectEquation(equation.getLeftSide().toString()));
            correctEquations.add(Equation.newCorrectEquation(equation.getRightSide()));
        }
        return correctEquations;
    }

}

