/*
 */
package dateTime;

import dateTime.Date;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author neo
 */
@RunWith(Parameterized.class)
public class InvalidDateTest {

    private int year;
    private int month;
    private int day;

    /**
     *
     */
    public InvalidDateTest(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //test dates
    @Parameters
    public static Collection<Object[]> invalidDates() {
        Object[][] data = new Object[][]{{111, 2, 20030}, {11, 234, 2003}, {11, 24, 23456},
            {12, 13, 1}, {2003, 6, 31}, /*non-month*/ {916, 1, 32}, /*invaslid leap-year*/{2013, 2, 29}/* add invalid dates here...*/};
        return Arrays.asList(data);

    }

    @Test
    public void testConstructor() {
        try {
            Date date = new Date(year, month, day);
            fail("Should throw an exception for" + date.toString());
        } catch (RuntimeException ex) {
        }
    }
}
