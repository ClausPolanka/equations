package equation;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class EquationJsonConverter {

    private static final String EQUATION_REGEX = ".*(\\d=\\d).*";
    private static final Pattern EQUATION_PATTERN = Pattern.compile(EQUATION_REGEX);

    public static Equation toEquation(String jsonResponse) {
        Matcher m = EQUATION_PATTERN.matcher(jsonResponse);
        m.find();
        return new Equation(m.group(1));
    }

    public static String toJson(Set<Equation> equations) {
        String solutions = equations.stream()
                .map(equation -> format("\"%s=%s\"", equation.getLeftSide().toString(), equation.getRightSide()))
                .collect(Collectors.joining(", "));
        return format("{ \"correctedEquations\": [%s]}", solutions);
    }
}
