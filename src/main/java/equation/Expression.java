package equation;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class Expression {

    private static final int INDEX_OF_OPERAND1 = 0;
    private static final int INDEX_OF_OPERATOR = 1;
    private static final int INDEX_OF_OPERAND2 = 2;
    public String leftOperand;
    public String rightOperand;
    public String operator;

    public Expression(String expression) {
        leftOperand = valueOf(expression.charAt(INDEX_OF_OPERAND1));
        operator = valueOf(expression.charAt(INDEX_OF_OPERATOR));
        rightOperand = valueOf(expression.charAt(INDEX_OF_OPERAND2));
    }

    public String evaluate() {
        switch (operator) {
            case "+":
                return valueOf(parseInt(leftOperand) + parseInt(rightOperand));
            case "-":
                return valueOf(parseInt(leftOperand) - parseInt(rightOperand));
            default:
                throw new RuntimeException("Operation not supported");
        }
    }

    @Override
    public String toString() {
        return leftOperand + operator + rightOperand;
    }

    public Expression withNewLeftOperand(String solution) {
        return new Expression(solution + operator + rightOperand);
    }

    public Expression withNewRightOperand(String solution) {
        return new Expression(leftOperand + operator + solution);
    }

}
