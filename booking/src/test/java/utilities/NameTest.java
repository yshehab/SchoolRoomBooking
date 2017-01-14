/*
 * GPL 2.0
 */
package utilities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author team
 */
public class NameTest {

    private Name name1, name2, name3, name4, name5;

    /**
     * Test of Class Name
     */
    public NameTest() {
    }

    /**
     * Set up before each test
     */
    @Before
    public void setUp() {
        name1 = Name.createNewName(Name.Title.MR, "Joe", "Bloggs");
        name2 = Name.createNewName(Name.Title.MR, "Alan", "Bloggs");
        name3 = Name.createNewName(Name.Title.MR, "Wallace", "Bloggs");
        name4 = Name.createNewName(Name.Title.MR, "Jo", "Ash");
        name5 = Name.createNewName(Name.Title.LORD, "Michael", "Cavendish");
    }

    /**
     * Test of toString() method of Class Name.
     */
    @Test
    public void testToString() {
        assertEquals(name1.toString(), "Mr. Joe Bloggs");
        assertEquals(name2.toString(), "Mr. Alan Bloggs");
        assertEquals(name5.toString(), "Lord Michael Cavendish");
    }

    /**
     * Test of compareTo(Name) of class Name.
     */
    @Test
    public void testCompareTo() {
        assertTrue("Should be 1", name1.compareTo(name5) == 1);
        assertTrue("Should be 1", name2.compareTo(name1) == 1);
        assertTrue("Should be -1", name2.compareTo(name3) == 1);
        assertTrue("Should be 1", name4.compareTo(name1) == 1);
        assertTrue("Should be 1", name3.compareTo(name5) == 1);
        assertTrue("Should be 0", name2.compareTo(name2) == 0);
        assertTrue("Should be true", name2.equals(name2));//sneaky equals test
        assertTrue("Should be -1", name5.compareTo(name1) == -1);
        assertTrue("Should be -1", name1.compareTo(name2) == -1);
        assertTrue("Should be -1", name3.compareTo(name2) == -1);
        assertTrue("Should be -1", name1.compareTo(name4) == -1);
        assertTrue("Should be -1", name5.compareTo(name3) == -1);
    }
}
