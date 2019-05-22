import java.util.Arrays;
import java.util.List;

public class Equations {

    public List<String> findCorrectEquations(String s) {
        final String[] digits = s.split("=");

        return Arrays.asList(digits[0] + "=" + digits[1]);

    }

}
