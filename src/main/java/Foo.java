import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class Foo {

    public static void main(String[] args) throws Exception {
        for (int testCase = 1; testCase <= 100; testCase++) {
            String equation = getEquationFor(testCase);
            System.out.println(equation + ": " + solveEquation(equation.split("=")[0], equation.split("=")[1]));
        }
    }

    public static String getEquationFor(int testCase) throws IOException {
        URL url = new URL("http", "localhost", 8080, "/assignment/stage/1/testcase/" + testCase);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        Pattern p = Pattern.compile(".*(\\d)=(\\d).*");
        Matcher m = p.matcher(content);
        m.matches();
        return m.group(1) + "=" + m.group(2);
    }

    public static Set<String> solveEquation(String left, String right) {
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
            correctedEquations.add("\"" + right + "=" + right + "\"");
        if (solutionSpace.get(right).contains(left))
            correctedEquations.add("\"" + left + "=" + left + "\"");
        return correctedEquations;
    }
}
