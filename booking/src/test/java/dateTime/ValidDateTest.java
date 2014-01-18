/*
 */
package dateTime;

import dateTime.Date;
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
public class ValidDateTest {

    private int year;
    private int month;
    private int day;
    private Date date;

    /**
     *
     * @param dateString
     */
    public ValidDateTest(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //test dates
    /**
     *
     * @return
     */
    @Parameters
    public static Collection<Object[]> validDates() {
        Object[][] data = new Object[][]{{2003, 2, 11}, {3, 2, 11}, {2345, 7, 23},
            {12, 11, 10} /* add valid dates here...*/};
        return Arrays.asList(data);

    }

    /**
     *
     */
    @Test
    public void testConstructor() {
        try {
            date = new Date(year, month, day);
        } catch (RuntimeException ex) {
            fail(ex.toString());
        }
    }
}
