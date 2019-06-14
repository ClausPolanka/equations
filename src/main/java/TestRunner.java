import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        HttpURLConnection con = createHttpGetConnection(testcase);
        String jsonResponse = executeRequest(con);
        Equation equation = convertJsonToEquation(jsonResponse);
        System.out.println(jsonResponse + " => " + equation);
    }

    public static Equation convertJsonToEquation(String jsonResponse) {
        Matcher m = EQUATION_PATTERN.matcher(jsonResponse);
        m.find();
        return new Equation(m.group(1));
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

}
