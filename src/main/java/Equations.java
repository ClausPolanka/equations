import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
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

    public static Set<String> solve(Equation equation) {
        return findCorrectEquations(equation.getLeftSide(), equation.getRightSide());
    }

    private static Set<String> findCorrectEquations(String digit1, String digit2) {
        Set<String> correctEquations = new HashSet<>();
        if (SOLUTION_SPACE.get(digit1).contains(digit2)) {
            correctEquations.add(toEquation(digit1));
            correctEquations.add(toEquation(digit2));
        }
        return correctEquations;
    }

    private static String toEquation(String digit) {
        return format("%s=%s", digit, digit);
    }

    private static String[] parse(String equation) {
        return equation.split("=");
    }

}
