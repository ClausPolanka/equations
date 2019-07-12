package equation;

import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class Expression {

    private static final int INDEX_OF_OPERAND1 = 0;
    private static final int INDEX_OF_OPERATOR = 1;
    private static final int INDEX_OF_OPERAND2 = 2;
    public String operand1;
    public String operand2;
    public String operator;

    public Expression(String expression) {
        operand1 = valueOf(expression.charAt(INDEX_OF_OPERAND1));
        if (expression.length() > 1) {
            operator = valueOf(expression.charAt(INDEX_OF_OPERATOR));
            operand2 = valueOf(expression.charAt(INDEX_OF_OPERAND2));
        }
    }

    public Expression(String operand1, String operator, String operand2) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }

    public String evaluate() {
        switch (operator) {
            case "+":
                return valueOf(parseInt(operand1) + parseInt(operand2));
            case "-":
                return valueOf(parseInt(operand1) - parseInt(operand2));
            default:
                throw new RuntimeException("Operation Not Supported");
        }

    }

    @Override
    public String toString() {
        return Stream.of(operand1, operator, operand2)
                .filter(Objects::nonNull)
                .collect(joining());
    }

    public boolean isSingleDigit() {
        return operand1 == null || operand2 == null;
    }

}
