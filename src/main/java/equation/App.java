package equation;

import equation.domain.TestCaseRunner;
import equation.io.HttpTestCaseRepository;

public class App {

    public static void main(String[] args) {
        int firstTestCase = 1;
        int lastTestCase = 100;
        new TestCaseRunner(new HttpTestCaseRepository()).run(firstTestCase, lastTestCase);
    }

}
