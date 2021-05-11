package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class ListAdapterTester
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
    public void addIndexTest()
    {
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
            list.add(i, test.get(i));

        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));

        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i - 4), list.get(i));

        list.add(0, "test");

        assertEquals("test", list.get(0));
        assertEquals(0, list.get(1));

        assertEquals(11, list.size());

        assertThrows(NullPointerException.class, () -> {list.add(0, null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(100, "test");});
    }

    @Test
    public void addTest()
    {
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
            assertTrue(list.add(test.get(i)));

        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));
        for (int i = 0; i < list.size() / 2; i++)
            assertTrue(list.contains(i));

        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i - 4), list.get(i));
        for (int i = 5; i < list.size(); i++)
            assertTrue(list.contains("stringa " + (i - 4)));

        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    @Test
    public void addAllTest()
    {
        assertTrue(list.addAll(getACollection()));
        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));

        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i-4), list.get(i));

        assertTrue(list.add("pippo"));
        assertEquals("pippo", list.get(10));

        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
    }

    @Test
    public void addAllIndexTest()
    {
        assertTrue(list.addAll(getACollection()));
        assertEquals(10, list.size());
        assertTrue(list.addAll(5, getACollection()));
        assertEquals(20, list.size());

        for (int i = 0; i < list.size() / 4; i++)
            assertEquals(i, list.get(i));

        for (int i = 5; i < list.size() / 2; i++)
            assertEquals(i - 5, list.get(i));

        for (int i = 10; i < (list.size() - list.size() / 4); i++)
            assertEquals("stringa " + (i - 4 - 5), list.get(i));

        for (int i = 15; i < list.size(); i++)
            assertEquals("stringa " + (i - 4 - 10), list.get(i));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(50, getACollection());});
        assertThrows(NullPointerException.class, () -> {list.addAll(5, null);});
    }

    @Test
    public void clearTest()
    {
        assertEquals(0, list.size());
        assertTrue(list.addAll(getACollection()));
        assertEquals(10, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void containsTest()
    {
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
            assertTrue(list.add(test.get(i)));

        assertEquals(10, list.size());

        for (int i = 0; i < list.size() / 2; i++)
            assertTrue(list.contains(i));

        for (int i = 5; i < list.size(); i++)
            assertTrue(list.contains("stringa " + (i - 4)));

        assertThrows(NullPointerException.class, () -> {list.contains(null);});
    }

    @Test
    public void containsAllTest()
    {
        ListAdapter test = getACollection();

        assertFalse(list.containsAll(test));

        assertTrue(list.addAll(test));
        assertEquals(10, list.size());
        assertTrue(list.containsAll(test));

        for (int i = 0; i < list.size(); i++)
            assertTrue(list.contains(test.get(i)));

        list.add(2, 111);
        list.add(4, 43531);

        assertTrue(list.containsAll(test));

        assertThrows(NullPointerException.class, () -> {list.containsAll(null);});
        assertThrows(ClassCastException.class, () -> {list.containsAll((HCollection) getAVector());});
    }

    @Test
    public void equalsTest()
    {
        ListAdapter test = new ListAdapter();
        test.add(1);
        list.add(1);
        assertTrue(list.equals(test));
        test.add(2);
        assertFalse(list.equals(test));

        assertFalse(list.equals(getACollection()));
        assertFalse(list.equals(null));
        assertFalse(list.equals(getAVector()));
    }

    @Test
    public void hashCodeTest()
    {
        ListAdapter test1 = new ListAdapter();
        test1.add(1);

        ListAdapter test2 = new ListAdapter();
        test2.add(2);

        list.add(1);

        assertEquals(test1.hashCode(), list.hashCode());
        assertNotEquals(test2.hashCode(), list.hashCode());

        test1.add(1);
        assertNotEquals(test1.hashCode(), list.hashCode());
    }

    @Test
    public void getTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.get(i));
        for (int i = 5; i < list.size(); i++)
            assertEquals("stringa " + (i - 4), list.get(i));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(999);});
    }

    @Test
    public void indexOfTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.indexOf(i));
        for (int i = 5; i < list.size(); i++)
            assertEquals(i, list.indexOf("stringa " + (i - 4)));

        assertEquals(-1, list.indexOf("pippo"));

        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }

    @Test
    public void isEmptyTest()
    {
        assertTrue(list.isEmpty());
        ListAdapter test = getACollection();

        for (int i = 0; i < test.size(); i++)
        {
            assertTrue(list.add(test.get(i)));
            assertFalse(list.isEmpty());
        }
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void iteratorTest()
    {
        HIterator testNull = list.iterator();
        assertNotNull(testNull);

        list.addAll(getACollection());
        HIterator test = list.iterator();

        for (int i = 0; i < list.size(); i++)
            assertEquals(test.next(), list.get(i));

        int j = 0;
        while (test.hasNext())
            assertEquals(test.next(), list.get(j++));
    }

    @Test
    public void lastIndexOfTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        for (int i = 0; i < list.size() / 2; i++)
            assertEquals(i, list.lastIndexOf(i));
        for (int i = 5; i < list.size(); i++)
            assertEquals(i, list.lastIndexOf("stringa " + (i - 4)));

        assertEquals(-1, list.lastIndexOf("pippo"));

        list.add(1);

        assertEquals(list.size() - 1, list.lastIndexOf(1));

        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }

    @Test
    public void listIteratorTest()
    {
        HListIterator testNull = list.listIterator();
        assertNotNull(testNull);

        list.addAll(getACollection());
        HListIterator test = list.listIterator();

        for (int i = 0; i < list.size(); i++)
            assertEquals(test.next(), list.get(i));

        int j = 0;
        while (test.hasNext())
            assertEquals(test.next(), list.get(j++));
    }

    @Test
    public void listIteratorIndexTest()
    {
        HListIterator testNull = list.listIterator();
        assertNotNull(testNull);

        list.addAll(getACollection());
        HListIterator test = list.listIterator(5);

        int j = 0;
        while (test.hasNext())
            assertEquals(test.next(), list.get(j++ + 5));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.listIterator(999);});
    }

    @Test
    public void removeIndexTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        Object tmp;
        int count = list.size();
        int size = list.size();

        for (int i = 0; i < size; i++)
        {
            tmp = list.get(0);
            assertSame(tmp, list.remove(0));
            assertEquals(--count, list.size());
        }

        list.addAll(test);
        tmp = list.get(5);
        assertSame(tmp, list.remove(5));
        assertNotSame(tmp, list.remove(8));
        assertEquals(size - 2, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(999);});
    }

    @Test
    public void removeObjectTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        int count = list.size();
        int size = list.size();

        for (int i = 0; i < size / 2; i++)
        {
            assertTrue(list.remove((Object)i));
            assertEquals(--count, list.size());
        }

        assertTrue(list.remove("stringa " + 1));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 2));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 3));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 4));
        assertEquals(--count, list.size());
        assertTrue(list.remove("stringa " + 5));
        assertEquals(--count, list.size());


        list.addAll(test);
        assertTrue(list.remove("stringa 2"));
        assertFalse(list.remove("stringa 200"));
        assertEquals(size - 1, list.size());

        assertThrows(NullPointerException.class, () -> {list.remove(null);});
    }

    @Test
    public void removeAllTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);
        list.addAll(test);

        assertEquals(20, list.size());
        assertTrue(list.removeAll(test));
        assertEquals(10, list.size());

        list.clear();
        for (int i = 0; i < 10; i++) list.add("pippo");
        assertFalse(list.removeAll(test));

        assertThrows(NullPointerException.class, () -> {list.removeAll(null);});
        assertThrows(ClassCastException.class, () -> {list.removeAll((HCollection) getAVector());});
    }

    @Test
    public void retainAllTest()
    {
        ListAdapter test = getACollection();
        list.addAll(test);

        assertEquals(10, list.size());
        assertFalse(list.retainAll(test));
        assertEquals(10, list.size());

        list.clear();
        for (int i = 0; i < 10; i++) list.add(i);
        int size = list.size();
        assertTrue(list.retainAll(test));
        for (int i = 0; i < list.size(); i++)
            assertEquals(i, list.get(i));

        assertEquals(size / 2, list.size());

        assertThrows(NullPointerException.class, () -> {list.removeAll(null);});
        assertThrows(ClassCastException.class, () -> {list.removeAll((HCollection) getAVector());});
    }

    @Test
    public void setTest()
    {
        list.addAll(getACollection());

        Object tmp;
        int count = list.size();
        int size = list.size();

        for (int i = 0; i < size; i++)
        {
            tmp = list.get(i);
            assertSame(tmp, list.set(i, i*i));
            assertEquals(i*i, list.get(i));
            assertEquals(size, list.size());
        }

        assertThrows(NullPointerException.class, () -> {list.set(0, null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.set(-1, 1);});
    }

    @Test
    public void sizeTest()
    {
        assertEquals(0, list.size());

        int count = 0;
        for (int i = 0; i < 10; i ++)
        {
            list.add(i);
            assertEquals(++count, list.size());
        }

        assertEquals(count, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void subListTest()
    {
        list.addAll(getACollection());
        list.addAll(getACollection());

        assertNotNull(list.subList(2, 18));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(-1, 10);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(10, 999);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(15, 5);});
    }

    @Test
    public void toArrayTest()
    {
        list.addAll(getACollection());
        Object[] test = new Object[list.size()];

        for (int i = 0; i < list.size(); i++)
            test[i] = list.get(i);

        assertArrayEquals(test, list.toArray());
    }

    @Test
    public void toArrayArgTest()
    {
        list.addAll(getACollection());
        Object[] test = new Object[list.size()];
        Object[] test1 = new Object[list.size() + 5];
        Object[] test2 = new Object[list.size() - 5];

        for (int i = 0; i < list.size(); i++)
            test[i] = list.get(i);

        assertArrayEquals(test, list.toArray(test));

        list.toArray(test1);
        for (int i = 0; i < list.size(); i++)
            assertEquals(test[i], test1[i]);

        Object[] test2_1 = list.toArray(test2);
        for (int i = 0; i < list.size(); i++)
            assertEquals(test[i], test2_1[i]);

        assertThrows(NullPointerException.class, () -> {list.toArray(null);});
    }

    private ListAdapter getACollection()
    {
        ListAdapter l = new ListAdapter();
        for (int i = 0; i < 5; i++)
            l.add(i, i);

        for (int i = 5; i < 10; i++)
            l.add(i, "stringa " + (i-4));

        return l;
    }

    private Vector getAVector()
    {
        Vector test = new Vector();
        for (int i = 0; i < 10; i++) test.addElement(i);
        return test;
    }
}