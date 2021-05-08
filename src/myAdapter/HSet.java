package myAdapter;

public interface HSet extends HCollection
{
    // Adds the specified element to this set if it is not already present (optional operation).
    boolean add(Object o);

    // Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
    boolean addAll(HCollection c);

    // Removes all of the elements from this set (optional operation).
    void clear();

    // Returns true if this set contains the specified element.
    boolean contains(Object o);

    // Returns true if this set contains all of the elements of the specified collection.
    boolean containsAll(HCollection c);

    // Compares the specified object with this set for equality.
    boolean equals(Object o);

    // Returns the hash code value for this set.
    int hashCode();

    // Returns true if this set contains no elements.
    boolean isEmpty();

    // Returns an iterator over the elements in this set.
    HIterator iterator();

    // Removes the specified element from this set if it is present (optional operation).
    boolean remove(Object o);

    // Removes from this set all of its elements that are contained in the specified collection (optional operation).
    boolean removeAll(HCollection c);

    // Retains only the elements in this set that are contained in the specified collection (optional operation).
    boolean retainAll(HCollection c);

    // Returns the number of elements in this set (its cardinality).
    int size();

    // Returns an array containing all of the elements in this set.
    Object[] toArray();

    // Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
    Object[] toArray(Object[] a);
}
