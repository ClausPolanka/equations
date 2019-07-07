package equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

        SOLUTION_SPACE.get(equation.getLSLO()).forEach(s -> {
            correctEquations.add(equation.withLSLO(s));
        });

        SOLUTION_SPACE.get(equation.getLSRO()).forEach(s -> {
            correctEquations.add(equation.withLSRO(s));
        });

        SOLUTION_SPACE.get(equation.getRightSide()).forEach(s -> {
            correctEquations.add(equation.withRightSide(s));
        });

        return correctEquations.stream()
                .filter(Equation::isCorrect)
                .collect(Collectors.toSet());
    }

    private static Set<Equation> solveStage1(Equation equation) {
        Set<Equation> correctEquations = new HashSet<>();
        if (SOLUTION_SPACE.get(equation.getLeftSide().toString()).contains(equation.getRightSide())) {
            correctEquations.add(Equation.newCorrectEquation(equation.getLeftSide().toString()));
            correctEquations.add(Equation.newCorrectEquation(equation.getRightSide()));
        }
        return correctEquations;
    }

}
