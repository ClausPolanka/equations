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

    private static final Map<String, List<String>> MOVE_SEGMENT_TO_DIGIT_ALTERNATIVES = new HashMap<String, List<String>>() {{
        put("0", singletonList("8"));
        put("1", singletonList("7"));
        put("3", singletonList("9"));
        put("5", asList("6", "9"));
        put("6", singletonList("8"));
        put("9", singletonList("8"));
    }};
    private static final Map<String, List<String>> MOVE_SEGMENT_FROM_DIGIT_ALTERNATIVES = new HashMap<String, List<String>>() {{
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

        moveSegmentTo(equation.getLeftSideOperand1()).forEach(o1 ->
                moveSegmentFrom(equation.getLeftSideOperand2()).forEach(o2 ->
                        equations.add(equation.withLeftSideOperand1(o1).withLeftSideOperand2(o2))));

        moveSegmentTo(equation.getLeftSideOperand2()).forEach(o2 ->
                moveSegmentFrom(equation.getLeftSideOperand1()).forEach(o1 ->
                        equations.add(equation.withLeftSideOperand1(o1).withLeftSideOperand2(o2))));

        moveSegmentTo(equation.getLeftSideOperand1()).forEach(o1 ->
                moveSegmentFrom(equation.getRightSide()).forEach(rs ->
                        equations.add(equation.withLeftSideOperand1(o1).withRightSide(rs))));

        moveSegmentTo(equation.getRightSide()).forEach(rs ->
                moveSegmentFrom(equation.getLeftSideOperand1()).forEach(o1 ->
                        equations.add(equation.withLeftSideOperand1(o1).withRightSide(rs))));

        moveSegmentTo(equation.getLeftSideOperand2()).forEach(o2 ->
                moveSegmentFrom(equation.getRightSide()).forEach(rs ->
                        equations.add(equation.withLeftSideOperand2(o2).withRightSide(rs))));

        moveSegmentTo(equation.getRightSide()).forEach(rs ->
                moveSegmentFrom(equation.getLeftSideOperand2()).forEach(o2 ->
                        equations.add(equation.withLeftSideOperand2(o2).withRightSide(rs))));

        return equations;
    }

    private List<String> moveSegmentTo(String digit) {
        return MOVE_SEGMENT_TO_DIGIT_ALTERNATIVES.getOrDefault(digit, emptyList());
    }

    private List<String> moveSegmentFrom(String digit) {
        return MOVE_SEGMENT_FROM_DIGIT_ALTERNATIVES.getOrDefault(digit, emptyList());
    }

}
