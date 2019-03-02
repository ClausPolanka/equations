import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class Foo {

    public static void main(String[] args) {
        eval("0", "6");
    }

    public static Set<String> eval(String left, String right) {
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

        Set<String> correctedEquations = new HashSet<>();
        if (solutionSpace.get(left).contains(right))
            correctedEquations.add(right + "=" + right);
        if (solutionSpace.get(right).contains(left))
            correctedEquations.add(left + "=" + left);
        return correctedEquations;
    }
}
