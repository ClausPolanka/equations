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

public class HttpTestCaseRepository implements TestCaseRepository {

    public Equation getEquationFor(int testCase) {
        try {
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
            return new Equation(m.group(1) + "=" + m.group(2));
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        } catch (ProtocolException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void submitCorrectedEquationsFor(int testCase, Set<Equation> correctedEquations) {
        try {
            URL url = new URL("http", "localhost", 8080, "/assignment/stage/1/testcase/" + testCase);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            StringBuilder requestBody = new StringBuilder();
            requestBody.append("{");
            requestBody.append("\"correctedEquations\":").append(correctedEquations);
            requestBody.append("}");
            int length = requestBody.length();

            con.setFixedLengthStreamingMode(length);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.connect();
            try (OutputStream os = con.getOutputStream()) {
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
