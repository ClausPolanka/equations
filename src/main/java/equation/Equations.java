package equation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

public class Equations {

    private static final Map<String, List<String>> ALTERNATIVE_DIGITS = new HashMap<String, List<String>>() {{
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
        return Stream.of(
                createEquationsByChangingLeftSideOperandOneOf(equation),
                createEquationsByChangingLeftSideOperandTwoOf(equation),
                createEquationsByChangingRightSideOf(equation))
                .flatMap(i -> i)
                .filter(Equation::isCorrect)
                .collect(toSet());
    }

    private static Stream<Equation> createEquationsByChangingLeftSideOperandOneOf(Equation e) {
        return ALTERNATIVE_DIGITS.get(e.getLeftSideOperand1()).stream().map(e::withLeftSideOperand1);
    }

    private static Stream<Equation> createEquationsByChangingLeftSideOperandTwoOf(Equation e) {
        return ALTERNATIVE_DIGITS.get(e.getLeftSideOperand2()).stream().map(e::withLeftSideOperand2);
    }

    private static Stream<Equation> createEquationsByChangingRightSideOf(Equation e) {
        return ALTERNATIVE_DIGITS.get(e.getRightSide()).stream().map(e::withRightSide);
    }

}

