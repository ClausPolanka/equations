package equation;

import static java.lang.String.format;

public class Equation {

    private final String rightSide;
    private Expression leftSide;

    /**
     * @param equation format leftSide=rightSide e.g. '1=2' or '1+2=3'
     */
    public Equation(String equation) {
        this.leftSide = new Expression(equation.split("=")[0]);
        this.rightSide = equation.split("=")[1];
    }

    public Equation(Expression leftSide, String rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public static Equation newCorrectEquation(String digit) {
        return new Equation(format("%s=%s", digit, digit));
    }

    public Expression getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public boolean equals(Object obj) {
        Equation equation = (Equation) obj;
        return leftSide.toString().equals(equation.leftSide.toString())
                && rightSide.equals(equation.rightSide);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return format("Equation{%s=%s}", leftSide.toString(), rightSide);
    }

    public boolean isCorrect() {
        return leftSide.evaluate().equals(rightSide);
    }

    public Equation withLeftSide(Expression leftSide) {
        return new Equation(leftSide, rightSide);
    }

    public Equation withRightSide(String rightSide) {
        return new Equation(leftSide, rightSide);
    }

    public String getLSLO() {
        return leftSide.leftOperand;
    }

    public Equation withLSLO(String leftOperand) {
        return withLeftSide(leftSide.withLeftOperand(leftOperand));
    }

    public String getLSRO() {
        return leftSide.rightOperand;
    }

    public Equation withLSRO(String rightOperand) {
        return withLeftSide(leftSide.withRightOperand(rightOperand));
    }

}
