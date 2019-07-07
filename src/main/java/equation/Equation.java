package equation;

import static java.lang.String.format;

public class Equation {

    private final String rightSide;
    private Expression leftSideExp;

    /**
     * @param equation format leftSide=rightSide e.g. '1=2' or '1+2=3'
     */
    public Equation(String equation) {
        this.leftSideExp = new Expression(equation.split("=")[0]);
        this.rightSide = equation.split("=")[1];
    }

    public Equation(String leftSide, String rightSide) {
        this.leftSideExp = new Expression(leftSide);
        this.rightSide = rightSide;
    }

    public Equation(Expression leftSideExp, String rightSide) {
        this.leftSideExp = leftSideExp;
        this.rightSide = rightSide;
    }

    public static Equation newCorrectEquation(String digit) {
        return new Equation(format("%s=%s", digit, digit));
    }

    public boolean hasSingleNumberLeftSide() {
        return leftSideExp.operator.equals("");
    }

    public String getLeftSide() {
        return leftSideExp.toString();
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public boolean equals(Object obj) {
        Equation equation = (Equation) obj;
        return leftSideExp.toString().equals(equation.leftSideExp.toString())
                && rightSide.equals(equation.rightSide);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return format("Equation{%s=%s}", leftSideExp.toString(), rightSide);
    }

    public boolean isCorrect() {
        System.out.println(this);
        return leftSideExp.evaluate().equals(rightSide);
    }

    public Expression getLeftSideExpression() {
        if (hasSingleNumberLeftSide())
            throw new RuntimeException("Left side not an Expression");
        return leftSideExp;
    }

    public Equation withLeftSide(Expression leftSide) {
        if (!isCorrect()) {
            System.out.println(this);
        }
        return new Equation(leftSide, rightSide);
    }

    public Equation withLeftSide(String leftSide) {
        return new Equation(leftSide, rightSide);
    }

}
