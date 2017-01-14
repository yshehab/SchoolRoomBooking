/**
 * 
 * GPL 2.0
 * 
 * Intended for a single project
 */
package dateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 *
 * A lightweight Date class
 * Only day, month and year are recorded. Month is 0 based e.g. January == 0.
 *
 * Will throw a RuntimeException if an invalid date is used.
 *
 * @author neo
 */
@NodeEntity
public class Date implements Repeat {
    
    @GraphId 
    private Long graphId;

    private final Calendar calendar;
    //static members
    /**
     * These are field values for arguments to the static add method. NOT actual day, month or year
     * values.
     */
    public static int DAY_OF_MONTH = GregorianCalendar.DAY_OF_MONTH;
    public static int MONTH = GregorianCalendar.MONTH;
    public static int YEAR = GregorianCalendar.YEAR;

    //static methods
    /**
     *
     * @return A Date instance representing the current date
     */
    public static Date dateNow() {
        Calendar calends = GregorianCalendar.getInstance(); // now!
        return new Date(calends.get(Date.YEAR), calends.get(Date.MONTH + 1), calends.get(Date.DAY_OF_MONTH)); //January is 0 
    }

    /**
     * No-arg constructor for persistence.
     */
    private Date(){
        calendar = null;
    }
    
    /**
     *
     * @param year The year, 0 == 2000, 13 == 2013, 113 == 2013
     * @param month The month. Note month is 0 based, January == 0, December == 11.
     * @param day The day of the month
     *
     */
    public Date(int year, int month, int day) {
        if (year < 1000) {
            year = year + 2000;
        }
        final boolean isValidDate = validateDate(year, month, day);
        boolean leapYearChecked = true;
        if(month == 2 && day == 29){//leap year
            leapYearChecked = isLeapYear(year);
        }
        
        if (isValidDate && leapYearChecked) {
            calendar = new GregorianCalendar(year, month - 1, day);
        } else {
            throw new RuntimeException("Invalid date.");
        }
    }

    /**
     *
     * @param date
     */
    public Date(Date date) {
        int year = date.calendar.get(Date.YEAR);
        int month = date.calendar.get(Date.MONTH);
        int day = date.calendar.get(Date.DAY_OF_MONTH);
        final boolean isValidDate = validateDate(year, month, day);
        if (isValidDate) {
            calendar = new GregorianCalendar(year, month, day);
        } else {
            throw new RuntimeException("Invalid date.");
        }
    }

    /**
     * @param obj A date object to compare the receiver to.
     * @return int BEFORE = -1; date before receiver EQUAL = 0; date same as receiver AFTER = 1;
     * date after receiver
     */
    public int compareTo(Object obj) {
        Date myDate = (Date) obj;
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        final int result;
        boolean DATE_BEFORE = this.calendar.before(myDate.calendar);
        boolean DATE_AFTER = this.calendar.after(myDate.calendar);
        if (!DATE_BEFORE && !DATE_AFTER) {
            result = EQUAL;
        } else {
            result = (DATE_BEFORE) ? BEFORE : AFTER;
        }
        return result;
    }

    /**
     *
     * @return the hashCode of the receiver
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.calendar); //bit odd this auto-generated - neo
        return hash;
    }

    /**
     *
     * @param obj
     * @return true is obj is a Date with the same state as the receiver, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Date other = (Date) obj;
        if (!Objects.equals(this.calendar, other.calendar)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return A string representation of the receiver
     */
    @Override
    public String toString() {
        final int day = calendar.get(Date.DAY_OF_MONTH);
        final int month = calendar.get(Date.MONTH) + 1;
        final int year = calendar.get(Date.YEAR);
        final String result = day + "/" + month + "/" + year;
        return result;
    }

    @Override
    public Date add(RepeatRate rate) {
        Calendar calend = (Calendar) this.calendar.clone();
        int field = rate.getField();
        if (field == Date.DAY_OF_MONTH) {//change day
            calend.roll(field, rate.getRate());
            final boolean MONTH_CHANGE = calend.get(Date.DAY_OF_MONTH) < this.calendar.get(Date.DAY_OF_MONTH);
            if (MONTH_CHANGE) { //need to increment month
                calend.roll(Date.MONTH, true);
            }
        } else { //change month/year
            calend.roll(field, true);
        }
        final boolean YEAR_CHANGE = calend.get(Date.MONTH) < this.calendar.get(Date.MONTH);
        if (YEAR_CHANGE) {
            calend.roll(Date.YEAR, true);
        }
        final int year = calend.get(Date.YEAR);
        final int month = calend.get(Date.MONTH) + 1;
        final int day = calend.get(Date.DAY_OF_MONTH);
        Date result = new Date(year, month, day);
        return result;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean isAfter(Object obj) {
        return this.compareTo((Date) obj) == -1;
    }

    //package methods
    //this is a stub for something that I've been working on --neo
    boolean isWeekday() {
        boolean result = true;
        final boolean isSaturday = this.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
        final boolean isSunday = this.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
        if (isSaturday || isSunday) {
            result = false;
        }
        return result;
    }

    //private methods
    //uses SimpleDateFormat class to check for a valid date
    private boolean validateDate(int year, int month, int day) {
        boolean result;
        SimpleDateFormat format = new SimpleDateFormat("y/M/d");
        format.setLenient(false);
        final String dateString = year + "/" + month + "/" + day;
        try {
            format.parse(dateString);
            result = true;
        } catch (ParseException ex) {
            result = false;
        }
        return result;
    }

    //returns true if year is a leap year
    private boolean isLeapYear(int year) {
        boolean result = false;
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            result = true;
        }
        return result;
    }

    @Override
    public String getDisplayString() {
        return this.toString();
    }

    /**
     * @return the graphId
     */
    public Long getGraphId() {
        return graphId;
    }
}
