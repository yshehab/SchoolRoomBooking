/* 
 * GPL 2.0
 * 
 * version 1.0
 * 
 * Intended for a single project
 */
package dateTime;

/**
 * Repeat is an interface that Classes and Enums in the dateTime package must implement. 
 * @author neo
 */
public interface Repeat<T> {  

    /**
     *
     * @param rate A RepeatRate Enum.
     */
    public T add(RepeatRate rate);

    /**
     *
     * @param o An object of an implementing class or enum.
     * @return true if the receiver is ordered before or equal to the argument, otherwise false.
     */
    public boolean isAfter(T o);
    
    /**
     * 
     */
    public String getDisplayString();
}
