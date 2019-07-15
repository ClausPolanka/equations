package equation;

import java.util.*;
import java.util.stream.*;

import static java.util.Arrays.*;
import static java.util.Collections.*;

public class EquationsStage3 implements Equations {

    private static final Map<String, List<String>> ADDS = new HashMap<String, List<String>>() {{
        put("0", singletonList("8"));
        put("1", singletonList("7"));
        put("3", singletonList("9"));
        put("5", asList("6", "9"));
        put("6", singletonList("8"));
        put("9", singletonList("8"));
    }};

    private static final Map<String, List<String>> SUBS = new HashMap<String, List<String>>() {{
        put("6", singletonList("5"));
        put("7", singletonList("1"));
        put("8", asList("0", "6", "9"));
        put("9", asList("3", "5"));
    }};

    @Override
    public Set<Equation> solve(Equation equation) {
        Set<Equation> correctEquations = new EquationsStage1And2().solve(equation);

        List<String> addLSLO = ADDS.getOrDefault(equation.getLSLO(), emptyList());
        List<String> addLSRO = ADDS.getOrDefault(equation.getLSRO(), emptyList());
        List<String> addRS = ADDS.getOrDefault(equation.getRightSide(), emptyList());
        List<String> subLSLO = SUBS.getOrDefault(equation.getLSLO(), emptyList());
        List<String> subLSRO = SUBS.getOrDefault(equation.getLSRO(), emptyList());
        List<String> subRS = SUBS.getOrDefault(equation.getRightSide(), emptyList());

        addLSLO.forEach(a -> subLSRO.forEach(s -> {
            correctEquations.add(equation.withLSLO(a).withLSRO(s));
        }));

        addLSLO.forEach(a -> subRS.forEach(s -> {
            correctEquations.add(equation.withLSLO(a).withRightSide(s));
        }));

        addLSRO.forEach(a -> subLSLO.forEach(s -> {
            correctEquations.add(equation.withLSLO(s).withLSRO(a));
        }));

        addLSRO.forEach(a -> subRS.forEach(s -> {
            correctEquations.add(equation.withLSRO(a).withRightSide(s));
        }));

        addRS.forEach(a -> subLSLO.forEach(s -> {
            correctEquations.add(equation.withRightSide(a).withLSLO(s));
        }));

        addRS.forEach(a -> subLSRO.forEach(s -> {
            correctEquations.add(equation.withRightSide(a).withLSRO(s));
        }));

        return correctEquations.stream()
            .filter(Equation::isCorrect)
            .collect(Collectors.toSet());
    }
}
