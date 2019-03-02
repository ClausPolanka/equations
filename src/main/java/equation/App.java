package equation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws Exception {
        for (int testCase = 1; testCase <= 100; testCase++) {
            Equation equation = getEquationFor(testCase);
            Set<Equation> correctedEquations = EquationSolver.solve(equation);
            sendCorrectedEquationsFor(testCase, correctedEquations);
        }
    }

    public static void sendCorrectedEquationsFor(int testCase, Set<Equation> correctedEquations) throws IOException {
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
    }

    public static Equation getEquationFor(int testCase) throws IOException {
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
    }
}
