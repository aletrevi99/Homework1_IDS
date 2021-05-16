package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapter implements HList
{
    private final Vector vectorList;

    public ListAdapter()
    {
        this(new Vector());
    }

    public ListAdapter(Vector v)
    {
        vectorList = v;
    }

    @Override
    public void add(int index, Object element)
    {
        if (element == null)
            throw new NullPointerException();

        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        vectorList.insertElementAt(element, index);
    }

    @Override
    public boolean add(Object o)
    {
        if (o == null)
            throw new NullPointerException();

        vectorList.addElement(o);
        return true;
    }

    @Override
    public boolean addAll(HCollection c)
    {
        if (c == null)
            throw new NullPointerException();

        HIterator iter = c.iterator();
        HIterator iterCheck = c.iterator();

        while (iterCheck.hasNext())
            if (iterCheck.next() == null)
                throw new NullPointerException();

        if (c.isEmpty())
            return false;

        while (iter.hasNext())
            vectorList.addElement(iter.next());

        return true;
    }

    @Override
    public boolean addAll(int index, HCollection c)
    {
        if (c == null)
            throw new NullPointerException();

        HIterator iter = c.iterator();
        HIterator iterCheck = c.iterator();

        while (iterCheck.hasNext())
            if (iterCheck.next() == null)
                throw new NullPointerException();

        if (c.isEmpty())
            return false;

        while (iter.hasNext())
        {
            try
            {
                vectorList.insertElementAt(iter.next(), index++);
            } catch (ArrayIndexOutOfBoundsException aioobe)
            {
                throw new IndexOutOfBoundsException();
            }
        }

        return true;
    }

    @Override
    public void clear()
    {
        vectorList.removeAllElements();
    }

    @Override
    public boolean contains(Object o)
    {
        if (o == null)
            throw new NullPointerException();

        return vectorList.contains(o);
    }

    @Override
    public boolean containsAll(HCollection c)
    {
        if (c == null)
            throw new NullPointerException();

        if (!(c instanceof ListAdapter))
            throw new ClassCastException();

        ListAdapter coll = (ListAdapter) c;
        HIterator iter = coll.iterator();
        HIterator iterCheck = coll.iterator();

        while (iterCheck.hasNext())
            if (iterCheck.next() == null)
                throw new NullPointerException();

        while (iter.hasNext())
            if (!contains(iter.next()))
                return false;

        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;

        ListAdapter l = null;
        if (o instanceof ListAdapter)
            l = (ListAdapter) o;
        else
            return false;

        HIterator iter = l.iterator();
        if (l.size() != size())
            return false;

        for (int i = 0; i < size(); i++)
            if (!iter.next().equals(get(i)))
                return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 1;

        HIterator iter = iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            hash = 31 * hash + (obj == null ? 0 : obj.hashCode());
        }
        return hash;
    }

    @Override
    public Object get(int index)
    {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        return vectorList.elementAt(index);
    }

    @Override
    public int indexOf(Object o)
    {
        if (o == null)
            throw new NullPointerException();

        return vectorList.indexOf(o);
    }

    @Override
    public boolean isEmpty()
    {
        return vectorList.isEmpty();
    }

    @Override
    public HIterator iterator()
    {
        return new HIteratorClass(vectorList);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        if (o == null)
            throw new NullPointerException();

        return vectorList.lastIndexOf(o);
    }

    @Override
    public HListIterator listIterator()
    {
        return new HListIteratorClass(vectorList);
    }

    @Override
    public HListIterator listIterator(int index)
    {
        if (index < 0 || index > vectorList.size())
            throw new IndexOutOfBoundsException();

        return new HListIteratorClass(vectorList, index);
    }

    @Override
    public Object remove(int index)
    {
        try
        {
            Object tmp = vectorList.elementAt(index);
            vectorList.removeElementAt(index);

            return tmp;
        } catch (ArrayIndexOutOfBoundsException aioobe)
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(Object o)
    {
        if (o == null)
            throw new NullPointerException();

        return vectorList.removeElement(o);
    }

    @Override
    public boolean removeAll(HCollection c)
    {
        if (c == null)
            throw new NullPointerException();

        if (!(c instanceof ListAdapter))
            throw new ClassCastException();

        ListAdapter coll = (ListAdapter) c;
        HIterator iter = coll.iterator();
        HIterator iterCheck = coll.iterator();

        while (iterCheck.hasNext())
            if (iterCheck.next() == null)
                throw new NullPointerException();

        if (coll.isEmpty())
            return false;

        int size = this.size();
        Object tmp = null;
        while (iter.hasNext())
        {
            tmp = iter.next();
            if (this.contains(tmp))
                remove(tmp);
        }

        return size != this.size();
    }

    @Override
    public boolean retainAll(HCollection c)
    {
        if (c == null)
            throw new NullPointerException();

        if (!(c instanceof ListAdapter))
            throw new ClassCastException();

        ListAdapter coll = (ListAdapter) c;
        HIterator iterCheck = coll.iterator();

        while (iterCheck.hasNext())
            if (iterCheck.next() == null)
                throw new NullPointerException();

        if (coll.isEmpty())
            return false;

        HIterator iter = iterator();
        int size = this.size();

        Object tmp = null;
        while (iter.hasNext())
        {
            tmp = iter.next();
            if (!coll.contains(tmp))
                iter.remove();
        }

        return size != this.size();
    }

    @Override
    public Object set(int index, Object element)
    {
        if (element == null)
            throw new NullPointerException();

        try
        {
            Object tmp = vectorList.elementAt(index);
            vectorList.setElementAt(element, index);

            return tmp;
        } catch (ArrayIndexOutOfBoundsException aioobe)
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size()
    {
        return vectorList.size();
    }

    @Override
    public HList subList(int fromIndex, int toIndex)
    {
        if (fromIndex < 0 || toIndex > vectorList.size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();

        return new SubList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray()
    {
        Object[] tmp = new Object[vectorList.size()];

        vectorList.copyInto(tmp);

        return tmp;
    }

    @Override
    public Object[] toArray(Object[] a)
    {
        if (a == null)
            throw new NullPointerException();

        if (a.length < vectorList.size())
            return toArray();
        else
            vectorList.copyInto(a);

        return a;
    }

    private class HIteratorClass implements HIterator
    {
        protected Vector iter;
        protected int current;
        protected boolean doneNext;

        public HIteratorClass(Vector v)
        {
            this(v, 0);
            doneNext = false;
        }

        public HIteratorClass(Vector v, int index)
        {
            iter = v;
            current = index;
            doneNext = false;
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

    private class HListIteratorClass extends HIteratorClass implements HListIterator
    {
        protected boolean donePrev;

        public HListIteratorClass(Vector v)
        {
            super(v);
            donePrev = false;
        }

        public HListIteratorClass(Vector v, int index)
        {
            super(v, index);
            donePrev = false;
        }

        @Override
        public void add(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            iter.insertElementAt(o, current++);
            doneNext = donePrev = false;
        }

        @Override
        public boolean hasNext()
        {
            return super.hasNext();
        }

        @Override
        public boolean hasPrevious()
        {
            return current - 1 >= 0;
        }

        @Override
        public Object next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            doneNext = true;
            donePrev = false;
            return super.next();
        }

        @Override
        public int nextIndex()
        {
            return current;
        }

        @Override
        public Object previous() throws NoSuchElementException
        {
            if (!hasPrevious())
                throw new NoSuchElementException();

            donePrev = true;
            doneNext = false;

            return iter.elementAt(--current);
        }

        @Override
        public int previousIndex()
        {
            return current - 1;
        }

        @Override
        public void remove()
        {
            if (!doneNext && !donePrev)
                throw new IllegalStateException();
            else if (donePrev)
            {
                iter.removeElementAt(current);
                donePrev = doneNext = false;

                return;
            } else
                super.remove();
        }

        @Override
        public void set(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            if (iter.isEmpty())
            {
                add(o);
                return;
            }

            if (!doneNext && !donePrev)
                throw new IllegalStateException();
            else if (doneNext)
                iter.setElementAt(o, current - 1);
            else if (donePrev)
                iter.setElementAt(o, current);
        }
    }

    private class SubList extends ListAdapter
    {
        private final int from;

        private int to;

        public SubList(int from, int to)
        {
            this.from = from;
            this.to = to;
        }

        @Override
        public void add(int index, Object element)
        {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            if (element == null)
                throw new NullPointerException();

            ListAdapter.this.add(index + from, element);
            to++;
        }

        @Override
        public boolean add(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            ListAdapter.this.add(to++, o);
            return true;
        }

        @Override
        public boolean addAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            ListAdapter.this.addAll(to, c);
            to += c.size();

            return true;
        }

        @Override
        public boolean addAll(int index, HCollection c)
        {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            if (c == null)
                throw new NullPointerException();

            ListAdapter.this.addAll(from + index, c);
            to += c.size();

            return true;
        }

        @Override
        public void clear()
        {
            while (to > from)
                remove(to - from - 1);
        }

        @Override
        public boolean contains(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            if (isEmpty())
                return false;

            int count = 0;
            while (count < size())
            {
                if (ListAdapter.this.get(count + from).equals(o))
                    return true;

                count++;
            }

            return false;
        }

        @Override
        public boolean containsAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            if (!(c instanceof ListAdapter))
                throw new ClassCastException();

            ListAdapter coll = (ListAdapter) c;
            HIterator iter = coll.iterator();
            HIterator iterCheck = coll.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            boolean tmp = true;

            while (iter.hasNext())
                tmp &= contains(iter.next());

            return tmp;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == null)
                return false;

            HCollection l;
            if (o instanceof HCollection)
                l = (HCollection) o;
            else
                return false;

            if (l.size() != size())
                return false;

            HIterator iter = l.iterator();
            for (int i = 0; i < size(); i++)
                if (!ListAdapter.this.get(from + i).equals(iter.next()))
                    return false;

            return true;
        }

        @Override
        public Object get(int index)
        {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();

            return ListAdapter.this.get(from + index);
        }

        @Override
        public int hashCode()
        {
            int hash = 1;

            HListIterator iter = ListAdapter.SubList.this.listIterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                hash = 31 * hash + (obj == null ? 0 : obj.hashCode());
            }

            return hash;
        }

        @Override
        public int indexOf(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            for (int i = from; i < to; i++)
                if (ListAdapter.this.get(i).equals(o))
                    return i - from;

            return -1;
        }

        @Override
        public boolean isEmpty()
        {
            return size() == 0;
        }

        @Override
        public HIterator iterator()
        {
            return new HSubListIteratorClass(ListAdapter.this.vectorList, from, from, to);
        }

        @Override
        public int lastIndexOf(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            HIterator iter = iterator();
            int index = 0;
            int retVal = -1;

            while (iter.hasNext())
            {
                if (iter.next().equals(o))
                    retVal = index;
                index++;
            }

            return retVal;
        }

        @Override
        public HListIterator listIterator()
        {
            return new HSubListIteratorClass(ListAdapter.this.vectorList, from, from, to);
        }

        @Override
        public HListIterator listIterator(int index)
        {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            return new HSubListIteratorClass(ListAdapter.this.vectorList, from + index, from, to);
        }

        @Override
        public Object remove(int index)
        {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();

            to--;
            return ListAdapter.this.remove(index + from);
        }

        @Override
        public boolean remove(Object o)
        {
            if (o == null)
                throw new NullPointerException();

            int index = indexOf(o);
            if (index >= 0)
                remove(index);

            return index >= 0;
        }

        @Override
        public boolean removeAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            if (!(c instanceof ListAdapter))
                throw new ClassCastException();

            ListAdapter coll = (ListAdapter) c;
            HIterator iter = coll.iterator();
            HIterator iterCheck = coll.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            if (coll.isEmpty())
                return false;

            int size = this.size();
            Object tmp = null;
            while (iter.hasNext())
            {
                tmp = iter.next();
                if (this.contains(tmp))
                    remove(tmp);
            }

            return size != this.size();
        }

        @Override
        public boolean retainAll(HCollection c)
        {
            if (c == null)
                throw new NullPointerException();

            if (!(c instanceof ListAdapter))
                throw new ClassCastException();

            ListAdapter coll = (ListAdapter) c;
            HIterator iterCheck = coll.iterator();

            while (iterCheck.hasNext())
                if (iterCheck.next() == null)
                    throw new NullPointerException();

            if (coll.isEmpty())
                return false;

            HIterator iter = iterator();
            int size = this.size();

            Object tmp = null;
            while (iter.hasNext())
            {
                tmp = iter.next();
                if (!coll.contains(tmp))
                    iter.remove();
            }

            return size != this.size();
        }

        @Override
        public Object set(int index, Object element)
        {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();

            if (element == null)
                throw new NullPointerException();

            Object tmp = ListAdapter.this.get(index + from);
            ListAdapter.this.set(index + from, element);

            return tmp;
        }

        @Override
        public int size()
        {
            return to - from;
        }

        @Override
        public HList subList(int fromIndex, int toIndex)
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object[] toArray()
        {
            Object[] tmp = new Object[size()];

            for (int i = 0; i < size(); i++)
                tmp[i] = get(i);

            return tmp;
        }

        @Override
        public Object[] toArray(Object[] a)
        {
            if (a == null)
                throw new NullPointerException();

            if (a.length < size())
                return toArray();

            for (int i = 0; i < size(); i++)
                a[i] = get(i);

            return a;
        }

        private class HSubListIteratorClass extends HListIteratorClass implements HListIterator
        {
            private final int from;
            private int to;

            public HSubListIteratorClass(Vector v, int index, int from, int to)
            {
                super(v, index);
                this.to = to;
                this.from = from;
            }

            @Override
            public void add(Object o)
            {
                if (o == null)
                    throw new NullPointerException();
                to++;
                ListAdapter.SubList.this.to = to;
                super.add(o);
            }

            @Override
            public boolean hasNext()
            {
                return current < to;
            }

            @Override
            public boolean hasPrevious()
            {
                return current > from;
            }

            @Override
            public Object next()
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                return super.next();
            }

            @Override
            public int nextIndex()
            {
                return current - from;
            }

            @Override
            public Object previous()
            {
                if (!hasPrevious())
                    throw new NoSuchElementException();

                return super.previous();
            }

            @Override
            public int previousIndex()
            {
                return current - from - 1;
            }

            @Override
            public void remove()
            {
                if (!doneNext && !donePrev)
                    throw new IllegalStateException();
                else if (donePrev)
                {
                    vectorList.removeElementAt(current);
                    donePrev = doneNext = false;
                } else
                {
                    doneNext = false;
                    vectorList.removeElementAt(--current);
                }
                to--;
                SubList.this.to = to;
            }

            @Override
            public void set(Object o)
            {
                if (o == null)
                    throw new NullPointerException();
                if (from == to)
                {
                    add(o);
                    return;
                }
                if (!doneNext && !donePrev)
                    throw new IllegalStateException();
                else if (doneNext)
                    vectorList.setElementAt(o, current - 1);
                else if (donePrev)
                    vectorList.setElementAt(o, current);
            }
        }
    }
}