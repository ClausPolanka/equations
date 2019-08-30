package equation;

import static java.lang.String.format;

public class Equation {

    private final String leftSide;
    private final String rightSide;

    public Equation(String equation) {
        this.leftSide = equation.split("=")[0];
        this.rightSide = equation.split("=")[1];
    }

    private Equation(Expression leftSide, String rightSide) {
        this.leftSide = leftSide.toString();
        this.rightSide = rightSide;
    }

    public static Equation newCorrectEquation(String digit) {
        return new Equation(format("%s=%s", digit, digit));
    }

    public Expression getLeftSide() {
        return new Expression(leftSide);
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Equation)) {
            return false;
        }
        Equation equation = (Equation) obj;
        return leftSide.equals(equation.leftSide)
                && rightSide.equals(equation.rightSide);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return format("Equation{%s=%s}", leftSide, rightSide);
    }

    boolean isCorrect() {
        return new Expression(leftSide).evaluate().equals(rightSide);
    }

    private Equation withLeftSide(Expression leftSide) {
        return new Equation(leftSide, rightSide);
    }

    Equation withRightSide(String rightSide) {
        return new Equation(new Expression(leftSide), rightSide);
    }

    Equation withLeftSideOperand1(String operand1) {
        return withLeftSide(getLeftSide().withOperand1(operand1));
    }

    Equation withLeftSideOperand2(String operand2) {
        return withLeftSide(getLeftSide().withOperand2(operand2));
    }

    String getLeftSideOperand1() {
        return getLeftSide().getOperand1();
    }

    String getLeftSideOperand2() {
        return getLeftSide().getOperand2();
    }

    public Equation withLeftSideOperator(String operator) {
        return withLeftSide(new Expression(getLeftSideOperand1(), operator, getLeftSideOperand2()));
    }
}
