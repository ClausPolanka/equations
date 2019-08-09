package equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.format;

public class TestServerHttpConnector {

    public static final String TEST_SERVER_URL_FORMAT = "http://localhost:8080/assignment/stage/%d/testcase/%d";

    public static String getAssignmentFor(int stage, int testcase) throws IOException {
        HttpURLConnection con = createHttpGetConnection(stage, testcase); // throws IOException
        StringBuffer content = executeRequest(con); // throws IOException
        return content.toString();
    }

    private static HttpURLConnection createHttpGetConnection(int stage, int testcase) throws IOException {
        URL url = new URL(format(TEST_SERVER_URL_FORMAT, stage, testcase)); // throws MalformedURLException
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); // throws IOException
        con.setRequestMethod("GET"); // throws ProtocolException
        return con;
    }

    private static StringBuffer executeRequest(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // throws IOException
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) { // throws IOException
            content.append(inputLine);
        }
        in.close(); // throws IOException
        con.disconnect();
        return content;
    }

    private static HttpURLConnection createHttpPostConnection(int stage, int testcase, String json) throws IOException {
        URL url = new URL(format(TEST_SERVER_URL_FORMAT, stage, testcase));
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

    public static void submitSolutionFor(int stage, int testcase, String json) throws IOException {
        HttpURLConnection con = createHttpPostConnection(stage, testcase, json);
        executeRequest(con);
    }

}
