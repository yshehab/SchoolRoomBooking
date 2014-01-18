package core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *
 * @author Team School objects represent schools in the system.
 */
@NodeEntity
public class School {
    //attributes

    @GraphId
    private Long graphId;

    private String name;

    @Fetch
    private Address address;

    @Fetch
    @RelatedTo(elementClass = Permit.class)
    private Collection<Permit> permits;

    @Fetch
    @RelatedTo
    private Collection<Member> members;

    @Fetch
    @RelatedTo 
    /* I don't think this is needed, I think we need Collection<Room> and 
       Map<Room, Collection<Booking>> to be created upon request.
       ys 18jan2014 */
    private Map<Room, Collection<Booking>> bookings; //use key to record all rooms in a school, rooms with no bookings will an empty collection as value.

    @Fetch
    @RelatedTo(elementClass = Booking.class)
    private Collection<Booking> roomBookings; //empty collection for use in constructor

    //constructor
    /**
     * Initialises a new School object with the given name and Address.
     *
     * @param aName the name of the school
     * @param anAddress the address of the school
     * @param aRoom the initial room in the school
     */
    public School(String aName, Address anAddress, Room aRoom) {
        this.name = aName;
        this.address = anAddress;
        permits = new HashSet<>();
        members = new HashSet<>();
        bookings = new HashMap<>();
        roomBookings = new HashSet<>();
        bookings.put(aRoom, roomBookings); //initial room should have no bookings, so use empty collection
    }

    //public protocol
    /**
     * Returns the name of this School.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this School.
     *
     * @param aName the new name of the school
     */
    public void setName(String aName) {
        this.name = aName;
    }

    /**
     * Returns the address of this School.
     *
     * @return Address
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Returns the members of this School.
     *
     * @return members
     */
    public Collection<Member> getMembers() {
        return Collections.unmodifiableCollection(this.members);
    }

    /**
     * Returns all the Booking objects linked to the receiver Note: only needed
     * for testing purposes? - ben
     *
     * @return a collection of all Booking objects linked to the receiver
     */
    public Collection<Booking> getBookings() {
        //TODO write method
        return roomBookings;//incorrect return
    }

    /**
     * Returns the Map<Room, Collection<Booking>> bookings Note: only needed for
     * testing purposes? - ben
     *
     * @return bookings
     */
    public Map<Room, Collection<Booking>> getRoomsAndBookings() {

        return bookings;
    }

    /**
     * Returns the Bookings of this School for a specified date.
     *
     * @return a collection of all Booking objects linked to the receiver whose
     * date attribute contains a Date object matching aDate.
     * @param aDate
     */
    public Collection<Booking> getBookings(dateTime.Date aDate) {
        //TODO write method
        return roomBookings;//incorrect return
    }

    /**
     * Returns the Bookings of this School for a specified room, for the current
     * day and the future.
     *
     * @return a collection of all Booking objects linked to the receiver whose
     * date attribute is today or in the future
     * @param aRoom
     */
    public Collection<Booking> getBookings(Room aRoom) {
        //TODO write method
        return roomBookings;//incorrect return
    }

    /**
     * Sets the address of this School.
     *
     * @param anAddress the new address of the school
     */
    public void setAddress(Address anAddress) {
        this.address = anAddress;
    }

    /**
     * Returns a string representation of the school.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString() {
        return this.name
                + "\n" + this.address.getTown()
                + "\n" + this.address.getPostCode();
    }

    /**
     * adds a new Permit to this instances collection of Permits. Note
     * Invariant: aMember must be linked to anOrg
     *
     * @param aPermit
     */
    public void addPermit(Permit aPermit) {
        permits.add(aPermit);
    }

    /**
     *
     * @return defensive copy of permits
     */
    public Collection<Permit> getPermits() {
        return new HashSet<>(permits);
    }

    /**
     * @param aPermit
     * @return true if the receiver contains aPermit, false otherwise
     */
    public boolean hasPermit(Permit aPermit) {
        //TODO write method
        return false;
    }

    /**
     *
     * @return defensive copy of the linked Room objects
     */
    public Collection<Room> getRooms() {

        //TODO - write method
        return new HashSet<>();
    }

    /**
     * Adds aMember to the school.
     *
     * @param aMember the new Member to be added
     */
    public void addMember(Member aMember) {
        members.add(aMember);
    }

    /**
     * Adds a room with no Bookings to the school.
     *
     * @param aName the name of the room
     * @param assets a collection of the rooms assets
     * @param capacity
     * @param roomtype
     */
    public void addRoom(String aName, Collection<Asset> assets, int capacity, RoomType roomtype) {
        //TODO write method
        //note: should throw illegalArgumentException if aName is the same as any name of the receivers linked rooms
    }

    /**
     * Adds a Booking to the school.
     *
     * @param aRoom the Room to be added to
     * @param aBooking the Booking to be added
     */
    public void addBooking(Room aRoom, Booking aBooking) {

        bookings.get(aRoom).add(aBooking);
    }

    /**
     * @param aMember
     * @return true if the receiver contains aMember, false otherwise
     */
    public boolean isMember(Member aMember) {
        //TODO write method
        return false;
    }

    /**
     * @param aName
     * @param anAddress
     * @param anEmail
     * @return true if the receiver contains a Member with the supplied
     * parameters, false otherwise
     */
    public boolean hasMember(Name aName, Address anAddress, Email anEmail) {
        //TODO write method
        return false;
    }

    /**
     * Removes a Booking from the school.
     *
     * @param aBooking the Booking to be removed
     */
    public void removeBooking(Booking aBooking) {

        //TODO write method
    }

    /**
     * @return the graphId
     */
    public Long getGraphId() {
        return graphId;
    }

}
