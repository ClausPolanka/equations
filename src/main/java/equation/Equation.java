package equation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {

    private String equation;

    public Equation(String equation) {
        validateFormatOf(equation);
        this.equation = equation;
    }

    private void validateFormatOf(String equation) {
        Pattern p = Pattern.compile("(\\d)=(\\d)");
        Matcher m = p.matcher(equation);
        if (!m.matches()) {
            throw new EquationInvalidFormatException("Equation must be in format: NR=NR");
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
        return equation;
    }

    public class EquationInvalidFormatException extends RuntimeException {

        public EquationInvalidFormatException(String msg) {
            super(msg);
        }
    }
}
