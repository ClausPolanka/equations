import java.util.List;

import static java.util.Arrays.asList;

public class Equations {

    public List<String> findCorrectEquations(String equation) {
        final String[] digits = parse(equation);

        return asList(digits[0] + "=" + digits[1]);

    }

    private String[] parse(String equation) {
        return equation.split("=");
    }

}
