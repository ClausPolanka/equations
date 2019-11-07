package equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

public class TestServerHttpConnector {

    private static final String TEST_SERVER_URL_FORMAT = "http://localhost:8080/assignment/stage/%d/testcase/%d";

    public static String getAssignmentFor(int stage, int testcase) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format(TEST_SERVER_URL_FORMAT, stage, testcase)))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, BodyHandlers.ofString()); // throws InterruptedException

        return response.body();
    }

    private static StringBuffer executeRequest(HttpURLConnection con) throws IOException {
        StringBuffer content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) { // throws IOException
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) { // throws IOException
                content.append(inputLine);
            }
        }
        con.disconnect();
        return content;
    }

    private static HttpURLConnection createHttpPostConnection(int stage, int testcase, String json) throws IOException {
        URL url = new URL(format(TEST_SERVER_URL_FORMAT, stage, testcase)); // throws MalformedURLException
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); // throws IOException
        con.setRequestMethod("POST"); // throws ProtocolException
        con.setRequestProperty("Content-Type", "application/json; utf-8"); // throws IllegalStateException and NullPointerException
        con.setDoOutput(true); // throws IllegalStateException
        try (OutputStream os = con.getOutputStream()) { // throws IOException
            byte[] input = json.getBytes(UTF_8); // throws UnsupportedEncodingException
            os.write(input, 0, input.length); // throws IOException
            return con;
        }
    }

    public static void submitSolutionFor(
            int stage,
            int testcase,
            String json
    ) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format(TEST_SERVER_URL_FORMAT, stage, testcase)))
                .POST(BodyPublishers.ofString(json))
                .build();

        HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
    }

}
