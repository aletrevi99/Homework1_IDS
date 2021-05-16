package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(ListAdapterSuite.class);

        for (Failure failure : result.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per ListAdapter ========");
        if (result.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println(result.getFailureCount() + " test falliti.\n");

        System.out.println("Test eseguiti: " + result.getRunCount());
        System.out.println("========= Fine Test per ListAdapter =========\n\n" +
                "\tProcedo con la prossima Suite.\n");


        Result result2 = JUnitCore.runClasses(MapAdapterSuite.class);

        for (Failure failure : result2.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per MapAdapter ========");
        if (result2.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println(result2.getFailureCount() + " test falliti.\n");

        System.out.println("Test eseguiti: " + result2.getRunCount());
        System.out.println("========= Fine Test per MapAdapter =========\n");

        System.out.println("Totale test eseguiti: " + (result.getRunCount() + result2.getRunCount()) + ". Di cui "
                + (result.getRunCount() + result2.getRunCount() - result.getFailureCount() - result2.getFailureCount())
                + " completati con successo.");
        System.out.println("Versione di JUnit utilizzata: 4.13.1");
    }
}