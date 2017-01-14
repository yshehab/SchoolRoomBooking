/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateTime;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author neo
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    DateTest.class,
    ValidDateTest.class,
    InvalidDateTest.class,
    RangeTest.class,
    SessionTest.class,
    TimeSlotTest.class
    
})
public class DateTimeTests {
}
