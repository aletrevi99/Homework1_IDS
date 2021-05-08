package myAdapter;

public interface HListIterator extends HIterator
{
    //     Inserts the specified element into the list (optional operation).
    void add(Object o);

    //     Returns true if this list iterator has more elements when traversing the list in the forward direction.
    boolean hasNext();

    //     Returns true if this list iterator has more elements when traversing the list in the reverse direction.
    boolean hasPrevious();

    //     Returns the next element in the list.
    Object next();

    //     Returns the index of the element that would be returned by a subsequent call to next.
    int nextIndex();

    //     Returns the previous element in the list.
    Object previous();

    //     Returns the index of the element that would be returned by a subsequent call to previous.
    int previousIndex();

    //     Removes from the list the last element that was returned by next or previous (optional operation).
    void remove();

    //     Replaces the last element returned by next or previous with the specified element (optional operation).
    void set(Object o);
}
