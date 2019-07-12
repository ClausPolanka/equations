package equation;

import static java.lang.String.format;

public class Equation {

    private final String leftSide;
    private final String rightSide;

    public Equation(String equation) {
        this.leftSide = equation.split("=")[0];
        this.rightSide = equation.split("=")[1];
    }

    public Equation(Expression leftSide, String rightSide) {
        this.leftSide = leftSide.toString();
        this.rightSide = rightSide;
    }

    public static Equation newCorrectEquation(String digit) {
        return new Equation(format("%s=%s", digit, digit));
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public boolean equals(Object obj) {
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

    public boolean isCorrect() {
        return new Expression(leftSide).evaluate().equals(rightSide);
    }

    public Equation with(Expression leftSide) {
        return new Equation(leftSide, rightSide);
    }
}
