package equation;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class EquationSolver {

    private static Hashtable<String, List<String>> solutionSpace = solutions();

    private static Hashtable<String, List<String>> solutions() {
        Hashtable<String, List<String>> solutionSpace = new Hashtable<>();
        solutionSpace.put("0", asList("0", "6", "9"));
        solutionSpace.put("1", asList("1"));
        solutionSpace.put("2", asList("2", "3"));
        solutionSpace.put("3", asList("2", "3", "5"));
        solutionSpace.put("4", asList("4"));
        solutionSpace.put("5", asList("3", "5"));
        solutionSpace.put("6", asList("0", "6", "9"));
        solutionSpace.put("7", asList("7"));
        solutionSpace.put("8", asList("8"));
        solutionSpace.put("9", asList("0", "6", "9"));
        return solutionSpace;
    }

    public static Set<String> solveEquation(String equation) {
        Set<String> correctedEquations = new HashSet<>();

        if (solutionSpace.get(leftSideOf(equation)).contains(rightSideOf(equation)))
            correctedEquations.add(String.format("\"%s=%s\"", rightSideOf(equation), rightSideOf(equation)));

        if (solutionSpace.get(rightSideOf(equation)).contains(leftSideOf(equation)))
            correctedEquations.add(String.format("\"%s=%s\"", leftSideOf(equation), leftSideOf(equation)));

        return correctedEquations;
    }

    public static String leftSideOf(String equation) {
        return equation.split("=")[0];
    }

    public static String rightSideOf(String equation) {
        return equation.split("=")[1];
    }
}
