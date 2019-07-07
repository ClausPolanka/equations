package equation;

import static java.lang.String.format;

public class Equation {

    private final String leftSide;
    private final String rightSide;

    /**
     * @param equation format leftSide=rightSide e.g. '1=2' or '1+2=3'
     */
    public Equation(String equation) {
        this.leftSide = equation.split("=")[0];
        this.rightSide = equation.split("=")[1];
    }

    public Equation(String leftSide, String rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public static Equation newCorrectEquation(String digit) {
        return new Equation(format("%s=%s", digit, digit));
    }

    public boolean hasSingleNumberLeftSide() {
        return leftSide.length() == 1;
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
        return leftSide.equals(rightSide);
    }

    public Expression getLeftSideExpression() {
        if (hasSingleNumberLeftSide())
            throw new RuntimeException("Left side not an Expression");
        return new Expression(getLeftSide());
    }

    public Equation withNewLeftSide(String leftSide) {
        return new Equation(leftSide, rightSide);
    }

}
