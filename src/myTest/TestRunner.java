package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ListAdapterTester.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println("Total tests run: 26.");
        System.out.println("End of the suite ListAdapterTester. Proceeding with the next one.\n");

        /*
        Result result2 = JUnitCore.runClasses(ListAdapterSubListTester.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result2.wasSuccessful());
        System.out.println("End of the suite ListAdapterSubListTester.");
        System.out.println("Total tests run: 58. Grand total of tests run: 119.");
        System.out.println("JUnit version used for the tests: 4.13");
        */
    }
}