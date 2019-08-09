package equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toSet;

public class EquationsStage3 implements Equations {

    private static final Map<String, List<String>> ALTERNATIVE_DIGITS_ADD_SEGMENT = new HashMap<String, List<String>>() {{
        put("0", singletonList("8"));
        put("1", singletonList("7"));
        put("3", singletonList("9"));
        put("5", asList("6", "9"));
        put("6", singletonList("8"));
        put("9", singletonList("8"));
    }};
    private static final Map<String, List<String>> ALTERNATIVE_DIGITS_REMOVE_SEGMENT = new HashMap<String, List<String>>() {{
        put("6", singletonList("5"));
        put("7", singletonList("1"));
        put("8", asList("0", "6", "9"));
        put("9", asList("3", "5"));
    }};

    @Override
    public Set<Equation> solve(Equation equation) {
        Set<Equation> equations = new EquationsStage1And2().solve(equation);
        equations.addAll(solveStage3(equation));
        return equations.stream()
                .filter(Equation::isCorrect)
                .collect(toSet());
    }

    private Set<Equation> solveStage3(Equation equation) {
        Set<Equation> equations = new HashSet<>();
        List<String> addSegmentsO1 = ALTERNATIVE_DIGITS_ADD_SEGMENT.getOrDefault(equation.getLeftSideOperand1(), emptyList());
        List<String> addSegmentsO2 = ALTERNATIVE_DIGITS_ADD_SEGMENT.getOrDefault(equation.getLeftSideOperand2(), emptyList());
        List<String> addSegmentsRS = ALTERNATIVE_DIGITS_ADD_SEGMENT.getOrDefault(equation.getRightSide(), emptyList());
        List<String> removeSegmentsO1 = ALTERNATIVE_DIGITS_REMOVE_SEGMENT.getOrDefault(equation.getLeftSideOperand1(), emptyList());
        List<String> removeSegmentsO2 = ALTERNATIVE_DIGITS_REMOVE_SEGMENT.getOrDefault(equation.getLeftSideOperand2(), emptyList());
        List<String> removeSegmentsRS = ALTERNATIVE_DIGITS_REMOVE_SEGMENT.getOrDefault(equation.getRightSide(), emptyList());

        addSegmentsO1.forEach(ao1 ->
                removeSegmentsO2.forEach(ro2 ->
                        equations.add(equation.withLeftSideOperand1(ao1).withLeftSideOperand2(ro2))));
        removeSegmentsO1.forEach(ro1 ->
                addSegmentsO2.forEach(ao2 ->
                        equations.add(equation.withLeftSideOperand1(ro1).withLeftSideOperand2(ao2))));
        addSegmentsO1.forEach(ao1 ->
                removeSegmentsRS.forEach(rrs ->
                        equations.add(equation.withLeftSideOperand1(ao1).withRightSide(rrs))));
        addSegmentsRS.forEach(ars ->
                removeSegmentsO1.forEach(ro1 ->
                        equations.add(equation.withLeftSideOperand1(ro1).withRightSide(ars))));
        addSegmentsO2.forEach(ao2 ->
                removeSegmentsRS.forEach(rrs ->
                        equations.add(equation.withLeftSideOperand2(ao2).withRightSide(rrs))));
        addSegmentsRS.forEach(ars ->
                removeSegmentsO2.forEach(ro2 ->
                        equations.add(equation.withLeftSideOperand2(ro2).withRightSide(ars))));
        return equations;
    }

}
