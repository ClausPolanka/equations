package equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestServerHttpConnector {

    private static HttpURLConnection createHttpGetConnection(int testcase) throws IOException {
        URL url = new URL("http://localhost:8080/assignment/stage/1/testcase/" + testcase);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con;
    }

    public static String getAssignmentFor(int testcase) throws IOException {
        HttpURLConnection con = createHttpGetConnection(testcase);
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

    public static void submitSolutionFor(int testcase, String json) throws IOException {
        HttpURLConnection con = createHttpPostConnection(testcase, json);
        executeRequest(con);
    }

}
