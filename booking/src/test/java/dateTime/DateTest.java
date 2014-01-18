/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateTime;

import dateTime.Date;
import dateTime.RepeatRate;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author neo
 */
public class DateTest {

    private Date date1, date2, date3, date4, date5, date6; //dates for compareTo
    private Date date7, date8, date9, date10, date11; //dates for add
    private Date date12, date13, date14, date15, date16;

    public DateTest() {
    }

    @Before
    public void setUp() {
        date1 = new Date(13, 12, 7);
        date2 = new Date(2013, 12, 7);
        date3 = new Date(12, 7, 13);
        date4 = new Date(2012, 7, 13);
        date5 = new Date(13, 7, 13);
        date6 = new Date(11, 7, 13);
        date7 = new Date(1, 2, 1);
        date8 = new Date(1, 2, 2);
        date9 = new Date(1, 2, 8);
        date10 = new Date(1, 3, 1);
        date11 = new Date(2, 2, 1);
        date12 = new Date(5, 12, 30);
        date13 = new Date(5, 12, 31);
        date14 = new Date(6, 1, 1);
        date15 = new Date(6, 1, 6);
        date16 = new Date(6, 1, 7);
    }

    /**
     * Test of compareTo method of class Date.
     */
    @Test
    public void testCompareTo() {
        //TODO add more compareTo tests to DateTest
        //equals test
        assertTrue("Problem with 7/12/13", date1.compareTo(date2) == 0);
        assertTrue("Problem with 12/7/13", date3.compareTo(date4) == 0);

        //reflex equals
        assertTrue("Problem with 12/7/13 & 12/7/2013", date2.compareTo(date1) == 0);
        assertTrue("Problem with 12/7/13 & 12/7/2013", date4.compareTo(date3) == 0);

        //before test
        assertTrue("Should be before.", date3.compareTo(date1) == -1);
        assertTrue("Should be before.", date3.compareTo(date2) == -1);
        assertTrue("Should be before.", date6.compareTo(date4) == -1);
        assertTrue("Should be before.", date6.compareTo(date5) == -1);
        assertTrue("Should be before.", date5.compareTo(date1) == -1);

        //after test reflex before
        assertTrue("Should be after.", date1.compareTo(date3) == 1);
        assertTrue("Should be after.", date2.compareTo(date3) == 1);
        assertTrue("Should be after.", date4.compareTo(date6) == 1);
        assertTrue("Should be after.", date1.compareTo(date5) == 1);

        //trans
        boolean before1, before2, before3, before4;
        before1 = date6.compareTo(date4) == -1; //before
        before2 = date4.compareTo(date5) == -1;
        before3 = date5.compareTo(date1) == -1;
        before4 = date6.compareTo(date1) == -1;
        assertTrue("Should be true", (before1 && before2 && before3 && before4));
    }

    @Test
    public void testAdd() {
        assertTrue("Should be true", date7.add(RepeatRate.SINGLE).equals(date7));
        assertTrue("Should be true", date7.add(RepeatRate.NEXT).equals(date8));
        assertTrue("Should be true", date7.add(RepeatRate.WEEKLY).equals(date9));
        assertTrue("Should be true", date7.add(RepeatRate.MONTHLY).equals(date10));
        assertTrue("Should be true", date7.add(RepeatRate.YEARLY).equals(date11));
        assertTrue("Should be true", date12.add(RepeatRate.NEXT).equals(date13));
        assertTrue("Should be true", date13.add(RepeatRate.NEXT).equals(date14));
        assertTrue("Should be true", date12.add(RepeatRate.WEEKLY).equals(date15));
        assertTrue("Should be true", date13.add(RepeatRate.WEEKLY).equals(date16));
    }

    @Test
    public void testEquals() {;
        assertTrue("Should be true", date1.equals(date1));
        assertTrue("Should be true", date1.equals(date2));
        //reflex
        assertTrue("Should be true", date3.equals(date4));
        assertTrue("Should be true", date4.equals(date3));
    }

    @Test
    public void testAfter() {
        assertFalse("Should be false", date1.isAfter(date3));
        assertFalse("Should be false", date5.isAfter(date3));
        assertFalse("Should be false", date2.isAfter(date6));
        assertFalse("Should be false", date11.isAfter(date10));
        //equal
        assertFalse("Should be false", date10.isAfter(date10));
        //reflex
        assertTrue("Should be true", date3.isAfter(date1));
        assertTrue("Should be true", date3.isAfter(date5));
        assertTrue("Should be true", date6.isAfter(date2));
        assertTrue("Should be true", date10.isAfter(date11));
    }
}
