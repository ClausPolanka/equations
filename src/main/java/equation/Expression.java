package equation;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class Expression {

    private static final int INDEX_OF_OPERAND1 = 0;
    private static final int INDEX_OF_OPERATOR = 1;
    private static final int INDEX_OF_OPERAND2 = 2;
    public final String leftOperand;
    public final String rightOperand;
    public final String operator;

    public Expression(String expression) {
        leftOperand = valueOf(expression.charAt(INDEX_OF_OPERAND1));
        operator = expression.length() > 1 ? valueOf(expression.charAt(INDEX_OF_OPERATOR)) : "";
        rightOperand = expression.length() > 2 ? valueOf(expression.charAt(INDEX_OF_OPERAND2)) : "";
    }

    public String evaluate() {
        switch (operator) {
            case "+":
                return valueOf(parseInt(leftOperand) + parseInt(rightOperand));
            case "-":
                return valueOf(parseInt(leftOperand) - parseInt(rightOperand));
            default:
                return leftOperand;
        }
    }

    public String toString() {
        return leftOperand + operator + rightOperand;
    }

    public Expression withLeftOperand(String solution) {
        return new Expression(solution + operator + rightOperand);
    }

    public Expression withRightOperand(String solution) {
        return new Expression(leftOperand + operator + solution);
    }

}
