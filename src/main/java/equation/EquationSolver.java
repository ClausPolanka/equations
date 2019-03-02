package equation;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class EquationSolver {

    private static Hashtable<String, List<String>> solutionSpace = solutions();

    private static Hashtable<String, List<String>> solutions() {
        Hashtable<String, List<String>> solutionSpace = new Hashtable<>();
        solutionSpace.put("0", asList("0", "6", "9"));
        solutionSpace.put("1", singletonList("1"));
        solutionSpace.put("2", asList("2", "3"));
        solutionSpace.put("3", asList("2", "3", "5"));
        solutionSpace.put("4", singletonList("4"));
        solutionSpace.put("5", asList("3", "5"));
        solutionSpace.put("6", asList("0", "6", "9"));
        solutionSpace.put("7", singletonList("7"));
        solutionSpace.put("8", singletonList("8"));
        solutionSpace.put("9", asList("0", "6", "9"));
        return solutionSpace;
    }

    public static Set<String> solveEquation(Equation e) {
        Set<String> correctedEquations = new HashSet<>();

        if (solutionSpace.get(e.leftSide()).contains(e.rightSide()))
            correctedEquations.add(format("\"%s=%s\"", e.rightSide(), e.rightSide()));

        if (solutionSpace.get(e.rightSide()).contains(e.leftSide()))
            correctedEquations.add(format("\"%s=%s\"", e.leftSide(), e.leftSide()));

        return correctedEquations;
    }
}
