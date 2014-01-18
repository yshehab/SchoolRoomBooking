/**
 * GPL 2.0
 * 
 * designed for a single project.
 */
package core;

import dateTime.Date;
import dateTime.Range;
import dateTime.RepeatRate;
import dateTime.TimeSlot;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *
 * @author neo
 */
@Ignore //most methods and tests have not been implemented yet.
public class LettingCoordTest {

    private static LettingCoord letting;
    private Address address1, address2;
    private Email email;
    private Name name1;
    private Member member1;
    private Organization organization1;
    private School school1, school2;
    private Permit permit1;
    private Room room1, room2;
    private Date date1, date2, date3;
    private Range<Date> dates1, dates2;
    private Range<TimeSlot> times;
    private Account account1;
    private Collection<Asset> assets;

    public LettingCoordTest() {
    }

    @Before
    public void setUp() {
        //TODO add new 'parameters' for LettingCoord_test
        assets = new HashSet<>();
       // assets.add(Asset.);
        //assets.add(Asset.);
        letting = LettingCoord.getTestInstance();//always vanilla
        address1 = Address.createNewAddress("5", "Some street", "", "Some Town", "Some County", "EE12 5WE");
        address2 = Address.createNewAddress("3", "Another street", "", "Another town", "", "yy22 0jj");
        email = new Email("someone@somewhere.com");
        name1 = Name.createNewName(null, "", "");
        member1 = new Member(name1, address1, email);
        organization1 = new Organization("", address1, member1);
        school1 = new School("Some school", address1, room1);
        school2 = new School("Another school", address2, room1);
        permit1 = new Permit(organization1, member1);
        room1 = new Room("1-01", assets, 30, RoomType.CLASSROOM);
        room2 = new Room("Games Hall", assets, 30, RoomType.GYM);
        date1 = new Date(10, 11, 13);
        date2 = new Date(10, 11, 14);
        date3 = new Date(2010, 11, 15);
        dates1 = new Range<>(date1, date1, RepeatRate.SINGLE);
        dates2 = new Range<>(date2, date3, RepeatRate.NEXT);
        times = new Range<>(TimeSlot.EVENING, TimeSlot.EVENING, RepeatRate.SINGLE);
        account1 = new Account(new BigDecimal(10));

    }

    /**
     * Test of createPermit method, of class LettingCoord.
     *
     * This is use case 1
     */
    @Test
    public void testCreatePermit() {
        //TODO write tests
        
    }

    /**
     * Test of addBooking method, of class LettingCoord.
     *
     * This is a test for use case 3 where a booking is added to an existing permit.
     */
    @Test
    public void testAddBooking_case3() {
        //TODO add other fails to testAddBooking to a permit
        int bookingsThere = letting.getBookings(room1).size();
        letting.addBooking(permit1, room1, dates1, times);
        assertTrue("addBooking for school permit has gone bad", letting.getBookings(room1).size() == bookingsThere + 1);
    }

    /**
     * Test of addBooking method, of class LettingCoord.
     *
     * This is a test for use case 3 where a booking without a permit
     */
    @Test
    public void testAddBooking_case2() {
        //TODO add other fails to testAddBooking_case2 for staff member
        int bookingsThere = letting.getBookings(room1).size();
        letting.addBooking(member1, room1, dates1, times);
        assertTrue("addBooking for school member gone bad", letting.getBookings(room1).size() == bookingsThere + 1);
    }

    /**
     * Test of cancelBooking method, of class LettingCoord.
     *
     * This is a test of use case 4
     */
    @Test
    public void testCancelBooking() {
        //TODO add other fails to testCancelBooking
        int bookingsThere = letting.getBookings(room1).size();
        letting.addBooking(member1, room1, dates1, times);
        Booking booked = new Booking(dates1, times, room1);
        assertTrue("Booking not removed", !letting.getBookings(room1).contains(booked));
        assertTrue("Booking not removed", letting.getBookings(room1).size() == bookingsThere - 1);
    }

    /**
     * Test of getBookings method, of class LettingCoord.
     *
     * This is use case 5 getBookings(Date a Date)
     */
    @Test
    public void testGetBookings_Date() {
        //TODO create other failing tests for testGetBookings(date)
        letting.addBooking(member1, room1, dates1, times);
        assertTrue("GetBookings(date) isn't working", letting.getBookings(date1).size() == 1);
    }

    /**
     * Test of getBookings method, of class LettingCoord.
     *
     * This is use case 6 getBookings(organization)
     */
    @Test
    public void testGetBookings_Organization() {
        //TODO create other fails for testGetBooking_Organization
          //TODO review test as createpermit has changed
        letting.addOrganization(organization1, member1);
        //permit1 = letting.createPermit(organization1, school1, member1);
        permit1.addBooking(new Booking(dates1, times, room1));
        assertTrue("Wrong number of bookings", letting.getBookings(organization1).size() == 1);
    }

    /**
     * Test of getRooms method, of class LettingCoord.
     *
     * This is use case 7.
     */
    @Test
    public void testGetRooms() {
        //TODO create other fails for testGetRooms
         //TODO review test as createpermit has changed
        letting.addOrganization(organization1, member1);
      //  permit1 = letting.createPermit(organization1, school1, member1);
        permit1.addBooking(new Booking(dates1, times, room1));
        permit1.addBooking(new Booking(dates2, times, room1));
        assertTrue("Should be an empty collection", letting.getRooms(school1, RoomType.CLASSROOM, dates1, times).isEmpty());
        assertTrue("Should be an empty collection", letting.getRooms(school1, RoomType.CLASSROOM, dates2, times).isEmpty());
    }

    /**
     * Test of getBookings(Room) method, of class LettingCoord.
     *
     * This is use case 8
     */
    @Test
    public void testGetBookings_Room() {
        //TODO add more failing tests for getBookings(Room)
        letting.addBooking(member1, room1, dates1, times);
        letting.addBooking(permit1, room1, dates2, times);
        assertTrue("There should be two bookings", letting.getBookings(room1).size() == 2);
    }

    /**
     * Test of createAccount method, of class LettingCoord.
     *
     * This is use case 9
     */
    @Test
    public void testCreateAccount() {
        //TODO add more test to testCreatAccount
        letting.addBooking(permit1, room1, dates1, times);
        Collection<Booking> books = permit1.getBookings();
        assertTrue("Wrong number of bookings", books.size() == 1);
        //TODO we can't exactly do this yet
        //we can't really create Account objects yet
    }

    /**
     * Test of getCharge method, of class LettingCoord.
     */
    @Test
    public void testGetCharge() {
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getMembers method, of class LettingCoord.
     * This is use case 11 overloaded
     */
    @Test
    public void testGetMembers() {
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getPermits method, of class LettingCoord.
     */
    @Test
    public void testGetPermits() {
        
    }

    /**
     * Test of getBookings method, of class LettingCoord.
     */
    @Test
    public void testGetBookings_Permit() {
        //TODO create a test for getBookings_Permit
       
    }

    /**
     * Test of getAccounts method, of class LettingCoord.
     */
    @Test
    public void testGetAccounts() {
    }

    /**
     * Test of addOrganization(Organization, Member) method, of class LettingCoord. use case 15
     */
    @Test
    public void testAddOrganization() {
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of addOrganization(String, Address, Name, Address, Email) method, of class LettingCoord.
     * use case 15a
     */
    @Test
    public void test2AddOrganization() {
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of addMemberToOrganization method, of class LettingCoord.
     */
    @Test
    public void testAddMemberToOrganization_Member_Organization() {
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of addMemberToOrganization method, of class LettingCoord.
     */
    @Test
    public void testAddMemberToOrganization_4args() {
        
    }

    /**
     * Test of addSchool method, of class LettingCoord.
     *
     * This is use case 18
     */
    @Test
    public void testAddSchool() {
        //TODO review test add school - School constructor has changed to include Room parameter
        assertTrue("Should be empty", letting.getSchools().isEmpty());
       letting.addSchool("Some School", address1, room1); //school1
        letting.addSchool("Another school", address2, room1); //school2
        assertTrue("There should be 2 schools", letting.getSchools().size() == 2);
        assertTrue("Should contain school1", letting.getSchools().contains(school1));
        assertTrue("Should contain school2", letting.getSchools().contains(school2));
        letting.addSchool("Another School", address2, room1); //duplicate should not be added
        assertTrue("There should be 2 schools, a duplicate has been added", letting.getSchools().size() == 2);

    }

    /**
     * Test of addRoom method, of class LettingCoord.
     *
     * This is really testAddRoomToSchool use case 19
     */
    @Test
    public void testAddRoom() 
     //TODO review test add school - School constructor has changed to include Room parameter
    {
        letting.addSchool("Some School", address1, room1);
        letting.addSchool("Nother school", address2, room2);
        assertTrue("Should be 2 schools", letting.getSchools().size() == 2);
    }

    /**
     * Test of addMemberToSchool method, of class LettingCoord.
     *
     * Use case 20
     */
    @Test
    public void testAddMemberToSchool() {
        // TODO add more fails to addMemberToSchool test.
        letting.addMemberToSchool(member1, school1);
        assertTrue("Schooll should have a single member", school1.getMembers().size() == 1);
        assertTrue("school1 should contain member1", school1.getMembers().contains(member1));
    }

    /**
     * Test of getSchools method, of class LettingCoord.
     */
    @Test
    public void testGetSchools() {
        // TODO review test get schools test
         //TODO review test get school - School constructor has changed to include Room parameter
        
        assertTrue("Should be empty", letting.getSchools().isEmpty());
        letting.addSchool("Some School", address1, room1); //school1
        letting.addSchool("Another School", address2, room1); //school2
        assertTrue("There should be 2 schools", letting.getSchools().size() == 2);
        assertTrue("Should contain school1", letting.getSchools().contains(school1));
        assertTrue("Should contain school2", letting.getSchools().contains(school2));
        letting.addSchool("Another School", address2, room1); //duplicate should not be added
        assertTrue("There should be 2 schools, a duplicate has been added", letting.getSchools().size() == 2);
    }

    /**
     * Test of getOrganizations method, of class LettingCoord.
     */
    @Test
    public void testGetOrganizations() {
        // TODO review the generated test code and remove the default call to fail.
      
    }
}
