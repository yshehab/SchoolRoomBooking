/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateTime;

import dateTime.TimeSlot;
import dateTime.RepeatRate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neil
 */
public class TimeSlotTest {

    public TimeSlotTest() {
    }

    /**
     * Test of add method, of class TimeSlot.
     */
    @Test
    public void testAdd() {
        assertTrue("Should be period 2", TimeSlot.PERIOD_1.add(RepeatRate.NEXT) == TimeSlot.PERIOD_2);
        assertTrue("Should be None", TimeSlot.LATE.add(RepeatRate.NEXT) == TimeSlot.NONE);
        assertTrue("Should be None", TimeSlot.PERIOD_1.add(RepeatRate.WEEKLY) == TimeSlot.NONE);
        assertTrue("Should be period 2", TimeSlot.PERIOD_10.add(RepeatRate.SINGLE) == TimeSlot.NONE);
    }

    /**
     * Test of isAfter method, of class TimeSlot.
     */
    @Test
    public void testIsAfter() {
        assertTrue("Should be true", TimeSlot.PERIOD_1.isAfter(TimeSlot.PERIOD_2));
        assertFalse("Should be false", TimeSlot.PERIOD_6.isAfter(TimeSlot.PERIOD_6));
        assertFalse("Should be false", TimeSlot.PERIOD_2.isAfter(TimeSlot.PERIOD_1));
    }
}
