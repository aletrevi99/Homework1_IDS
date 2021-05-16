package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class MapAdapter implements HMap
{
    private final Hashtable hashMap;

    public MapAdapter()
    {
        hashMap = new Hashtable();
    }

    @Override
    public void clear()
    {
        hashMap.clear();
    }

    @Override
    public boolean containsKey(Object key)
    {
        if (key == null)
            throw new NullPointerException();

        return hashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        if (value == null)
            throw new NullPointerException();

        return hashMap.contains(value);
    }

    @Override
    public HSet entrySet()
    {
        return new EntrySet(this);
    }

    @Override
    public Object get(Object key)
    {
        if (key == null)
            throw new NullPointerException();

        return hashMap.get(key);
    }

    @Override
    public boolean isEmpty()
    {
        return hashMap.isEmpty();
    }

    @Override
    public HSet keySet()
    {
        return new KeySet(this);
    }

    @Override
    public Object put(Object key, Object value)
    {
        if (key == null || value == null)
            throw new NullPointerException();

        return hashMap.put(key, value);
    }

    @Override
    public void putAll(HMap t)
    {
        if (t == null)
            throw new NullPointerException();

        HSet s = t.entrySet();

        HIterator iterCheck = s.iterator();

        Entry tmp = null;
        while (iterCheck.hasNext())
        {
            tmp = (Entry) iterCheck.next();

            if (tmp == null)
                throw new NullPointerException();

            if (tmp.getKey() == null || tmp.getValue() == null)
                throw new NullPointerException();
        }

        HIterator iter = s.iterator();

        Entry val = null;
        while (iter.hasNext())
        {
            val = (Entry) iter.next();
            put(val.getKey(), val.getValue());
        }
    }

    @Override
    public Object remove(Object key)
    {
        if (key == null)
            throw new NullPointerException();

        return hashMap.remove(key);
    }

    @Override
    public int size()
    {
        return hashMap.size();
    }

    @Override
    public HCollection values()
    {
        return new ValuesCollection(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;

        if (!(o instanceof HMap))
            return false;

        HMap tmp = (HMap) o;
        return this.entrySet().equals(tmp.entrySet());
    }

    @Override
    public int hashCode()
    {
        HSet s = entrySet();
        HIterator iter = s.iterator();
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
            if (o == null)
                return false;

            Entry tmp = (Entry) o;

            return tmp.getValue().equals(this.getValue()) && tmp.getKey().equals(this.getKey());
        }
    }

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

            if (((Entry) o).getKey() == null || ((Entry) o).getValue() == null)
                throw new NullPointerException();

            if (hashTable.containsKey(((Entry) o).getKey()))
                return hashTable.get(((Entry) o).getKey()).equals(((Entry) o).getValue());

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
            Entry tmp;
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
            Entry obj;
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

            Entry tmp;
            while (iter.hasNext())
            {
                tmp = (Entry) iter.next();
                if (!c.contains(tmp))
                    iter.remove();
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

            int j = this.size();
            while (iter.hasNext())
                tmp[--j] = iter.next();

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

            int j = this.size();
            while (iter.hasNext())
                a[--j] = iter.next();

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
                return false;

            if (!(o instanceof EntrySet))
                return false;

            EntrySet es = (EntrySet) o;
            HIterator iter = es.iterator();

            Entry tmp;
            while (iter.hasNext())
            {
                tmp = (Entry) iter.next();

                if (tmp.getKey() == null || tmp.getValue() == null)
                    return false;
            }

            return this.hashCode() == es.hashCode();
        }
    }

    private class KeySet implements HSet
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

                return lastKey;
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

        public KeySet(MapAdapter m)
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

            return hashTable.containsKey(o);
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheck = c.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            HIterator iter = c.iterator();

            while (iter.hasNext())
                if (!contains(iter.next()))
                    return false;

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

            if (this.contains(o))
            {
                MapAdapter.this.remove(o);
                return true;
            }

            return false;
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheck = c.iterator();
            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            HIterator iter = c.iterator();
            int size = hashTable.size();

            Object tmp = null;
            while (iter.hasNext())
            {
                tmp = iter.next();
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

            HIterator iterCheck = c.iterator();
            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            HIterator iter = iterator();
            int size = hashTable.size();

            Object tmp;
            while (iter.hasNext())
            {
                tmp = iter.next();
                if (!c.contains(tmp))
                    iter.remove();
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

            int j = this.size();
            while (iter.hasNext())
                tmp[--j] = iter.next();

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

            int j = this.size();
            while (iter.hasNext())
                a[--j] = iter.next();

            return a;
        }

        @Override
        public int hashCode()
        {
            HIterator iter = this.iterator();

            int hash = 0;
            Object tmp;

            while (iter.hasNext())
            {
                tmp = iter.next();
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
                return false;

            if (!(o instanceof KeySet))
                return false;

            KeySet ks = (KeySet) o;
            HIterator iter = ks.iterator();

            while (iter.hasNext())
                if (iter.next() == null)
                    return false;

            return this.hashCode() == ks.hashCode();
        }
    }

    private class ValuesCollection implements HCollection
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

                return iter.get(lastKey);
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

        public ValuesCollection(MapAdapter m)
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

            HIterator iter = iterator();

            while (iter.hasNext())
                if ((iter.next()).equals(o))
                    return true;

            return false;
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheck = c.iterator();
            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            HIterator iter = c.iterator();

            while (iter.hasNext())
                if (contains(iter.next()))
                    return true;

            return false;
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

            HIterator iter = iterator();

            while (iter.hasNext())
                if (iter.next() == o)
                {
                    iter.remove();
                    return true;
                }

            return false;
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            HIterator iterCheck = c.iterator();
            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            HIterator iter = c.iterator();
            int size = hashTable.size();

            Object tmp;
            while (iter.hasNext())
            {
                tmp = iter.next();
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

            HIterator iterCheck = c.iterator();
            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            HIterator iter = iterator();
            int size = hashTable.size();

            Object tmp;
            while (iter.hasNext())
            {
                tmp = iter.next();
                if (!c.contains(tmp))
                    iter.remove();
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

            int j = this.size();
            while (iter.hasNext())
                tmp[--j] = iter.next();

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

            int j = this.size();
            while (iter.hasNext())
                a[--j] = iter.next();

            return a;
        }

        @Override
        public int hashCode()
        {
            HIterator iter = this.iterator();

            int hash = 0;
            Object tmp;

            while (iter.hasNext())
            {
                tmp = iter.next();
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
                return false;

            if (!(o instanceof ValuesCollection))
                return false;

            ValuesCollection vc = (ValuesCollection) o;
            HIterator iter = vc.iterator();

            while (iter.hasNext())
                if (iter.next() == null)
                    return false;

            return this.hashCode() == vc.hashCode();
        }
    }
}