package equation;

public class Equation {

    private String equation;

    public Equation(String equation) {
        this.equation = equation;
    }

    public String leftSide() {
        return equation.split("=")[0];
    }

    public String rightSide() {
        return equation.split("=")[1];
    }
}
