import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Equations {

    public List<String> findCorrectEquations(String equation) {
        final String[] digits = parse(equation);
        if (digits[0].equals(digits[1])) {
            return asList(digits[0] + "=" + digits[1]);
        }
        return findCorrectEquations(digits[0], digits[1]);

    }

    private List<String> findCorrectEquations(String digit1, String digit2) {
        Map<String, List<String>> solutions = new HashMap<>();
        solutions.put("0", emptyList());
        solutions.put("2", asList("3"));
        solutions.put("3", asList("2"));
        List<String> correctEquations = new ArrayList<>();
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
