package equation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Equations {

    private static final Map<String, List<String>> SOLUTION_SPACE = new HashMap<String, List<String>>() {{
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
        return equation.hasSingleNumberLeftSide()
                ? solveStage1(equation)
                : solveStage2(equation);
    }

    private static Set<Equation> solveStage2(Equation equation) {
        Set<Equation> correctEquations = new HashSet<>();

        Expression e = new Expression(equation.getLeftSide());

        // [...] + 0 = 6
        List<String> solutions = SOLUTION_SPACE.get(e.operand1);
        for (String solution : solutions) {
            Expression exp = new Expression(solution + e.operator + e.operand2);
            String r = exp.evaluate();
            if (r.equals(equation.getRightSide())) {
                correctEquations.add(new Equation(exp + "=" + equation.getRightSide()));
            }
        }

        // 0 + [...] = 6
        solutions = SOLUTION_SPACE.get(e.operand2);
        for (String solution : solutions) {
            Expression exp = new Expression(e.operand1 + e.operator + solution);
            String r = exp.evaluate();
            if (r.equals(equation.getRightSide())) {
                correctEquations.add(new Equation(exp + "=" + equation.getRightSide()));
            }
        }

        // 6 + 3 = [...]
        solutions = SOLUTION_SPACE.get(equation.getRightSide());
        for (String solution : solutions) {
            String r = e.evaluate();
            if (r.equals(solution)) {
                correctEquations.add(new Equation(e + "=" + solution));
            }
        }

        return correctEquations;
    }

    private static Set<Equation> solveStage1(Equation equation) {
        Set<Equation> correctEquations = new HashSet<>();
        if (SOLUTION_SPACE.get(equation.getLeftSide()).contains(equation.getRightSide())) {
            correctEquations.add(Equation.newCorrectEquation(equation.getLeftSide()));
            correctEquations.add(Equation.newCorrectEquation(equation.getRightSide()));
        }
        return correctEquations;
    }

}
