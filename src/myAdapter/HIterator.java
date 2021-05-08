package myAdapter;

public interface HIterator
{
    //     Returns true if the iteration has more elements.
    boolean hasNext();

    //     Returns the next element in the iteration.
    Object next();

    //     Removes from the underlying collection the last element returned by the iterator (optional operation).
    void remove();
}
