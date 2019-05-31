import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

public class Equations {

    public Set<String> findCorrectEquations(String equation) {
        final String[] digits = parse(equation);
        return findCorrectEquations(digits[0], digits[1]);

    }

    private Set<String> findCorrectEquations(String digit1, String digit2) {
        Map<String, List<String>> solutions = new HashMap<>();
        solutions.put("0", asList("0"));
        solutions.put("1", asList("1"));
        solutions.put("2", asList("2", "3"));
        solutions.put("3", asList("2", "3"));
        solutions.put("4", asList("4"));
        solutions.put("5", asList("5"));
        solutions.put("6", asList("6"));
        solutions.put("7", asList("7"));
        solutions.put("8", asList("8"));
        solutions.put("9", asList("9"));

        Set<String> correctEquations = new HashSet<>();
        if (solutions.get(digit1).contains(digit2)) {
            correctEquations.add(digit1 + "=" + digit1);
            correctEquations.add(digit2 + "=" + digit2);
        }
        return correctEquations;
    }

    private String[] parse(String equation) {
        return equation.split("=");
    }

}
