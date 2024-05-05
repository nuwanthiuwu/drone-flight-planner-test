package runners;

import tests.TestDroneFlightPlanner;
import org.testng.TestNG;

public class TestRunner {
    public static void main(String[] args) {
        Class[] testClasses = {TestDroneFlightPlanner.class};
        TestNG runner = new TestNG();
        runner.setTestClasses(testClasses);
        runner.run();
    }
}
