package myAdapter;

public interface Entry
{
    //     Compares the specified object with this entry for equality.
    boolean equals(Object o);

    //     Returns the key corresponding to this entry.
    Object getKey();

    //     Returns the value corresponding to this entry.
    Object getValue();

    //     Returns the value corresponding to this entry.
    int hashCode();

    //     Replaces the value corresponding to this entry with the specified value (optional operation).
    Object setValue(Object value);
}
