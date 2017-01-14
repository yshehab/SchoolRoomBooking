/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateTime;

import dateTime.Session;
import dateTime.RepeatRate;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author neil
 */
public class SessionTest {

    public SessionTest() {
    }

    /**
     * Test of add method, of class Session.
     */
    @Test
    public void testAdd() {
        assertTrue("Should be Session 2", Session.SESSION_1.add(RepeatRate.NEXT) == Session.SESSION_2);
        assertTrue("Should be Session 10", Session.SESSION_9.add(RepeatRate.NEXT) == Session.SESSION_10);
        assertTrue("Should be None", Session.SESSION_13.add(RepeatRate.NEXT) == Session.NONE);
        assertTrue("Should be Session None", Session.SESSION_1.add(RepeatRate.WEEKLY) == Session.NONE);
        assertTrue("Should be session 1", Session.SESSION_1.add(RepeatRate.SINGLE) == Session.NONE);
    }

    /**
     * Test of isAfter method, of class Session.
     */
    @Test
    public void testIsAfter() {
        assertTrue("Should be true", Session.SESSION_1.isAfter(Session.SESSION_2));
        assertTrue("Should be true", Session.SESSION_4.isAfter(Session.SESSION_5));
        //equal
        assertFalse("Should be false", Session.SESSION_13.isAfter(Session.SESSION_13));
        //wrong
        assertFalse("Should be false", Session.SESSION_12.isAfter(Session.SESSION_10));
        assertFalse("Should be false", Session.SESSION_12.isAfter(Session.SESSION_1));
    }
}
