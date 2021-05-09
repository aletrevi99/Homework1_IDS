package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListAdapterTest
{
    private ListAdapter list;

    @Before
    public void initialize()
    {
        list = new ListAdapter();
    }

    @Test
    public void initializeTest()
    {
        assertEquals(0, list.size());
    }

    @Test
    public void testAddIndex()
    {
        list.add(0, "pippo");
        list.add(1, "pluto");
        list.add(list.size(), 333);

        assertEquals(3, list.size());
        assertEquals(333, list.get(list.size() -1));
        assertEquals("pippo", list.get(0));

        assertEquals(444, list.get(0));
        assertEquals(4, list.size());

        assertThrows(NullPointerException.class, () -> {list.add(0, null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(5, "paperino");});
    }

    @Test
    public void testadd()
    {
        assertTrue(list.add("pippo"));
        assertEquals(1, list.size());

        assertTrue(list.add("pluto"));
        assertEquals(2, list.size());

        assertTrue(list.add(111));
        assertEquals(3, list.size());

        assertTrue(list.contains("pluto"));
        assertTrue(list.contains(111));
        assertFalse(list.contains("paperino"));
        assertEquals("pluto", list.get(1));
        assertEquals(111, list.get(2));

        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }


    @Test
    public void testAddAll()
    {
        assertTrue(list.addAll(getACollection()));
        assertEquals(10, list.size());

        assertTrue(list.addAll(getACollection()));
        assertEquals(20, list.size());

        assertTrue(list.add("pippo"));
        assertEquals("pippo", list.get(20));
    }

    private ListAdapter getACollection()
    {
        ListAdapter l = new ListAdapter();
        for (int i = 0; i < 5; i++)
            l.add(i, i*i);

        for (int i = 5; i < 10; i++)
            l.add(i, "string number " + (i-4));

        return l;
    }

}