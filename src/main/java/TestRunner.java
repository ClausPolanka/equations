import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        HttpURLConnection con = createHttpGetConnection(testcase);

        String jsonResponse = executeRequest(con);
        Equation equation = convertJSONtoEquation(jsonResponse);
        System.out.println(jsonResponse);
    }

    public static Equation convertJSONtoEquation(String jsonResponse) {
        final String regex = ".*(\\d=\\d).*";
        return new Equation("0=9");
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
