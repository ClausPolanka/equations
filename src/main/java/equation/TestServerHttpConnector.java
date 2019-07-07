package equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.format;

public class TestServerHttpConnector {

    private static HttpURLConnection createHttpGetConnection(int testStage, int testcase) throws IOException {
        URL url = new URL(format("http://localhost:8080/assignment/stage/%d/testcase/%d", testStage, testcase));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con;
    }

    public static String getAssignmentFor(int testStage, int testcase) throws IOException {
        HttpURLConnection con = createHttpGetConnection(testStage, testcase);
        StringBuffer content = executeRequest(con);
        return content.toString();
    }

    private static StringBuffer executeRequest(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content;
    }

    private static HttpURLConnection createHttpPostConnection(
            int testStage,
            int testcase,
            String json
    ) throws IOException {
        URL url = new URL("http://localhost:8080/assignment/stage/" + testStage + "/testcase/" + testcase);
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

    public static void submitSolutionFor(int testStage, int testcase, String json) throws IOException {
        HttpURLConnection con = createHttpPostConnection(testStage, testcase, json);
        executeRequest(con);
    }

}
