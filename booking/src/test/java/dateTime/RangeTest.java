/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateTime;

import dateTime.Range;
import dateTime.TimeSlot;
import dateTime.Date;
import dateTime.Session;
import dateTime.RepeatRate;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author neo
 */
public class RangeTest {

    private Range<Date> dates1, dates2, dates3, dates4;
    private Range<TimeSlot> times1, times2, times3;
    private Range<Session> sessions1, sessions2;
    private Date date1, date2, date3, date4, date5, date6;

    public RangeTest() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
        //dates
        date1 = new Date(12, 7, 13);
        date2 = new Date(12, 7, 20);
        date3 = new Date(12, 7, 19);
        date4 = new Date(1, 8, 13);
        //ranges
        //dates
        dates1 = new Range<>(date1, date2, RepeatRate.NEXT);
        dates2 = new Range<>(date1, date2, RepeatRate.WEEKLY);
        dates3 = new Range<>(date1, date3, RepeatRate.WEEKLY);
        dates4 = new Range<>(date1, date2, RepeatRate.SINGLE);
        //timeslots
        times1 = new Range<>(TimeSlot.PERIOD_1, TimeSlot.PERIOD_3, RepeatRate.NEXT);
        times2 = new Range<>(TimeSlot.PERIOD_9, TimeSlot.LATE, RepeatRate.NEXT);
        times3 = new Range<>(TimeSlot.PERIOD_2, TimeSlot.PERIOD_10, RepeatRate.NEXT);
        //sessions
        sessions1 = new Range<>(Session.SESSION_1, Session.SESSION_3, RepeatRate.NEXT);
        sessions2 = new Range<>(Session.SESSION_3, Session.SESSION_6, RepeatRate.NEXT);
    }

    /**
     * Test of constructor of class range
     */
    @Test
    public void testConstructor() {
        //right size
        assertTrue("Shoulds contain 8 elements", dates1.size() == 8);
        assertTrue("Should contain 2 elements", dates2.size() == 2);
        assertTrue("Should contain 3 elements", times1.size() == 3);
        assertTrue("Should contain 5 elements", times2.size() == 5);
        assertTrue("Should contain 3 elements", sessions1.size() == 3);
        assertTrue("Should contain 1 element", dates3.size() == 1);
        assertTrue("Should contain 1 element", dates4.size() == 1);
        assertTrue("Should contain 4 elements", sessions2.size() == 4);
        //see that we have the right things
        assertTrue("Should contain date 1", dates1.contains(date1));
        assertTrue("Should contain date 1", dates2.contains(date1));
        assertTrue("Should contain PERIOD_2", times1.contains(TimeSlot.PERIOD_2));
        assertTrue("Should contain SESSION_2", sessions1.contains(Session.SESSION_2));
        //and no wrong things in there
        assertFalse("Shoud not contain date 12/7/14", dates2.contains(new Date(12, 7, 14)));
        assertFalse("Should not contain PERIOD_5", times1.contains(TimeSlot.PERIOD_5));
        assertFalse("Should not contain SESSION_5", sessions1.contains(Session.SESSION_5));
    }

    /**
     * Test of intersect method, of class Range.
     */
    @Test
    public void testIntersect() {
        Range intersect1 = dates1.intersect(dates2);
        Range intersect2 = times1.intersect(times3);
        Range intersect3 = sessions1.intersect(sessions2);
        //right size
        assertTrue("should be 2", intersect1.size() == 2);
        assertTrue("should be 2", intersect2.size() == 2);
        assertTrue("should be 1", intersect3.size() == 1);
        //contains right thing
        assertTrue("Should contain", intersect1.contains(date1));
        assertTrue("Should contain", intersect1.contains(date2));
        //does not contain
        assertFalse("should not contain", intersect1.contains(date4));
        assertFalse("should not contain", intersect3.contains(Session.SESSION_5));
    }
    
    /**
     * Test toSting() method of class Range
     */
    @Test
    public void testToString(){
        assertTrue("Should be 13/7/2012 to 20/7/2012", dates1.toString().equals("13/7/2012 to 20/7/2012"));
        assertTrue("Should be 13/7/2012 to 13/7/2012", dates3.toString().equals("13/7/2012"));
        assertTrue("Should be Session 1 to Session 3", sessions1.toString().equals("Session 1 to Session 3"));
        assertTrue("Should be Period 1 to Period 3", times1.toString().equals("Period 1 to Period 3"));
    }
}
