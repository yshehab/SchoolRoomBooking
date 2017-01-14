/*
 * 
 * GPL 2.0
 * Intended for a single project
 */
package dateTime;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @param <T> T is any class that implements the Repeat interface
 * @author neo
 *
 * A Range creates a generic pseudo-Collection for a classes or Enums that implements the Repeat
 * interface.
 *
 * This collection is a Set.
 *
 */
public class Range<T extends Repeat> {
    
    private Set<T> range;

    //constructors
        
    /**
     *
     * @param start An object that implements Repeat to build the Range from.
     * @param end An object that implements Repeat to stop the Range.
     * @param rate How to build from start,
     */
    public Range(T start, T end, RepeatRate rate) {
        range = new HashSet<>();
        if (rate.getRate() == 0) {//SINGLE etc
            range.add(start);
        } else {
            while (!end.isAfter(start)) {//isAfter(start) returns false when start & end are equal
                range.add(start);
                start = (T) start.add(rate);
            }
        }
    }

    //package
    /**
     * Creates an empty Range
     */
    Range() {
        range = new HashSet<>();
    }

    //public protocol
    /**
     *
     * @param range A Range<T> of elements
     * @return A range<T> of elements that the Ranges share. Will return an empty range if the have
     * no elements in common.
     */
    public Range<T> intersect(Range<T> range) {
        Range<T> result = new Range<>();
        for (T isIn : this.range) {
            if (range.contains(isIn)) {
                result.add(isIn);
            }
        }
        return result;
    }

    /**
     *
     * @return the number of elements in the range.
     */
    public int size() {
        return range.size();
    }

    /**
     *
     * @param term a term
     * @return true if term is in the range
     */
    public boolean contains(T term) {
        return range.contains(term);
    }

    /**
     * @return a string representation of the receiver.
     * Of the form start to last, or start if a single element.
     */
    @Override
    public String toString() {
        String result;
        String first = this.getFirst().getDisplayString();
        String last = this.getLast().getDisplayString();
        if (first.equals(last)) {
            result = first;
        } else {
            result = first + " to " + last;
        }
        return result;

    }

    //package protocol
    /**
     *
     * @param term T will be added to the receiver if it does not already contain T.
     */
    void add(T term) {
        this.range.add(term);
    }

    /**
     *
     * @param term T will be added to the receiver if it does not already contain T.
     */
    void remove(T term) {
        this.range.remove(term);
    }

    //private methods
    private T getFirst() {
        T first = null;
        for (T element : this.range) {
            if (first == null || element.isAfter(first)) {
                first = element;
            }
        }
        return first;
    }

    private T getLast() {
        T last = null;
        for (T element : this.range) {
            if (last == null || !element.isAfter(last)) {
                last = element;
            }
        }
        return last;
    }

}
