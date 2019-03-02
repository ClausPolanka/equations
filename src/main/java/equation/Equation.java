package equation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {

    private String equation;
    private Pattern pattern = Pattern.compile("\\d=\\d");

    public Equation(String equation) {
        validateFormatOf(equation);
        this.equation = equation;
    }

    private void validateFormatOf(String equation) {
        Matcher m = pattern.matcher(equation);
        if (!m.matches()) {
            throw new EquationInvalidFormatException("Equation must be in format: NR=NR but was: '" + equation + "'");
        }
    }

    public String leftSide() {
        return equation.split("=")[0];
    }

    public String rightSide() {
        return equation.split("=")[1];
    }

    @Override
    public String toString() {
        return "\"" + equation + "\"";
    }

    public class EquationInvalidFormatException extends RuntimeException {

        public EquationInvalidFormatException(String msg) {
            super(msg);
        }
    }
}
