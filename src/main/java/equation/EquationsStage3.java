package equation;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;

public class EquationsStage3 implements Equations {

    @Override
    public Set<Equation> solve(Equation equation) {
        Set<Equation> equations = new EquationsStage1And2().solve(equation);

        HashMap<String, List<String>> alternativeDigitsAddSegment = new HashMap<String, List<String>>() {{
            put("0", asList("8"));
            put("1", asList("7"));
            put("3", asList("9"));
            put("5", asList("6", "9"));
            put("6", asList("8"));
            put("9", asList("8"));
        }};
        HashMap<String, List<String>> alternativeDigitsRemoveSegment = new HashMap<String, List<String>>() {{
            put("6", asList("5"));
            put("7", asList("1"));
            put("8", asList("0", "6", "9"));
            put("9", asList("3", "5"));
        }};

        List<String> addSegments = alternativeDigitsAddSegment.getOrDefault(equation.getLeftSideOperand1(), emptyList());
        List<String> removeSegments = alternativeDigitsRemoveSegment.getOrDefault(equation.getLeftSideOperand2(), emptyList());

        for (String addSegmentO1 : addSegments) {
            for (String removeSegmentO2 : removeSegments) {
                equations.add(equation.withLeftSideOperand1(addSegmentO1).withLeftSideOperand2(removeSegmentO2));
            }
        }

        addSegments = alternativeDigitsAddSegment.getOrDefault(equation.getLeftSideOperand2(), emptyList());
        removeSegments = alternativeDigitsRemoveSegment.getOrDefault(equation.getLeftSideOperand1(), emptyList());

        for (String removeSegmentO1 : removeSegments) {
            for (String addSegmentO2 : addSegments) {
                equations.add(equation.withLeftSideOperand1(removeSegmentO1).withLeftSideOperand2(addSegmentO2));
            }
        }
        addSegments = alternativeDigitsAddSegment.getOrDefault(equation.getLeftSideOperand1(), emptyList());
        removeSegments = alternativeDigitsRemoveSegment.getOrDefault(equation.getRightSide(), emptyList());

        for (String addSegmentO1 : addSegments) {
            for (String removeSegmentRight : removeSegments) {
                equations.add(equation.withLeftSideOperand1(addSegmentO1).withRightSide(removeSegmentRight));
            }
        }

        addSegments = alternativeDigitsAddSegment.getOrDefault(equation.getRightSide(), emptyList());
        removeSegments = alternativeDigitsRemoveSegment.getOrDefault(equation.getLeftSideOperand1(), emptyList());

        for (String addRightSide : addSegments) {
            for (String removeSegmentO1 : removeSegments) {
                equations.add(equation.withLeftSideOperand1(removeSegmentO1).withRightSide(addRightSide));
            }
        }

        addSegments = alternativeDigitsAddSegment.getOrDefault(equation.getLeftSideOperand2(), emptyList());
        removeSegments = alternativeDigitsRemoveSegment.getOrDefault(equation.getRightSide(), emptyList());

        for (String addSegmentO2 : addSegments) {
            for (String removeRightSide : removeSegments) {
                equations.add(equation.withLeftSideOperand2(addSegmentO2).withRightSide(removeRightSide));
            }
        }

        addSegments = alternativeDigitsAddSegment.getOrDefault(equation.getRightSide(), emptyList());
        removeSegments = alternativeDigitsRemoveSegment.getOrDefault(equation.getLeftSideOperand2(), emptyList());

        for (String addRightside : addSegments) {
            for (String removeSegmentO2 : removeSegments) {
                equations.add(equation.withLeftSideOperand2(removeSegmentO2).withRightSide(addRightside));
            }
        }

        return equations.stream()
                .filter(e -> e.isCorrect())
                .collect(toSet());
    }

}
