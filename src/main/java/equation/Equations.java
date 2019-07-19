package equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

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
        Set<Equation> equations = new HashSet<>();
        SOLUTION_SPACE.get(equation.getLeftSideOperand1()).forEach(o1 -> {
            equations.add(equation.withLeftSideOperand1(o1));
        });
        SOLUTION_SPACE.get(equation.getLeftSideOperand2()).forEach(o2 -> {
            equations.add(equation.withLeftSideOperand2(o2));
        });
        SOLUTION_SPACE.get(equation.getRightSide()).forEach(rs -> {
            equations.add(equation.withRightSide(rs));
        });
        return equations.stream()
                .filter(Equation::isCorrect)
                .collect(toSet());
    }

}

