/*
 * GPL 2.0
 * Intended for a single project
 */
package dateTime;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 *
 * @author neo
 *
 * Models a school day for most use cases, some areas/activities will have different requirements
 * and Should use the Session class.
 *
 * Some TimeSlots may not be needed for a given school.
 *
 */
@NodeEntity
public enum TimeSlot implements Repeat {

    BEFORE_SCHOOL("Before school"),
    PERIOD_1("Period 1"),
    PERIOD_2("Period 2"),
    PERIOD_3("Period 3"),
    PERIOD_4("Period 4"),
    PERIOD_5("Period 5"),
    PERIOD_6("Period 6"),
    PERIOD_7("Period 7"),
    PERIOD_8("Period 8"),
    PERIOD_9("Period 9"),
    PERIOD_10("Period 10"),
    AFTER_SCHOOL("After school"),
    EVENING("Evening"),
    LATE("Late evening"),
    NONE("None");
    private String display;
    @GraphId private Long graphId;
    
    private TimeSlot(){
        
    }

    private TimeSlot(String display) {
        this.display = display;
    }

    /**
     *
     * @return A string that represents the receiver.
     */
    @Override
    public String getDisplayString() {
        return display;
    }

    /**
     *
     * @param rate A RepeatRate Enum.
     * @return The TimeSlot after the receiver or, if there is no TimeSlot after or RepeatRate.rate == 0, 
     * TimeSlot.NONE  
     */
    @Override
    public TimeSlot add(RepeatRate rate) {
        final int repeat = rate.getRate();
        final boolean LAST = this.ordinal() == (TimeSlot.NONE.ordinal() - 1);
        final TimeSlot result;
        if (repeat != 1 || LAST) {
            result = TimeSlot.NONE;
        } else {
            int next = this.ordinal() + repeat;
            result = TimeSlot.values()[next];
        }
        return result;
    }

    /**
     *
     * @param obj a TimeSlot
     * @return Returns true if the argument is naturally ordered after the receiver.
     */
    @Override
    public boolean isAfter(Object obj) {
        TimeSlot slot = (TimeSlot) obj;
        return (slot.ordinal() > this.ordinal());
    }
    
    public Long getGraphId(){
        return graphId;
    }
}
