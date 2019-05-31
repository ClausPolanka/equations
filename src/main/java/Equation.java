public class Equation {

    private final String leftSide;
    private final String rightSide;

    public Equation(String equation) {
        this.leftSide = equation.split("=")[0];
        this.rightSide = equation.split("=")[1];
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }
}
