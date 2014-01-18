package core;

import dateTime.Date;
import dateTime.Range;
import dateTime.TimeSlot;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *
 * @author Team
 */
public class LettingCoord {
    //attributes

    /**
     * The singleton instance of LettingCoord.
     */
    private static LettingCoord instance;
    /**
     * a collection of all School objects
     */
    private Collection<School> schools;
    /**
     * a collection of all Organization objects
     */
    private Collection<Organization> organizations;

    //constructor
    /**
     * Initializes a new LettingCoord object.
     */
    private LettingCoord() {
        schools = new HashSet<>();
        organizations = new HashSet<>();
    }

    //Static methods
    /**
     * @return the single instance of LettingCoord.
     */
    public static LettingCoord getInstance() {
        if (LettingCoord.instance == null) {
            LettingCoord.instance = new LettingCoord();
        }
        return LettingCoord.instance;
    }

    static LettingCoord getTestInstance() {
        //TODO remove this from production code
        return new LettingCoord();
    }
    
    @Override
    public String toString() {
       return "School Booking system"+
               "\n version: (testing only)"+
               "\n 3rd line"; 
    }

    //public protocol
    
    /**
     * Use Case 1 -  Create a permit (and a Booking) for an organization
     *
     * Creates a permit linked to anOrg if all parameters are valid, Otherwise informs the system
     * that a permit cannot be issued
     * 
     * @param anOrg
     * @param aMember
     * @param aRoom
     * @param dateRange
     * @param timeslots
     * @param display string
     * @return a collection of dates and timeslots which are already booked for aRoom,
     * this will be empty is the permit and booking have been created
     */
    public Collection<Range> createPermit(Organization anOrg, Member aMember, Room aRoom, Range dateRange, Range timeslots, String display)
    {
        
        //TODO write method
        Collection<Range> results = new HashSet<>();
        return results;
    }
    

    /**
     * Use case 2 - Add a booking
     *
     * Invariant: aMember must be working at the school linked to aRoom Invariant: aRoom must have
     * no existing bookings that overlap with range
     *
     * @param aMember the Member the booking is for
     * @param aRoom the Room the booking is for
     * @param dates a Range of Dates that the booking is for
     * @param times a Range of TimeSlot that the booking is for
     */
    public void addBooking(Member aMember, Room aRoom, Range<Date> dates, Range<TimeSlot> times) {
        throw new UnsupportedOperationException("Method not written: addBooking(Member aMember, "
                + "Room aRoom, DateRange range, Date timeSlot)");
    }

    /**
     * Use Case 3 - Add a booking to a Permit
     *
     * Invariant: The lower bound of range must be (after) the current date. Invariant: aRoom must
     * have no existing bookings that overlap with range
     *
     * @param aPermit the Permit to add the Booking to
     * @param aRoom the Room for the Booking
     * @param dates a Range of Dates for the booking
     * @param times a Range of TimeSlots or Sessions for the booking
     */
    public void addBooking(Permit aPermit, Room aRoom, Range<Date> dates, Range times) {
        throw new UnsupportedOperationException("Method not written");
    }

    /**
     * Use Case 4 - cancel booking Invariant: The booking must be after the current date
     *
     * @param aBooking the booking to be cancelled
     */
    public void cancelBooking(Booking aBooking) {
        throw new UnsupportedOperationException("Method not written: cancelBooking(Booking aBooking)");
    }

    /**
     * Use Case 5 - view all bookings for a given date
     *
     * @param aDate
     * @return a collection of booking objects which intersect with this date
     */
    public Collection<Booking> getBookings(Date aDate) {
        throw new UnsupportedOperationException("Method not written: getBookings(Date aDate)");
    }

    /**
     * Use Case 6 - view all bookings for a given organization
     *
     * @param anOrg
     * @return a collection of all bookings for anOrg
     */
    public Collection<Booking> getBookings(Organization anOrg) {
        throw new UnsupportedOperationException("Method not written: getBookings(Organization anOrg)");
    }

    /**
     * Use Case 7 - view available rooms for a given RoomType, DateRange & time slot
     *
     * @param school
     * @param type
     * @param dates
     * @param times
     * 
     * @return
     */
    public Collection<Room> getRooms(School school, RoomType type, Range<Date> dates, Range times) {
        throw new UnsupportedOperationException("Method not written)");
    }

    /**
     * Use Case 8 - view all bookings for a given room
     *
     * @param aRoom
     * @return
     */
    public Collection<Booking> getBookings(Room aRoom) {
        throw new UnsupportedOperationException("Method not written: getBookings(Room aRoom)");
    }

    /**
     * Use Case 9 - create an account for a given permit
     *
     * @param aPermit
     */
    public void createAccount(Permit aPermit) {
        throw new UnsupportedOperationException("Method not written: createAccount(Permit aPermit)");
    }

    /**
     * Use case 10 - view charges by room type
     *
     * @param type the type of Room
     * @return
     */
    public Charge getCharge(RoomType type) {
        throw new UnsupportedOperationException("Method not written: getCharge(RoomType type)");
    }

    /**
     * Use Case 11a - view members for a given organization
     *
     * @param anOrg
     * @return
     */
    public Collection<Member> getMembers(Organization anOrg) {
        return anOrg.getMembers();
    }

    /**
     * Use Case 11b - view members for a given school
     *
     * @param aSchool
     * @return
     */
    public Collection<Member> getMembers(School aSchool) {
        return aSchool.getMembers();
    }
    
    /**
     * Use Case 11c - view members who belong to both a given school
     *  - and a given organization
     * @param aSchool a given school
     * @param anOrg a given organization
     * @return Set<Member>
     */
    public Collection<Member> getMembers(School aSchool, Organization anOrg) {
       Set<Member> orgMembers = new HashSet<>(aSchool.getMembers());
       orgMembers.retainAll(anOrg.getMembers());
       return orgMembers;
    }
    
    /**
     * Use case 11d - view all members in the system
     * 
     * Note because this method relies on Set methods to return a unique
     * set of Members - TODO - Member class needs equals method & to 
     *  - implement Comparable interface to sort the returned Set.
     * @return   Set<Member> 
     */
    public Collection<Member> getMembers() {
        Set<Member> allMembers =  new HashSet<>();
        for (School each: schools) {
            allMembers.addAll(each.getMembers());
        }
        for (Organization ea: organizations) {
            allMembers.addAll(ea.getMembers());
        }
        return allMembers;
    }

    /**
     * Use Case 12 - view permits for a given organization
     *
     * @param anOrg
     * @return
     */
    public Collection<Permit> getPermits(Organization anOrg) {
        return anOrg.getPermits();
    }

    /**
     * Use Case 13 - view bookings for a given permit
     *
     * @param aPermit
     * @return
     */
    public Collection<Booking> getBookings(Permit aPermit) {
        return aPermit.getBookings();
    }

    /**
     * Use Case 14 - get accounts for a given organization
     *
     * @param anOrg
     * @return
     */
    public Collection<Account> getAccounts(Organization anOrg) {
        return anOrg.getAccounts();
    }

    /**
     * Use case 15 - add an Organization.
     *
     * The Organization object is added to the system.
     *
     * @param organization
     * @param aMember
     */
    public void addOrganization(Organization organization, Member aMember) {
        organizations.add(organization);
    }

    /**
     * Use case 15a - add an Organization (overloaded).
     *
     * Makes no attempt to validate the arguments to construct a new Member & Organization The
     * Organization object is added to the system.
     *
     * @param orgName
     * @param orgAddress
     * @param memberName
     * @param memberAddress
     * @param memberEmail
     */
    public void addOrganization(String orgName, Address orgAddress,
            Name memberName, Address memberAddress, Email memberEmail) {

        organizations.add(new Organization(orgName, orgAddress, new Member(memberName, memberAddress, memberEmail)));
    }

    /**
     * Use case 16 - add a member to an organization
     *
     * @param aMember The member to be added
     * @param anOrganization The organization to add the member to
     */
    public void addMemberToOrganization(Member aMember, Organization anOrganization) {
        anOrganization.addMember(aMember);
    }

    /**
     * Use case 17 - add a new member to an organization
     *
     * @param aName
     * @param anAddress
     * @param email
     * @param anOrg
     */
    public void addMemberToOrganization(Name aName, Address anAddress, Email email, Organization anOrg) {
        throw new UnsupportedOperationException("Method not written: addMemberToOrganization(Name aName, Address anAddress, Email email, Organization anOrg)");
    }

    /**
     * Use case 18 - add a school to the system
     *
     * @param aName
     * @param anAddress
     * @param aRoom The initial room for the school
     */
    public void addSchool(String aName, Address anAddress, Room aRoom) {
        for (School school : schools) {
            if (school.getName().equals(aName) && school.getAddress().equals(anAddress)) {
                return;
            }
        }
        schools.add(new School(aName, anAddress, aRoom));
    }

    /**
     * Use Case 19 - add a Room to aSchool
     *
     * @param aSchool
     * @param roomName
     * @param assets
     * @param capacity
     * @param type
     */
    public void addRoom(School aSchool,
            Name roomName,
            Collection<Asset> assets,
            int capacity,
            RoomType type) {
        throw new UnsupportedOperationException("Method not written: addRoom(School aSchool, Name roomName, Collection<Asset> assets, int capacity, RoomType type)");
    }

    /**
     * Use Case 20 - add a Member to aSchool
     *
     * @param aSchool
     * @param aMember
     */
    public void addMemberToSchool(Member aMember, School aSchool) {
        if (!aSchool.getMembers().contains(aMember)) {
            aSchool.addMember(aMember);
        }

    }

    /**
     * @return a mutable copy of schools
     */
    public Collection<School> getSchools() {
        return new HashSet<>(schools);
    }

    /**
     * @return a mutable copy of organizations
     */
    public Collection<Organization> getOrganizations() {
        return new HashSet<>(organizations);
    }
}
