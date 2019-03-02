package equation;

public class App {

    public static void main(String[] args) {
        new TestCaseRunner(new HttpTestCaseRepository()).run(1, 100);
    }

}
