package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Vector;

public class MapAdapter implements HMap
{
    private Hashtable hashMap = new Hashtable();

    private static class Entry implements HMap.Entry
    {
        private Object key = null;
        private Object value = null;

        public Entry(Object key, Object value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public Object getKey()
        {
            return key;
        }

        @Override
        public Object getValue()
        {
            return value;
        }

        @Override
        public Object setValue(Object value)
        {
            if (value == null)
                throw new NullPointerException();

            Object tmp = getValue();
            this.value = value;

            return tmp;
        }

        @Override
        public int hashCode()
        {
            return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
        }

        @Override
        public boolean equals(Object o)
        {
            Entry tmp = (Entry) o;

            return tmp.getValue().equals(this.getValue()) && tmp.getKey().equals(this.getKey());
        }
    }

    /*
    private class EntrySet implements HSet
    {
        private Vector vectorC = null;

        private class HIteratorClass implements HIterator
        {
            protected Vector iter = null;
            protected int current = 0;
            protected boolean doneNext = false;

            public HIteratorClass()
            {
                iter = vectorC;
            }

            @Override
            public boolean hasNext()
            {
                return current < iter.size();
            }

            @Override
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                doneNext = true;
                return iter.elementAt(current++);
            }

            @Override
            public void remove() throws IllegalStateException
            {
                if (!doneNext)
                    throw new IllegalStateException();

                iter.removeElementAt(--current);
                doneNext = false;
            }
        }

        public EntrySet()
        {
            vectorC = new Vector();
        }

        @Override
        public boolean add(Object o)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear()
        {
            vectorC.removeAllElements();
        }

        @Override
        public boolean contains(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            return vectorC.contains(o);
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
                if (!vectorC.contains(iter.next()))
                    return false;

            return true;
        }

        @Override
        public boolean isEmpty()
        {
            return vectorC.size() == 0;
        }

        @Override
        public HIterator iterator()
        {
            return new HIteratorClass();
        }

        @Override
        public boolean remove(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            return vectorC.removeElement(o);
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            boolean hasChanged = false;
            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
            {
                Object tmp = iter.next();
                if (vectorC.contains(tmp))
                {
                    vectorC.removeElement(tmp); //redundant?
                    hasChanged = true;
                }
            }

            return hasChanged;
        }

        @Override
        public boolean retainAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            boolean hasChanged = false;
            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
            {
                Object tmp = iter.next();
                if (!vectorC.contains(tmp))
                {
                    vectorC.removeElement(tmp); //redundant?
                    hasChanged = true;
                }
            }

            return hasChanged;
        }

        @Override
        public int size()
        {
            return vectorC.size();
        }

        @Override
        public int hashCode()
        {
            int hash = 0;
            for (int i = 1; i < vectorC.size() - 1; i++)
                hash += vectorC.elementAt(i).hashCode() * i;

            return hash;
        }

        @Override
        public boolean equals(Object o)
        {
            int hash = 0;
            for (int i = 1; i < vectorC.size() - 1; i++)
                hash += vectorC.elementAt(i).hashCode() * i;

            return o.hashCode() == hash;
        }

        @Override
        public Object[] toArray()
        {
            Object[] tmp = new Object[vectorC.size()];

            vectorC.copyInto(tmp);

            return tmp;
        }

        @Override
        public Object[] toArray(Object[] a)
        {
            if (a == null)
                throw new NullPointerException();

            if (a.length < vectorC.size())
            {
                Object[] tmp = new Object[vectorC.size()];
                vectorC.copyInto(tmp);

                return tmp;
            } else
                vectorC.copyInto(a);

            return a;
        }
    }
     */

    private class EntrySet implements HSet
    {
        private final MapAdapter hashTable;

        private class HIteratorClass implements HIterator
        {
            protected MapAdapter iter = null;
            protected Object lastKey;
            protected Enumeration keys;

            public HIteratorClass(MapAdapter map)
            {
                iter = map;
                keys = map.hashMap.keys();
                lastKey = null;
            }

            @Override
            public boolean hasNext()
            {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                lastKey = keys.nextElement();

                return new Entry(lastKey, iter.get(lastKey));
            }

            @Override
            public void remove() throws IllegalStateException
            {
                if (lastKey == null)
                    throw new IllegalStateException();

                iter.remove(lastKey);
                lastKey = null;
            }
        }

        public EntrySet(MapAdapter m)
        {
            hashTable = m;
        }

        @Override
        public boolean add(Object o)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear()
        {
            hashTable.clear();
        }

        @Override
        public boolean contains(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            if (!(o instanceof Entry))
                throw new ClassCastException();

            Entry obj = (Entry) o;

            if (obj.getKey() == null || obj.getValue() == null)
                throw new NullPointerException();

            if (hashTable.containsKey(obj.getKey()))
            {
                Entry tmp = (Entry) hashTable.get(obj.getKey());

                return tmp.getValue() == obj.getValue();
            }

            return false;
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheckClass = c.iterator();

            while (iterCheckClass.hasNext())
                if (!(iterCheckClass.next() instanceof Entry))
                    throw new ClassCastException();

            HIterator iterCheck = c.iterator();
            Entry tmp = null;
            while (iterCheck.hasNext())
            {
                tmp = (Entry) iterCheck.next();

                if (tmp == null)
                    throw new NullPointerException();

                if (tmp.getKey() == null || tmp.getValue() == null)
                    throw new NullPointerException();
            }

            HIterator iter = c.iterator();

            while (iter.hasNext())
            {
                if (!contains(iter.next()))
                    return false;
            }

            return true;
        }

        @Override
        public boolean isEmpty()
        {
            return hashTable.isEmpty();
        }

        @Override
        public HIterator iterator()
        {
            return new HIteratorClass(hashTable);
        }

        @Override
        public boolean remove(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            if (!(o instanceof Entry))
                throw new ClassCastException();

            Entry obj = (Entry) o;

            if (this.contains(obj))
            {
                MapAdapter.this.remove(obj.getKey());
                return true;
            }

            return false;
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheckClass = c.iterator();

            while (iterCheckClass.hasNext())
                if (!(iterCheckClass.next() instanceof Entry))
                    throw new ClassCastException();

            HIterator iterCheck = c.iterator();
            Entry obj = null;
            while (iterCheck.hasNext())
            {
                obj = (Entry) iterCheck.next();

                if (obj == null)
                    throw new NullPointerException();

                if (obj.getKey() == null || obj.getValue() == null)
                    throw new NullPointerException();
            }

            HIterator iter = c.iterator();
            int size = hashTable.size();

            Entry tmp = null;
            while (iter.hasNext())
            {
                tmp = (Entry) iter.next();
                if (contains(tmp))
                    remove(tmp);
            }

            return size != hashTable.size();
        }

        @Override
        public boolean retainAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheckClass = c.iterator();

            while (iterCheckClass.hasNext())
                if (!(iterCheckClass.next() instanceof Entry))
                    throw new ClassCastException();

            HIterator iterCheck = c.iterator();
            Entry obj = null;
            while (iterCheck.hasNext())
            {
                obj = (Entry) iterCheck.next();

                if (obj == null)
                    throw new NullPointerException();

                if (obj.getKey() == null || obj.getValue() == null)
                    throw new NullPointerException();
            }

            HIterator iter = iterator();
            int size = hashTable.size();

            Entry tmp = null;
            while (iter.hasNext())
            {
                tmp = (Entry) iter.next();
                if (!c.contains(tmp))
                    remove(tmp);
            }

            return size != hashTable.size();
        }

        @Override
        public int size()
        {
            return hashTable.size();
        }

        @Override
        public Object[] toArray()
        {
            Object[] tmp = new Object[this.size()];

            HIterator iter = this.iterator();

            int j = 0;
            while (iter.hasNext())
                tmp[j++] = iter.next();

            return tmp;
        }

        @Override
        public Object[] toArray(Object[] a)
        {
            if (a == null)
                throw new NullPointerException();

            if (a.length < size())
                return toArray();

            HIterator iter = this.iterator();

            int j = 0;
            while (iter.hasNext())
                a[j++] = iter.next();

            return a;
        }

        @Override
        public int hashCode()
        {
            HIterator iter = this.iterator();

            int hash = 0;
            Entry tmp = null;

            while (iter.hasNext())
            {
                tmp = (Entry) iter.next();
                if (tmp == null)
                    hash += 0;
                else
                    hash += tmp.hashCode();
            }

            return hash;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            if (!(o instanceof Entry))
                throw new ClassCastException();

            Entry obj = (Entry) o;

            if (obj.getKey() == null || obj.getValue() == null)
                throw new NullPointerException();

            EntrySet tmp = (EntrySet) o;
            return this.hashCode() == tmp.hashCode();
        }
    }

    private class KeysSet implements HSet
    {
        private Vector vectorC = null;

        private class HIteratorClass implements HIterator
        {
            protected Vector iter = null;
            protected int current = 0;
            protected boolean doneNext = false;

            public HIteratorClass()
            {
                iter = vectorC;
            }

            @Override
            public boolean hasNext()
            {
                return current < iter.size();
            }

            @Override
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                doneNext = true;
                return iter.elementAt(current++);
            }

            @Override
            public void remove() throws IllegalStateException
            {
                if (!doneNext)
                    throw new IllegalStateException();

                iter.removeElementAt(--current);
                doneNext = false;
            }
        }

        public KeysSet()
        {
            vectorC = new Vector();
        }

        @Override
        public boolean add(Object o)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear()
        {
            vectorC.removeAllElements();
        }

        @Override
        public boolean contains(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            return vectorC.contains(o);
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
                if (!vectorC.contains(iter.next()))
                    return false;

            return true;
        }

        @Override
        public boolean isEmpty()
        {
            return vectorC.size() == 0;
        }

        @Override
        public HIterator iterator()
        {
            return new HIteratorClass();
        }

        @Override
        public boolean remove(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            return vectorC.removeElement(o);
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            boolean hasChanged = false;
            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
            {
                Object tmp = iter.next();
                if (vectorC.contains(tmp))
                {
                    vectorC.removeElement(tmp); //redundant?
                    hasChanged = true;
                }
            }

            return hasChanged;
        }

        @Override
        public boolean retainAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            boolean hasChanged = false;
            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
            {
                Object tmp = iter.next();
                if (!vectorC.contains(tmp))
                {
                    vectorC.removeElement(tmp); //redundant?
                    hasChanged = true;
                }
            }

            return hasChanged;
        }

        @Override
        public int size()
        {
            return vectorC.size();
        }

        @Override
        public int hashCode()
        {
            int hash = 0;
            for (int i = 1; i < vectorC.size() - 1; i++)
                hash += vectorC.elementAt(i).hashCode() * i;

            return hash;
        }

        @Override
        public boolean equals(Object o)
        {
            int hash = 0;
            for (int i = 1; i < vectorC.size() - 1; i++)
                hash += vectorC.elementAt(i).hashCode() * i;

            return o.hashCode() == hash;
        }

        @Override
        public Object[] toArray()
        {
            Object[] tmp = new Object[vectorC.size()];

            vectorC.copyInto(tmp);

            return tmp;
        }

        @Override
        public Object[] toArray(Object[] a)
        {
            if (a == null)
                throw new NullPointerException();

            if (a.length < vectorC.size())
            {
                Object[] tmp = new Object[vectorC.size()];
                vectorC.copyInto(tmp);

                return tmp;
            } else
                vectorC.copyInto(a);

            return a;
        }
    }

    private class CollectionValues implements HCollection
    {
        private Vector vectorC = null;

        private class HIteratorClass implements HIterator
        {
            protected Vector iter = null;
            protected int current = 0;
            protected boolean doneNext = false;

            public HIteratorClass()
            {
                iter = vectorC;
            }

            @Override
            public boolean hasNext()
            {
                return current < iter.size();
            }

            @Override
            public Object next() throws NoSuchElementException
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                doneNext = true;
                return iter.elementAt(current++);
            }

            @Override
            public void remove() throws IllegalStateException
            {
                if (!doneNext)
                    throw new IllegalStateException();

                iter.removeElementAt(--current);
                doneNext = false;
            }
        }

        public CollectionValues()
        {
            vectorC = new Vector();
        }

        @Override
        public boolean add(Object o)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(HCollection c)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear()
        {
            vectorC.removeAllElements();
        }

        @Override
        public boolean contains(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            return vectorC.contains(o);
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
                if (!vectorC.contains(iter.next()))
                    return false;

            return true;
        }

        @Override
        public boolean isEmpty()
        {
            return vectorC.size() == 0;
        }

        @Override
        public HIterator iterator()
        {
            return new HIteratorClass();
        }

        @Override
        public boolean remove(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            return vectorC.removeElement(o);
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            boolean hasChanged = false;
            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
            {
                Object tmp = iter.next();
                if (vectorC.contains(tmp))
                {
                    vectorC.removeElement(tmp); //redundant?
                    hasChanged = true;
                }
            }

            return hasChanged;
        }

        @Override
        public boolean retainAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            boolean hasChanged = false;
            HIterator iter = c.iterator();
            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            while (iter.hasNext())
            {
                Object tmp = iter.next();
                if (!vectorC.contains(tmp))
                {
                    vectorC.removeElement(tmp); //redundant?
                    hasChanged = true;
                }
            }

            return hasChanged;
        }

        @Override
        public int size()
        {
            return vectorC.size();
        }

        @Override
        public int hashCode()
        {
            int hash = 0;
            for (int i = 1; i < vectorC.size() - 1; i++)
                hash += vectorC.elementAt(i).hashCode() * i;

            return hash;
        }

        @Override
        public boolean equals(Object o)
        {
            int hash = 0;
            for (int i = 1; i < vectorC.size() - 1; i++)
                hash += vectorC.elementAt(i).hashCode() * i;

            return o.hashCode() == hash;
        }

        @Override
        public Object[] toArray()
        {
            Object[] tmp = new Object[vectorC.size()];

            vectorC.copyInto(tmp);

            return tmp;
        }

        @Override
        public Object[] toArray(Object[] a)
        {
            if (a == null)
                throw new NullPointerException();

            if (a.length < vectorC.size())
            {
                Object[] tmp = new Object[vectorC.size()];
                vectorC.copyInto(tmp);

                return tmp;
            } else
                vectorC.copyInto(a);

            return a;
        }
    }

    @Override
    public void clear()
    {

    }

    @Override
    public boolean containsKey(Object key)
    {
        return false;
    }

    @Override
    public boolean containsValue(Object value)
    {
        return false;
    }

    @Override
    public HSet entrySet()
    {
        return null;
    }

    @Override
    public Object get(Object key)
    {
        return null;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public HSet keySet()
    {
        return null;
    }

    @Override
    public Object put(Object key, Object value)
    {
        return null;
    }

    @Override
    public void putAll(HMap t)
    {

    }

    @Override
    public Object remove(Object key)
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public HCollection values()
    {
        return null;
    }
}
