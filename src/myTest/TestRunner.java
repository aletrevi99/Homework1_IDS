package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(ListAdapterTester.class);

        for (Failure failure : result.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per ListAdapter ========");
        if (result.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println("Non tutti i test sono risultati corretti.\n");

        System.out.println("Test eseguiti: 26.");
        System.out.println("========= Fine Test per ListAdapter =========\n\n" +
                "\tProceeding with the next one.\n");


        Result result2 = JUnitCore.runClasses(MapAdapterTester.class);

        for (Failure failure : result2.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per MapAdapter ========");
        if (result.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println("Non tutti i test sono risultati corretti.\n");

        System.out.println("Test eseguiti: 15.");
        System.out.println("========= Fine Test per MapAdapter =========\n\n");

        System.out.println("Totale test eseguiti: 41. Grand total of tests run: 41.");
        System.out.println("Versione di JUnit utilizzata : 4.13.1");

    }
}