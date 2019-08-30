package equation;

public class OperatorFlipper {

    public static Equation flip(Equation equation) {
        Expression leftSide = equation.getLeftSide();
        String operator;
        if (leftSide.getOperator().equals("+")) {
            operator = "-";
        } else {
            operator = "+";
        }
        return equation.withLeftSideOperator(operator);
    }

}
