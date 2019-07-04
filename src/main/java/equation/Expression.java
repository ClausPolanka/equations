package equation;

public class Expression {

    public String operand1;

    public Expression(String expression) {
        operand1 = expression.split("\\+")[0];
    }

}
