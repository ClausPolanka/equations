package equation.io;

import equation.domain.Equation;
import equation.domain.TestCaseRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class HttpTestCaseRepository implements TestCaseRepository {

    private static final String PROTOCOL = "http";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final Pattern PATTERN = Pattern.compile(".*(\\d)=(\\d).*");

    public Equation getEquationFor(int testCase) {
        try {
            HttpURLConnection con = buildGetRequestConFor(testCase);
            String responseBody = readResponseBodyFrom(con);
            con.disconnect();
            Matcher m = PATTERN.matcher(responseBody);
            m.matches();
            return new Equation(format("%s=%s", m.group(1), m.group(2)));
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        } catch (ProtocolException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private HttpURLConnection buildGetRequestConFor(int testCase) throws IOException {
        URL url = new URL(PROTOCOL, HOST, PORT, pathToTestCase() + testCase);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con;
    }

    private String readResponseBodyFrom(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder responseBody = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            responseBody.append(inputLine);
        }
        in.close();
        return responseBody.toString();
    }

    private String pathToTestCase() {
        return "/assignment/stage/1/testcase/";
    }

    public void submitCorrectedEquationsFor(int testCase, Set<Equation> correctedEquations) {
        try {
            HttpURLConnection con = buildPostRequestConFor(testCase);
            StringBuilder requestBody = buildRequestBodyFor(correctedEquations);
            con.setFixedLengthStreamingMode(requestBody.length());
            con.connect();
            try (OutputStream os = con.getOutputStream()) {
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private HttpURLConnection buildPostRequestConFor(int testCase) throws IOException {
        URL url = new URL(PROTOCOL, HOST, PORT, pathToTestCase() + testCase);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        return con;
    }

    private StringBuilder buildRequestBodyFor(Set<Equation> correctedEquations) {
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("{");
        requestBody.append("\"correctedEquations\":").append(correctedEquations);
        requestBody.append("}");
        return requestBody;
    }
}
