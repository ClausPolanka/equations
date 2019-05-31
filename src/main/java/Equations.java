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

    public Set<String> solve(String equation) {
        final String[] digits = parse(equation);
        return findCorrectEquations(digits[0], digits[1]);
    }

    private Set<String> findCorrectEquations(String digit1, String digit2) {
        Set<String> correctEquations = new HashSet<>();
        if (SOLUTION_SPACE.get(digit1).contains(digit2)) {
            correctEquations.add(toEquation(digit1));
            correctEquations.add(toEquation(digit2));
        }
        return correctEquations;
    }

    private String toEquation(String digit) {
        return digit + "=" + digit;
    }

    private String[] parse(String equation) {
        return equation.split("=");
    }

}
