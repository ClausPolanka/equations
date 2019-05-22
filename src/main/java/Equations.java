import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Equations {

    public List<String> findCorrectEquations(String equation) {
        final String[] digits = parse(equation);
        if (digits[0].equals(digits[1])) {
            return asList(digits[0] + "=" + digits[1]);
        }
        return Collections.emptyList();

    }

    private String[] parse(String equation) {
        return equation.split("=");
    }

}
