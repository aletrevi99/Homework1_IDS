package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapter implements HList
{
    private Vector vectorList;

    private class HIteratorClass implements HIterator
    {
        protected Vector iter = null;
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

        HCollection l = null;
        if (o instanceof HCollection)
            l = (HCollection) o;
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
                remove(tmp);
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


    private class SubList extends ListAdapter
    {

        /**
         * Variable to indicate the lower bound (inclusive) of the vector to operate with.
         */
        private final int from;

        /**
         * Variable to indicate the upper bound (exclusive) of the vector to operate with.
         */
        private int to;

        /**
         * Constructor of a new SubList, given the well defined indexes.
         *
         * @param from the first index (inclusive).
         * @param to   the second index (exclusive).
         */
        public SubList(int from, int to)
        {
            this.from = from;
            this.to = to;
        }

        /**
         * Inserts the specified element at the specified position in this sublist.
         * Shifts the element currently at that position (if any) and any subsequent elements to the
         * right (adds one to their indices).
         * The object inserted in this list shall also be visible by the original list.
         *
         * @param index   index at which the specified element is to be inserted.
         * @param element element to be inserted.
         * @throws IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index > size())}.
         * @throws NullPointerException      if the specified element is null.
         */
        @Override
        public void add(int index, Object element)
        {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            ListAdapter.this.add(index + from, element);
            to++;
        }

        /**
         * Appends the specified element to the end of this sublist.
         * <p>
         * Lists that support this operation may place limitations on what elements may be added
         * to this list. In particular, this list will refute the insertion of null objects.
         * Any object inserted with this method shall also be visible by the original list
         *
         * @param o element to be appended to this list.
         * @return {@code true} (as specified by {@link HCollection#add}).
         * @throws NullPointerException if the specified element is null.
         */
        @Override
        public boolean add(Object o)
        {
            ListAdapter.this.add(to++, o);
            return true;
        }

        /**
         * Appends all of the elements in the specified collection to the end of this sublist,
         * in the order that they are returned by the specified collection's iterator.
         * The behavior of this operation is unspecified if the specified collection is modified
         * while the operation is in progress. (Note that this will occur if the specified collection is
         * this list, and it's nonempty.)
         * Any objected inserted into this sublist, shall also be visible by the original list.
         *
         * @param c collection whose elements are to be added to this list.
         * @return {@code true} if this list changed as a result of the call.}
         * @throws NullPointerException if the specified collection is {@code null}
         *                              or contains one or more null objects.
         */
        @Override
        public boolean addAll(HCollection c)
        {
            ListAdapter.this.addAll(to, c);
            to += c.size();

            return true;
        }

        /**
         * Inserts all of the elements in the specified collection into this sublist at the specified position.
         * Shifts the element currently at that position (if any) and any subsequent elements to the
         * right (increases their indices). The new elements will appear in this sublist in the order
         * that they are returned by the specified collection's iterator. The behavior of this operation
         * is unspecified if the specified collection is modified while the operation is in progress.
         * (Note that this will occur if the specified collection is this list, and it's nonempty.)
         * Any objected inserted into this sublist, shall also be visible by the original list.
         *
         * @param index index at which to insert first element from the specified collection.
         * @param c     elements to be inserted into this sublist.
         * @return {@code true} if this list changed as a result of the call.
         * @throws IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index > size())}.
         * @throws NullPointerException      if the specified collection is {@code null}
         *                                   or contains one or more null objects.
         * @see #add(Object)
         */
        @Override
        public boolean addAll(int index, HCollection c)
        {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            ListAdapter.this.addAll(from + index, c);
            to += c.size();

            return true;
        }

        /**
         * Removes all of the elements from this sublist.
         * This sublist will be empty after this call returns (unless it throws an exception).
         * Any objected removed by this method will also be removed from the original list.
         */
        @Override
        public void clear()
        {
            while (to > from)
                remove(to - from - 1);
        }

        /**
         * Returns {@code true} if this sublist contains the specified element. More formally,
         * returns {@code true} if and only if this list contains at least one element {@code e} such
         * that {@code (o==null ? e==null : o.equals(e))}. (Note, if {@code contains} of this sublist returns
         * true, the {@code contains} method of the original list shall also return true.)
         *
         * @param o element whose presence in this sublist is to be tested.
         * @return {@code true} if this sublist contains the specified element.
         * @throws NullPointerException if the specified element is {@code null}.
         */
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

        /**
         * Returns true if this sublist contains all of the elements of the specified collection.
         *
         * @param c collection to be checked for containment in this list.
         * @return {@code true} if this sublist contains all of the elements of the specified collection.
         * @throws NullPointerException if the specified collection is null or contains null elements.
         * @see #contains(Object)
         */
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

        /**
         * Compares the specified object with this sublist for equality. Returns {@code true} if
         * and only if the specified object is also a list or sublist, both lists have the same size, and
         * all corresponding pairs of elements in the two lists are <i>equal</i>. (Two
         * elements {@code e1} and {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null :
         * e1.equals(e2)).)} In other words, two lists are defined to be <i>equal</i>
         * if they contain the same elements in the same order.
         *
         * @param o the object to be compared for equality with this list.
         * @return {@code true} if the specified object is equal to this list.
         */
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

        /**
         * Returns the element at the specified position in this sublist.
         *
         * @param index index of element to return.
         * @return the element at the specified position in this sublist.
         * @throws IndexOutOfBoundsException if the index is out of range
         *                                   {@code (index < 0 || index >= size())}.
         */
        @Override
        public Object get(int index)
        {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();

            return ListAdapter.this.get(from + index);
        }

        /**
         * Returns the hash code value for this sublist. The hash code of a list is defined to be the
         * result of the following calculation:
         * {@code
         * hashCode = 1;
         * Iterator i = list.iterator();
         * while (i.hasNext()) {
         * Object obj = i.next();
         * hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
         * }
         * }
         * This ensures that {@code list1.equals(list2)} implies that {@code list1.hashCode()==list2.hashCode()}
         * for any two lists, {@code list1} and {@code list2}, as required by the general contract
         * {@code of Object.hashCode}.
         * (Note, a sublist and a list may share the same hash code, if {@code sublist.equals(list)}
         * and {@code list.equals.(sublist)} both return {@code true}
         *
         * @return the hash code value for this list.
         */
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

        /**
         * Returns the index in this sublist of the first occurrence of the specified element,
         * or -1 if this sublist does not contain this element. More formally, returns the lowest
         * index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))},
         * or -1 if there is no such index.
         *
         * @param o element to search for.
         * @return the index in this sublist of the first occurrence of the specified
         * element, or -1 if this sublist does not contain this element.
         * @throws NullPointerException if the specified element is null.
         */
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

        /**
         * Returns {@code true} if this sublist contains no elements.
         *
         * @return {@code true} if this sublist contains no elements.
         */
        @Override
        public boolean isEmpty()
        {
            return size() == 0;
        }

        /**
         * Returns an iterator over the elements in this sublist in proper sequence.
         * (Note, any changes through iteration with this method will reflect in the original list.)
         *
         * @return an iterator over the elements in this sublist in proper sequence.
         */
        @Override
        public HIterator iterator()
        {
            return new HSubListIteratorClass(ListAdapter.this.vectorList, from, from, to);
        }

        /**
         * Returns the index in this sublist of the last occurrence of the specified element,
         * or -1 if this sublist does not contain this element. More formally, returns the highest index
         * i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}, or -1 if there is no such index.
         *
         * @param o element to search for.
         * @return the index in this sublist of the last occurrence of the specified element,
         * or -1 if this sublist does not contain this element.
         * @throws NullPointerException if the specified element is null.
         */
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

        /**
         * Returns a list iterator of the elements in this sublist (in proper sequence).
         * (Note, any changes through iteration with this method will reflect in the original list.)
         *
         * @return a list iterator of the elements in this sublist (in proper sequence).
         */
        @Override
        public HListIterator listIterator()
        {
            return new HSubListIteratorClass(ListAdapter.this.vectorList, from, from, to);
        }

        /**
         * Returns a list iterator of the elements in this sublist (in proper sequence),
         * starting at the specified position in this sublist. The specified index indicates the
         * first element that would be returned by an initial call to the {@code next} method. An
         * initial call to the {@code previous} method would return the element with the specified
         * index minus one.
         * (Note, any changes through iteration with this method will reflect in the original list.)
         *
         * @param index index of first element to be returned from
         *              the list iterator (by a call to the next method).
         * @return a list iterator of the elements in this sublist (in proper sequence),
         * starting at the specified position in this sublist.
         * @throws IndexOutOfBoundsException if the index is out of range
         *                                   {@code (index < 0 || index > size())}.
         */
        @Override
        public HListIterator listIterator(int index)
        {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();

            return new HSubListIteratorClass(ListAdapter.this.vectorList, from + index, from, to);
        }

        /**
         * Removes the element at the specified position in this sublist. Shifts any
         * subsequent elements to the left (subtracts one from their indices). Returns
         * the element that was removed from the list.
         * The object removed by this method will also be removed from the original list.
         *
         * @param index the index of the element to removed.
         * @return the element previously at the specified position.
         * @throws IndexOutOfBoundsException if the index is out of
         *                                   range {@code (index < 0 || index >= size())}.
         */
        @Override
        public Object remove(int index)
        {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();

            to--;
            return ListAdapter.this.remove(index + from);
        }

        /**
         * Removes the first occurrence in this sublist of the specified element. If this
         * sublist does not contain the element, it is unchanged. More formally, removes
         * the element with the lowest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}
         * (if such an element exists).
         * The object removed by this method will also be removed from the original list.
         *
         * @param o element to be removed from this sublist, if present.
         * @return {@code true} if this list contained the specified element.
         * @throws NullPointerException if the specified element is null.
         */
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

        /**
         * Removes from this sublist all the elements that are contained in the specified collection.
         * Any object removed by this method will also be removed from the original list.
         *
         * @param c collection that defines which elements will be removed from this sublist.
         * @return {@code true} if this sublist changed as a result of the call.
         * @see #remove(Object)
         * @see #contains(Object)
         */
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

        /**
         * Retains only the elements in this sublist that are contained in the
         * specified collection. In other words, removes from this sublist all
         * the elements that are not contained in the specified collection.
         * Any object removed by this method will also be removed from the original list.
         *
         * @param c collection that defines which elements this sublist will retain.
         * @return {@code true} if this sublist changed as a result of the call.
         * @see #remove(Object)
         * @see #contains(Object)
         */
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
                    remove(tmp);
            }

            return size != this.size();
        }

        /**
         * Replaces the element at the specified position in this sublist with
         * the specified element.
         * The object replaced by this method will also be replaced in the original list.
         *
         * @param index   index of element to replace.
         * @param element element to be stored at the specified position.
         * @return the element previously at the specified position.
         * @throws NullPointerException      if the specified element is null.
         * @throws IndexOutOfBoundsException if the index is out of range
         *                                   {@code (index < 0 || index >= size())}.
         */
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

        /**
         * Returns the number of elements in this sublist.
         *
         * @return the number of elements in this sublist.
         */
        @Override
        public int size()
        {
            return to - from;
        }

        /**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException
         */
        @Override
        public HList subList(int fromIndex, int toIndex)
        {
            throw new UnsupportedOperationException();
        }

        /**
         * Returns an array containing all of the elements in this sublist in proper sequence.
         *
         * @return an array containing all of the elements in this sublist in proper sequence.
         */
        @Override
        public Object[] toArray()
        {
            Object[] tmp = new Object[size()];

            for (int i = 0; i < size(); i++)
                tmp[i] = get(i);

            return tmp;
        }

        /**
         * Returns an array containing all of the elements in this sublist in proper
         * sequence; the runtime type of the returned array is that of the specified array.
         *
         * @param a the array into which the elements of this sublist are to be
         *          stored, if it is big enough; otherwise, a new {@code Object[]} array
         *          is created for this purpose.
         * @return an array containing the elements of this sublist.
         * @throws ArrayStoreException  if the runtime type of the specified array is
         *                              not a supertype of the runtime type of every element in this list.
         * @throws NullPointerException if the specified array is {@code null}.
         */
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

        /**
         * SubListIterator over a SubList object. It can traverse the vector associated
         * with the SubList forwards and in reverse, perform adding, removing
         * operations. Any change to the SubList will reflect into the original
         * vector of ListAdapter.
         */
        private class HSubListIteratorClass extends HListIteratorClass implements HListIterator
        {

            /**
             * Variable used to set a lower-bound to the navigation
             * of a Vector. Useful for navigating any sublist, or list
             * down to this bound.
             */
            private final int from;

            /**
             * Variable used to set an upper-bound to the navigation
             * of a Vector. Useful for navigating any sublist, or list
             * up to this bound.
             */
            private int to;

            /**
             * Constructor of a sub list iterator.
             * Any changes through iteration will reflect on the original list.
             *
             * @param v    the given Vector to iterate on.
             * @param index  the starting index, bound between from and to.
             * @param from the lower bound.
             * @param to   the upper bound.
             */
            public HSubListIteratorClass(Vector v, int index, int from, int to)
            {
                super(v, index);
                this.to = to;
                this.from = from;
            }

            /**
             * Inserts the specified element into the sublist. The element is inserted immediately
             * before the next element that would be returned by {@code next}, if any,
             * and after the next element that would be returned by {@code previous}, if any.
             * (If the sublist contains no elements, the new element becomes the sole element on the list.)
             * The new element is inserted before the implicit cursor: a subsequent call to {@code next}
             * would be unaffected, and a subsequent call to {@code previous} would return the new element.
             * (This call increases by one the value that would be returned by a call to {@code nextIndex}
             * or {@code previousIndex}.)
             * Any element inserted will be visible by the original list
             *
             * @param o the element to insert.
             * @throws NullPointerException if the specified element is null.
             */
            @Override
            public void add(Object o)
            {
                to++;
                ListAdapter.SubList.this.to = to;
                super.add(o);
            }

            /**
             * Returns {@code true} if the iteration has more elements. (In
             * other words, returns {@code true} if {@code next} would return an element
             * rather than throwing an exception).
             *
             * @return {@code true} if the list iterator has more elements when traversing
             * the sublist in the forward direction.
             */
            @Override
            public boolean hasNext()
            {
                return current < to;
            }

            /**
             * Returns {@code true} if this sublist iterator has more elements when traversing the
             * list in the reverse direction. (In other words, returns {@code true} if
             * {@code previous} would return an element rather than throwing an exception).
             *
             * @return {@code true} if the list iterator has more elements when traversing
             * the list in the reverse direction.
             */
            @Override
            public boolean hasPrevious()
            {
                return current > from;
            }

            /**
             * Returns the next element in the sublist. This method may be called repeatedly
             * to iterate through the sublist, or intermixed with calls to {@code previous} to go
             * back and forth. (Note that alternating calls to {@code next} and {@code previous}
             * will return the same element repeatedly).
             *
             * @return the next element in the sublist.
             * @throws NoSuchElementException if the iteration has no next element.
             */
            @Override
            public Object next()
            {
                if (!hasNext())
                    throw new NoSuchElementException();

                return super.next();
            }

            /**
             * Returns the index of the element that would be returned by a subsequent
             * call to {@code next}. (Returns sublist size if the list iterator is at the
             * end of the sublist.)
             *
             * @return the index of the element that would be returned by a
             * subsequent call to {@code next}, or sublist size if the
             * sublist iterator is at end of sublist.
             */
            @Override
            public int nextIndex()
            {
                return current - from;
            }

            /**
             * Returns the previous element in the sublist. This method may be called repeatedly
             * to iterate through the sublist backwards, or intermixed with calls to {@code next} to go
             * back and forth. (Note that alternating calls to {@code next} and {@code previous}
             * will return the same element repeatedly.)
             *
             * @return the previous element in the sublist.
             * @throws NoSuchElementException if the iteration has no previous element.
             */
            @Override
            public Object previous()
            {
                if (!hasPrevious())
                    throw new NoSuchElementException();

                return super.previous();
            }

            /**
             * Returns the index of the element that would be returned by a subsequent
             * call to {@code previous}. (Returns -1 if the list iterator is at the beginning
             * of the sublist.)
             *
             * @return the index of the element that would be returned by a subsequent call
             * to {@code previous}, or -1 if sublist iterator is at beginning of sublist.
             */
            @Override
            public int previousIndex()
            {
                return current - from - 1;
            }

            /**
             * Removes from the sublist the last element that was returned by {@code next} or
             * {@code previous}. This call can only be made once per call to {@code next} or
             * {@code previous}. It can be made only if {@code SubListIterator.add} has not been
             * called after the last call to {@code next} or {@code previous}.
             * Any element removed by this method will also be removed from the original list.
             *
             * @throws IllegalStateException neither {@code next} nor {@code previous} have
             *                               been called, or {@code remove} or {@code add} have been called after
             *                               the last call to {@code previous} or {@code previous}.
             */
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

            /**
             * Replaces the last element returned by {@code next} or {@code previous} with
             * the specified element. This call can be made only if neither {@code ListIterator.remove}
             * nor {@code ListIterator.add} have been called after the last call to {@code next}
             * or {@code previous}.
             * Any object replaced by this method will be also replaced in the original list.
             *
             * @param o the element to insert.
             * @throws IllegalStateException    if neither next nor previous have been called,
             *                                  or {@code remove} or {@code add} have been called after the last call
             *                                  to {@code next} or {@code previous}.
             * @throws IllegalArgumentException if some aspect of the specified element prevents
             *                                  it from being added to this list.
             * @throws NullPointerException     if the element to be set is null.
             */
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
