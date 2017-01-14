/*
 * Original comments by neo
 * if you wish to comment please add a handle
 * 
 * GPL 2.0
 * Intended for a single project
 */
package dateTime;

import java.util.Calendar;

/**
 *
 * @author neo
 *
 * Models the repeat rate of an object that implements the Repeat interface. Classes may
 * respond to some, none or all of the RepeatRate enums as they require.
 *
 * Natural ordering <b>does not</b> matter here.
 */
public enum RepeatRate {

    SINGLE(0, Date.DAY_OF_MONTH),
    NEXT(1, Calendar.DAY_OF_YEAR),
    WEEKLY(7, Date.DAY_OF_MONTH),
    // leave for now
    //WEEKDAY(1, Date.DAY_OF_MONTH),
    MONTHLY(1, Date.MONTH),
    YEARLY(1, Date.YEAR);
    private final int rate;
    private final int field;

    private RepeatRate(int rate, int field) {
        this.rate = rate;
        this.field = field;
    }

    //package methods
    int getRate() {
        return this.rate;
    }

    int getField() {
        return this.field;
    }
    
    /**
     * 
     * @return A String that represents the receiver. 
     */
    public String getDisplayString(){
        return this.toString();
    }
}
