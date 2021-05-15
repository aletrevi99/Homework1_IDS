package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MapAdapterTester.class,
        EntrySetTester.class,
        KeySetTester.class,
        ValuesCollectionTester.class
})
public class MapAdapterSuite {

}