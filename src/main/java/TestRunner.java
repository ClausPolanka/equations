import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {

    private static final String EQUATION_REGEX = ".*(\\d=\\d).*";
    private static final Pattern EQUATION_PATTERN = Pattern.compile(EQUATION_REGEX);

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            try {
                runTestCase(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void runTestCase(int testcase) throws IOException {
        HttpURLConnection getCon = createHttpGetConnection(testcase);
        String jsonResponse = executeRequest(getCon);
        Equation equation = convertJsonToEquation(jsonResponse);
        Set<Equation> solutions = Equations.solve(equation);
        String json = convertToJson(solutions);
        System.out.println(jsonResponse + " => " + equation + " => " + solutions);
        HttpURLConnection postCon = createHttpPostConnection(testcase, json);
        // executeRequest(postCon);
    }

    public static Equation convertJsonToEquation(String jsonResponse) {
        Matcher m = EQUATION_PATTERN.matcher(jsonResponse);
        m.find();
        return new Equation(m.group(1));
    }

    public static String convertToJson(Set<Equation> equations) {
        return "{ \"correctedEquations\": [\"1=1\"]}";
    }

    private static HttpURLConnection createHttpGetConnection(int testcase) throws IOException {
        URL url = new URL("http://localhost:8080/assignment/stage/1/testcase/" + testcase);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con;
    }

    private static String executeRequest(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        System.out.println(content);
        in.close();
        con.disconnect();
        return content.toString();
    }

    private static HttpURLConnection createHttpPostConnection(int testcase, String json) throws IOException {
        URL url = new URL("http://localhost:8080/assignment/stage/1/testcase/" + testcase);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
            return con;
        }
    }

}
