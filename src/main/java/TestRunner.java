import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

public class TestRunner {

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
        Equation equation = EquationJsonConverter.toEquation(jsonResponse);
        Set<Equation> solutions = Equations.solve(equation);
        String json = EquationJsonConverter.toJson(solutions);
        HttpURLConnection postCon = createHttpPostConnection(testcase, json);
        executeRequest(postCon);
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
