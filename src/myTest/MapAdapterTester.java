package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class MapAdapterTester
{
    private MapAdapter map;

    @Before
    public void initialize()
    {
        map = new MapAdapter();
    }

    @Test
    public void initializeTest()
    {
        assertEquals(0, map.size());
    }

    @Test
    public void clearTest()
    {
        assertEquals(0, map.size());

        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        // map.putAll(getAMap());
        assertEquals(10, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void containsKeyTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        assertEquals(10, map.size());

        for (int i = 0; i < map.size() / 2; i++)
            assertTrue(map.containsKey(i));

        for (int i = 5; i < map.size(); i++)
            assertTrue(map.containsKey("chiave " + (i - 4)));

        assertFalse(map.containsKey("pippo"));

        assertThrows(NullPointerException.class, () -> {map.containsKey(null);});
    }

    @Test
    public void containsValueTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        assertEquals(10, map.size());

        for (int i = 0; i < map.size() / 2; i++)
            assertTrue(map.containsValue("stringa " + i));

        for (int i = 5; i < map.size(); i++)
            assertTrue(map.containsValue(i));

        assertFalse(map.containsValue("pippo"));

        assertThrows(NullPointerException.class, () -> {map.containsValue(null);});
    }

    @Test
    public void entrySetTest()
    {
        map.putAll(getAMap());
        assertNotNull(map.entrySet());
    }

    @Test
    public void getTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        for (int i = 0; i < test.size() / 2; i++)
            assertEquals(test.get(i), map.get(i));
        for (int i = 5; i < test.size(); i++)
            assertEquals(test.get(i), map.get("stringa " + (i - 4)));

        assertNull(map.get("pippo"));

        assertThrows(NullPointerException.class, () -> {map.get(null);});
    }

    @Test
    public void isEmptyTest()
    {
        assertTrue(map.isEmpty());

        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
        {
            map.put(i, test.get(i));
            assertFalse(map.isEmpty());
        }
        for (int i = 5; i < test.size(); i++)
        {
            map.put("chiave " + (i - 4), i);
            assertFalse(map.isEmpty());
        }

        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void keySetTest()
    {
        map.putAll(getAMap());
        assertNotNull(map.keySet());
    }

    @Test
    public void putTest()
    {
        MapAdapter test = getAMap();

        for (int i = 0; i < test.size() / 2; i++)
            map.put(i, test.get(i));
        for (int i = 5; i < test.size(); i++)
            map.put("chiave " + (i - 4), i);

        assertEquals(10, map.size());

        for (int i = 0; i < test.size() / 2; i++)
            assertEquals(test.get(i), map.get(i));
        for (int i = 5; i < test.size(); i++)
            assertEquals(test.get(i), map.get("stringa " + (i - 4)));

        Object tmp = map.get(0);
        assertSame(tmp, map.put(0, "test"));

        tmp = map.get(0);
        assertEquals("test", tmp);

        map.put("chiave", "valore");
        assertEquals(11, map.size());

        HMap.Entry e = new MapAdapter.Entry(null, 1);

        assertThrows(NullPointerException.class, () -> {map.put(null, 0);});
        assertThrows(NullPointerException.class, () -> {map.put(0, null);});
        assertThrows(NullPointerException.class, () -> {map.put(null, null);});
    }

    @Test
    public void putAllTest()
    {
        MapAdapter test = getAMap();
        MapAdapter test2 = getAMap2();

        map.putAll(test);
        map.putAll(test2);

        assertEquals(20, map.size());

        for (int i = 0; i < test.size() / 2; i++)
            assertEquals(test.get(i), map.get(i));
        for (int i = 5; i < test.size(); i++)
            assertEquals(test.get(i), map.get("stringa " + (i - 4)));

        for (int i = 10; i < test2.size() / 2 + 10; i++)
            assertEquals(test2.get(i), map.get(i));
        for (int i = 15; i < test2.size() + 10; i++)
            assertEquals(test2.get(i), map.get("stringa " + (i - 4)));

        assertThrows(NullPointerException.class, () -> {map.putAll(null);});

    }

    @Test
    public void removeTest()
    {
        MapAdapter test = getAMap();
        map.putAll(test);

        Object tmp;
        int count = map.size();
        int size = map.size();

        for (int i = 0; i < size / 2; i++)
        {
            tmp = map.get(i);
            assertSame(tmp, map.remove(i));
            assertEquals(--count, map.size());
        }

        for (int i = 5; i < size; i++)
        {
            tmp = map.get("chiave " + (i - 4));
            assertSame(tmp, map.remove("chiave " + (i - 4)));
            assertEquals(--count, map.size());
        }

        map.putAll(test);
        tmp = map.get(2);
        assertSame(tmp, map.remove(2));
        assertNotSame(tmp, map.remove(3));
        assertNull(map.remove(8));
        assertEquals(size - 2, map.size());

        assertThrows(NullPointerException.class, () -> {map.remove(null);});
    }

    @Test
    public void sizeTest()
    {
        assertEquals(0, map.size());

        int count = 0;
        for (int i = 0; i < 10; i ++)
        {
            map.put(i, i);
            assertEquals(++count, map.size());
        }

        assertEquals(count, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void valuesTest()
    {
        map.putAll(getAMap());
        assertNotNull(map.values());
    }

    @Test
    public void hashCodeTest()
    {
        MapAdapter test1 = new MapAdapter();
        test1.put(1, "test1");

        MapAdapter test2 = new MapAdapter();
        test2.put(2, "test2");

        map.put(1, "test1");

        assertEquals(test1.hashCode(), map.hashCode());
        assertNotEquals(test2.hashCode(), map.hashCode());

        test1.put(1, 1);
        assertNotEquals(test1.hashCode(), map.hashCode());
    }

    @Test
    public void equalsTest()
    {
        MapAdapter test = new MapAdapter();
        test.put(1, "test");
        map.put(1, "test");
        assertTrue(map.equals(test));
        test.put(2, "test2");
        assertFalse(map.equals(test));

        assertFalse(map.equals(getAMap()));
        assertFalse(map.equals(null));
        assertFalse(map.equals(getAVector()));
    }

    private MapAdapter getAMap()
    {
        MapAdapter m = new MapAdapter();
        for (int i = 0; i < 5; i++)
            m.put(i, "stringa " + i);
        for (int i = 5; i < 10; i++)
            m.put("chiave " + (i - 4), i);
        return m;
    }

    private MapAdapter getAMap2()
    {
        MapAdapter m = new MapAdapter();
        for (int i = 10; i < 15; i++)
            m.put(i, "stringa " + i);
        for (int i = 15; i < 20; i++)
            m.put("chiave " + (i - 4), i);
        return m;
    }

    private Vector getAVector()
    {
        Vector test = new Vector();
        for (int i = 0; i < 10; i++) test.addElement(i);
        return test;
    }

}
