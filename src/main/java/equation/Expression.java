package equation;

import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

public class Expression {

    private static final int INDEX_OF_OPERAND1 = 0;
    private static final int INDEX_OF_OPERATOR = 1;
    private static final int INDEX_OF_OPERAND2 = 2;
    private String operand1 = "";
    private String operand2 = "";
    private String operator = "";

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
        switch (getOperator()) {
            case "+":
                return valueOf(parseInt(getOperand1()) + parseInt(getOperand2()));
            case "-":
                return valueOf(parseInt(getOperand1()) - parseInt(getOperand2()));
            default:
                return getOperand1();
        }

    }

    @Override
    public String toString() {
        return Stream.of(getOperand1(), getOperator(), getOperand2())
                .collect(joining());
    }

    public boolean isSingleDigit() {
        return toString().length() == 1;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getOperator() {
        return operator;
    }

}
