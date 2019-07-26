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
                createEquationsByChangingRightSideOf(equation),
                createEquationsByStage3(equation))
                .flatMap(i -> i)
                .filter(Equation::isCorrect)
                .collect(toSet());
    }

    private static Stream<Equation> createEquationsByStage3(Equation equation) {
        return Stream.empty();
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

    public static Set<Equation> solveStage3(Equation equation) {
        Set<Equation> equations = solve(equation);

        HashMap<String, List<String>> alternativeDigitsAddSegment = new HashMap<String, List<String>>() {{
            put("1", asList("7"));
            put("3", asList("9"));
            put("6", asList("8"));
        }};
        HashMap<String, List<String>> alternativeDigitsRemoveSegment = new HashMap<String, List<String>>() {{
            put("6", asList("5"));
            put("8", asList("9"));
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

        return equations.stream()
                .filter(e -> e.isCorrect())
                .collect(toSet());
    }

}

