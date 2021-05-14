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
        if (result2.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println("Non tutti i test sono risultati corretti.\n");

        System.out.println("Test eseguiti: 15.");
        System.out.println("========= Fine Test per MapAdapter =========\n\n"+
                "\tProceeding with the next one.\n");


        Result result3 = JUnitCore.runClasses(EntrySetTester.class);

        for (Failure failure : result3.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per EntrySet ========");
        if (result3.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println("Non tutti i test sono risultati corretti.\n");

        System.out.println("Test eseguiti: 16.");
        System.out.println("========= Fine Test per EntrySet =========\n\n"+
                "\tProceeding with the next one.\n");


        Result result4 = JUnitCore.runClasses(KeySetTester.class);

        for (Failure failure : result3.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per keySet ========");
        if (result4.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println("Non tutti i test sono risultati corretti.\n");

        System.out.println("Test eseguiti: 16.");
        System.out.println("========= Fine Test per keySet =========\n\n"+
                "\tProceeding with the next one.\n");


        Result result5 = JUnitCore.runClasses(ValuesCollectionTester.class);

        for (Failure failure : result5.getFailures())
            System.out.println(failure.toString());

        System.out.println("======== Inizio Test per ValuesCollection ========");
        if (result5.wasSuccessful())
            System.out.println("Tutti i test sono stati completati con successo.");
        else
            System.out.println("Non tutti i test sono risultati corretti.\n");

        System.out.println("Test eseguiti: 16.");
        System.out.println("========= Fine Test per ValuesCollection =========\n\n");

        System.out.println("Totale test eseguiti: xxx. Grand total of tests run: xxx.");
        System.out.println("Versione di JUnit utilizzata : 4.13.1");

    }
}