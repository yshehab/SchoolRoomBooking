/*
 * GPL 2.0
 * 
 * Letting version 1.0
 * December 2013
 * 
 * May be reused as long as we don't share your blame.
 */
package utilities;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Utility class for Name
 *
 * @author team
 */
@NodeEntity
public class Name implements Comparable<Name> {

    private Title title;
    private String firstName;
    private String surname;

    /* for persistence */
    @GraphId
    private Long graphId;

    /**
     * No-arg constructor for persistence.
     */
    public Name() {
    }

    /**
     * Constructor for Name
     *
     * Initializes a new Name object with the parameters passed.
     *
     * @param title
     * @param firstName
     * @param surname
     */
    public Name(Title title, String firstName, String surname) {
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     *
     * @param title the person's title.
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     *
     * @param firstName the person's given name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param surname the person's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the id of the underlying node in the graphDb
     */
    public Long getGraphId() {
        return graphId;
    }

    /**
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString() {
        return this.title.value + this.firstName + " " + this.surname;
    }

    /**
     *
     * @param name A Name object
     * @return 1 if the supplied name is lexically after the receiver. 0 if the
     * supplied name is the same as the receiver. -1 if the supplied name is
     * lexically before the receiver.
     *
     * Not null safe.
     */
    @Override
    public int compareTo(Name name) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        final boolean SURNAME_BEFORE = name.surname.compareToIgnoreCase(surname) < 0;
        final boolean SURNAME_AFTER = name.surname.compareToIgnoreCase(surname) > 0;
        final boolean NAME_BEFORE = name.firstName.compareToIgnoreCase(firstName) < 0;
        final boolean NAME_AFTER = name.firstName.compareToIgnoreCase(firstName) > 0;
        int result;
        if (!SURNAME_BEFORE && !SURNAME_AFTER) {
            System.out.println();
            if (!NAME_BEFORE && !NAME_AFTER) {
                result = EQUAL;
            } else {
                result = (NAME_BEFORE) ? BEFORE : AFTER;
            }
        } else {
            result = (SURNAME_BEFORE) ? BEFORE : AFTER;
        }
        return result;
    }

    //Static methods
    /**
     * Factory method to retrieve a new Name object with the supplied
     * attributes.
     *
     * @param aTitle the person's title. From the inner Title enum.
     * @param aFirstName the persons first name
     * @param aSurname the persons surname
     * @return a new instance of Name
     * @deprecated The public constructor should be used instead
     */
    public static Name createNewName(Title aTitle, String aFirstName, String aSurname) {
        return new Name(aTitle, aFirstName, aSurname);
    }

    //inner enum
    /**
     * Enumerated Titles for names.
     *
     * Use Name.Title.value for display.
     */
    public enum Title {

        /**
         * Basic titles.
         */
        MR("Mr. "),
        MISS("Miss "),
        MS("Ms. "),
        MRS("Mrs. "),
        SIR("Sir "),
        DOCTOR("Dr. "),
        LORD("Lord "),
        LADY("Lady "),
        REV("Rev. "),
        FATHER("Fr. "),
        /**
         * For when we just don't know!
         */
        NONE("");
        private final String value;

        Title(String s) {
            value = s;
        }
    }
}
