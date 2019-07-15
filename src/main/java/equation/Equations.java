package equation;

import java.util.*;
import java.util.stream.*;

import static java.util.Arrays.*;
import static java.util.Collections.*;

public class Equations {

    private static final Map<String, List<String>> SOLUTION_SPACE = new HashMap<String, List<String>>() {{
        put("", emptyList());
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
        Set<Equation> correctEquations = stage2(equation);
        return correctEquations.stream()
            .filter(Equation::isCorrect)
            .collect(Collectors.toSet());
    }

    private static Set<Equation> stage2(Equation equation) {
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
        return correctEquations;
    }

}
