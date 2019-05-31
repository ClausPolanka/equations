import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Equations {

    private static final Map<String, List<String>> SOLUTION_SPACE = createSolutionSpace();

    private static Map<String, List<String>> createSolutionSpace() {
        Map<String, List<String>> solutions = new HashMap<>();
        solutions.put("0", asList("0", "6", "9"));
        solutions.put("1", singletonList("1"));
        solutions.put("2", asList("2", "3"));
        solutions.put("3", asList("2", "3", "5"));
        solutions.put("4", singletonList("4"));
        solutions.put("5", asList("3", "5"));
        solutions.put("6", asList("0", "6", "9"));
        solutions.put("7", singletonList("7"));
        solutions.put("8", singletonList("8"));
        solutions.put("9", asList("0", "6", "9"));
        return solutions;
    }

    public Set<String> solve(String equation) {
        final String[] digits = parse(equation);
        return findCorrectEquations(digits[0], digits[1]);
    }

    private Set<String> findCorrectEquations(String digit1, String digit2) {
        Set<String> correctEquations = new HashSet<>();
        if (SOLUTION_SPACE.get(digit1).contains(digit2)) {
            correctEquations.add(digit1 + "=" + digit1);
            correctEquations.add(digit2 + "=" + digit2);
        }
        return correctEquations;
    }

    private String[] parse(String equation) {
        return equation.split("=");
    }

}
