/*
 * Original comments by neo
 * if you wish to comment please add a handle
 * 
 * GPL 2.0
 * Intended for a single project
 */
package core;

import dateTime.Date;
import dateTime.Range;
import dateTime.TimeSlot;
import java.math.BigDecimal;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Models a booking for a room on a given set of dates and a given number of
 * slots.
 *
 * @author neo
 */
@NodeEntity
public class Booking {

    @GraphId
    private Long graphId;

    @Fetch
    @RelatedTo(elementClass = Date.class)
    private Range<Date> dates;


    @Fetch
    @RelatedTo(elementClass = TimeSlot.class)
    private Range<TimeSlot> times;

    @Fetch
    private Room room;

    private BigDecimal cost;

    //TODO: make private after it's no longer used in test
    /**
     * No-arg constructor for persistence.
     */
    Booking() {
    }
    
    //public protocol
    /**
     *
     * @param dates a Range object with the Dates
     * @param times a Range object with the <i>Slots<i>
     * @param room a Room object
     */
    public Booking(Range<Date> dates, Range<TimeSlot> times, Room room) {
        this.dates = dates;
        this.times = times;
        this.room = room;
        this.cost = new BigDecimal(0);
    }

    /**
     *
     * @return room the Room associated with the receiver.
     */
    public Room getRoom() {
        return room;
    }

    public Long getGraphId() {
        return graphId;
    }

    /**
     *
     * @return A string representation of the receiver.
     */
    @Override
    public String toString() {
        return "Booking" + "dates: " + dates.toString()
                + ",\ntimes: " + times.toString() 
                + ",\n room: " + room.toString();
    }

    /**
     *
     * @return the total cost of the booking.
     */
    public BigDecimal getCost() {
        return cost;
    }

    //package protocol

    boolean blocks(Range Dates, Range times) {
        return false;
        //TODO create the logic for blocking method of Booking
    }

    Range<Date> getDateBlocks(Range<Date> booking) {
        return this.dates.intersect(booking);
    }


    Range<TimeSlot> getTimeBlocks(Range<TimeSlot> booking) {
        return this.times.intersect(booking);
    }

    void setCost(BigDecimal costPerSlot) {
        //TODO logic for creating setCost method of Booking
    }
}
