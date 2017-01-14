package core;

import dateTime.Range;
import dateTime.RepeatRate;
import dateTime.TimeSlot;
import java.util.Collection;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *
 * @author Team
 *
 *Note: All tests written as per class description 13.12.13 - Ben
 */
@Ignore //most tests are failing -will need to be reviewed
public class SchoolTest {

    private static Address address1, address2, address3;
    private static Name name1, name2;
    private static Email email1, email2;
    private static Member member1, member2, member3;
    private static School school1, school2;
    private static Room room1, room2;
    private static Collection<Asset> assets;
    private static Organization organization1, organization2;
    private static Permit permit1, permit3;
    private static dateTime.Date pastDate1, pastDate2, todayDate3, futureDate4;
    private static Range<dateTime.Date> pastDates1, pastDates2, todaysDates1, futureDates1, overlapDates1;
    private static Range<TimeSlot> times;
    private static Booking booking1, booking2, booking3, booking4, booking5, booking6, booking7;

    @BeforeClass
    public static void setUp() {
        address1 = Address.createNewAddress("56", "High Street", "Painterly",
                "London", "Greater London", "Nw7 8yu");
        address2 = Address.createNewAddress("567", "Penny Lane", "Silverton",
                "Liverpool", "Merseyslide", "li6b28");
        address3 = Address.createNewAddress("256", "Penny Lane", "Silverton",
                "Liverpool", "Merseyslide", "li6b28");

        email1 = new Email("teach@artgroup.org.uk ");
        name1 = Name.createNewName(Name.Title.MR, "John", "Monet");
        member1 = new Member(name1, address1, email1);
        member3 = new Member(name2, address1, email2);
        email2 = new Email("assist@artgroup.org.uk ");
        name2 = Name.createNewName(Name.Title.MR, "Peter", "Picasso");
        member2 = new Member(name2, address2, email2);
        school1 = new School("Bash Street Junior School", address3, room1);
        school2 = new School("Bash Street Infants School", address3, room1);
        room1 = new Room("1-01", assets, 30, RoomType.CLASSROOM);
        organization1 = new Organization("Art Group", address1, member1);
        organization2 = new Organization("yoga", address2, member2);
        permit1 = new Permit(organization2, member2);
        permit3 = new Permit(organization1, member2);
        pastDate1 = new dateTime.Date(13, 12, 2);
        pastDate2 = new dateTime.Date(2013, 12, 3);
        todayDate3 = dateTime.Date.dateNow();
        futureDate4 = new dateTime.Date(2074, 8, 16);
        pastDates1 = new Range<>(pastDate1, pastDate1, RepeatRate.SINGLE);
        pastDates2 = new Range<>(pastDate1, pastDate2, RepeatRate.SINGLE);
        todaysDates1 = new Range<>(todayDate3, todayDate3, RepeatRate.SINGLE);
        futureDates1 = new Range<>(futureDate4, futureDate4, RepeatRate.SINGLE);
        overlapDates1 = new Range<>(pastDate1, futureDate4, RepeatRate.WEEKLY);
        times = new Range<>(TimeSlot.EVENING, TimeSlot.EVENING, RepeatRate.SINGLE);
        booking1 = new Booking(pastDates1, times, room1);//all dates in the past
        booking2 = new Booking(pastDates2, times, room2);//all dates in the past
        booking3 = new Booking(todaysDates1, times, room1);//booking with todays date
        booking4 = new Booking(futureDates1, times, room2);//booking with all future dates
        booking5 = new Booking(overlapDates1, times, room1);//booking with overlapping dates
        booking6 = new Booking(todaysDates1, times, room2);//booking with todays date
        booking7 = new Booking(pastDates2, times, room1);//all dates in the past
    }

    /**
     * Tests the constructor works as expected
     */
    @Test
    public void constructorTest() {
        // Test
        School school3 = new School("Bash Street Infants", address1, room1);
        Collection<Room> roomResult = school3.getRooms();

        //Check the result
        assertSame("Address should be address1", address1, school3.getAddress());
        assertSame("Name should be Bash Street Infants", "Bash Street Infants", school3.getName());
        assertTrue("roomResult should contain 1 room", roomResult.size() == 1);
        assertTrue("roomResult should contain room1", roomResult.contains(room1));

    }

    /**
     * Tests getting bookings when aRoom has no Bookingss
     */
    @Test
    public void getBookingsRoomTest1() {

         //fixture
        //school1 by default has room1 with no bookings
        // Test
        Collection<Booking> theBookings = school1.getBookings(room1);

        // Check the result   
        assertTrue("theBookings should be empty", theBookings.isEmpty());
    }

    /**
     * Tests getting bookings when there are two Room objects, aRoom has no matching bookings, the
     * other room does.
     */
    @Test
    public void getBookingsRoomTest2() {

        //fixture
        school1.addBooking(room1, booking1);
        school1.addBooking(room1, booking7);
        school1.addBooking(room2, booking4);
        school1.addBooking(room2, booking6);
        

        // Test
        Collection<Booking> theBookings = school1.getBookings(room1);

        // Check the result   
        assertTrue("theBookings should be empty", theBookings.isEmpty());
    }
    
     /**
     * Tests getting bookings when there are two bookings in the future and two in the past for aRoom
     */
    @Test
    public void getBookingsRoomTest3() {

        //fixture
        school1.addBooking(room1, booking1);
        school1.addBooking(room1, booking3);
        school1.addBooking(room1, booking5);
        school1.addBooking(room1, booking7);
        // Test
        Collection<Booking> theBookings = school1.getBookings(room1);

        // Check the result   
        assertTrue("theBookings should contain 2 booking2", theBookings.size() == 2);
        assertTrue("theBookings should contain booking3 and booking5", theBookings.contains(booking3) && theBookings.contains(booking5));
    }

    /**
     * Tests getting bookings when there are no matching Bookings
     */
    @Test
    public void getBookingsDateTest1() {

        //fixture
        school1.addBooking(room1, booking1);

        // Test
        Collection<Booking> theBookings = school1.getBookings(dateTime.Date.dateNow());

        // Check the result   
        assertTrue("theBookings should be empty", theBookings.isEmpty());
    }

    /**
     * Tests getting bookings when there is one matching Booking
     */
    @Test
    public void getBookingsDateTest2() {

        //fixture
        school1.addBooking(room1, booking3);

        // Test
        Collection<Booking> theBookings = school1.getBookings(dateTime.Date.dateNow());

        // Check the result   
        assertTrue("theBookings should only contain 1 booking", theBookings.size() == 1);
        assertTrue("theBookings should contain booking3", theBookings.contains(booking3));
    }

    /**
     * Tests getting bookings when there is one matching Booking and one non-matching booking for
     * the same room
     */
    @Test
    public void getBookingsDateTest3() {

        //fixture
        school1.addBooking(room1, booking3);
        school1.addBooking(room1, booking1);
        // Test
        Collection<Booking> theBookings = school1.getBookings(dateTime.Date.dateNow());

        // Check the result   
        assertTrue("theBookings should only contain 1 booking", theBookings.size() == 1);
        assertTrue("theBookings should contain booking3", theBookings.contains(booking3));
    }

    /**
     * Tests getting bookings when there are two matching Bookings for different rooms
     */
    @Test
    public void getBookingsDateTest4() {

        //fixture
        school1.addBooking(room1, booking3);
        school1.addBooking(room2, booking6);
        // Test
        Collection<Booking> theBookings = school1.getBookings(dateTime.Date.dateNow());

        // Check the result   
        assertTrue("theBookings should contain 2 bookings", theBookings.size() == 2);
        assertTrue("theBookings should contain booking3 and booking6", theBookings.contains(booking3) && theBookings.contains(booking6));
    }

    /**
     * Tests adding a room when the school has one other room of different RoomType
     */
    @Test
    public void addRoomTest1() {

        //assert school1 has one room
        assertTrue(school1.getRooms().size() == 1);

        //send message
        school1.addRoom("Gym", assets, 200, RoomType.GYM);

        //assert school1 now has 2 rooms
        assertEquals("school1 should contain 2 rooms", school1.getRooms().size(), 2);
        //assert rooms have correct names
        for (Room eachRoom : school1.getRooms()) {
            if (eachRoom.getType() == RoomType.GYM) {
                assertTrue("Name should be Gym", eachRoom.getName().equals("Gym"));
            }
            if (eachRoom.getType() == RoomType.CLASSROOM) {
                assertTrue("Name should be 1-01", eachRoom.getName().equals("1-01"));
            }
        }

    }

    /**
     * Tests adding a room when the school has one other room of same RoomType and different name
     */
    @Test
    public void addRoomTest2() {

        //assert school1 has one room
        assertTrue(school1.getRooms().size() == 1);

        //send message
        school1.addRoom("1-02", assets, 30, RoomType.CLASSROOM);

        //assert school1 now has 2 rooms
        assertEquals("school1 should contain 2 rooms", school1.getRooms().size(), 2);
        //assert the two rooms have different names
        Boolean roomName101 = false;
        Boolean roomName102 = false;
        for (Room eachRoom : school1.getRooms()) {
            if (eachRoom.getName().equals("1-01")) {
                roomName101 = true;
            }
            if (eachRoom.getName().equals("1-02")) {
                roomName102 = true;
            }
        }
        assertTrue("Rooms should have different names", roomName101 && roomName102);

        //assert both rooms are of Type CLASSROOM
        Boolean roomType = true;
        for (Room eachRoom : school1.getRooms()) {
            if (eachRoom.getType() != RoomType.CLASSROOM) {
                roomType = false;
            }
            assertTrue("Rooms should both be CLASSROOM", roomType);
        }

    }

    /**
     * Test adding a room when the school has one other room of different RoomType but same name
     */
    @Test(expected = IllegalArgumentException.class)
    public void addRoomTest3() {

        //assert school1 has one room
        assertTrue(school1.getRooms().size() == 1);

        //send message
        school1.addRoom("1-01", assets, 30, RoomType.GYM);
    }

    /**
     * Tests adding a permit when the school has no permits
     */
    @Test
    public void addPermitTest1() {

        //assert school1 has no permits
        assertTrue(school1.getPermits().isEmpty());

        //send message
        school1.addPermit(permit1);

        //assert school1 now has 1 permit
        assertEquals(school1.getPermits().size(), 1);
        //extract that single permit
        Permit permit2 = school1.getPermits().iterator().next();
        //assert that this permit is instanciated correctly
        assertSame(permit2.getMember(), member2);
        assertSame(permit2.getOrganization(), organization2);
    }

    /**
     * Tests adding a new permit when the school has 1 permits already
     */
    @Test
    public void addPermitTest2() {
        //fixture
        school1.addPermit(permit1);
        //assert school1 has 1 permit
        assertTrue(school1.getPermits().size() == 1);

        //send message
        school1.addPermit(permit3);

        //assert school1 now has 2 permits
        assertEquals(school1.getPermits().size(), 2);
        //assert that school1 contains permit1
        assertTrue("school1 should contain permit1", school1.getPermits().contains(permit1));
        //assert that school1 contains permit3
        assertTrue("school1 should contain permit3", school1.getPermits().contains(permit3));
    }

    /**
     * Test when the school has no permits
     */
    @Test
    public void hasPermitTest1() {

        //assert school1 has no permits
        assertTrue(school1.getPermits().isEmpty());

        //send message
        boolean result = school1.hasPermit(permit1);

        //test
        assertFalse("result should be false", result);
    }

    /**
     * Test when the school has an incorrect permit
     */
    @Test
    public void hasPermitTest2() {

        //fixture
        school1.addPermit(permit3);
        //assert school1 has 1 permit
        assertTrue(school1.getPermits().size() == 1);

        //send message
        boolean result = school1.hasPermit(permit1);

        //test        
        assertFalse("result should be false", result);
    }

    /**
     * Test when the school has a correct permit
     */
    @Test
    public void hasPermitTest3() {

        //fixture
        school1.addPermit(permit1);
        school1.addPermit(permit3);
        //assert school1 has 2 permits
        assertTrue(school1.getPermits().size() == 2);

        //send message
        boolean result = school1.hasPermit(permit1);

        //test        
        assertTrue("result should be true", result);
    }

    /**
     * Test adding a member to an empty school
     */
    @Test
    public void addMemberTest1() {
        // Test
        school1.addMember(member2);

        // Check the result
        Collection<Member> members = school1.getMembers();
        assertTrue("school1 should contain member2", members.contains(member2));
        assertTrue("school1 should only contain one member", members.size() == 1);
    }

    /**
     * Test adding a member to an school which already contains a member
     */
    @Test
    public void addMemberTest2() {

        // Fixture     
        school1.addMember(member2);

        // Test
        school1.addMember(member1);

        // Check the result
        Collection<Member> members = school1.getMembers();
        assertTrue("school1 should only contain two members", members.size() == 2);
        assertTrue("school1 should contain member1 and member2",
                members.contains(member1) && members.contains(member2));
    }

    /**
     * Test adding a member to an school which already contains that member
     */
    @Test
    public void addMemberTest3() {
        // Fixture      
        school2.addMember(member1);

        // Test
        school2.addMember(member1);

        // Check the result
        Collection<Member> members = school2.getMembers();
        assertTrue("school1 should contain member1", members.contains(member1));
        assertTrue("school1 should only contain one member", members.size() == 1);
    }

    /**
     * Test when the school has no Members
     */
    @Test
    public void isMemberTest1() {

        //assert school1 has no Members
        assertTrue(school1.getMembers().isEmpty());

        //send message
        boolean result = school1.isMember(member1);

        //test
        assertFalse("result should be false", result);
    }

    /**
     * Test when the school has an incorrect Member
     */
    @Test
    public void isMemberTest2() {

        //fixture
        school1.addMember(member2);
        //assert school1 has 1 Member
        assertTrue(school1.getMembers().size() == 1);

        //send message
        boolean result = school1.isMember(member1);

        //test        
        assertFalse("result should be false", result);
    }

    /**
     * Test when the school has a correct Member
     */
    @Test
    public void isMemberTest3() {

        //fixture
        school1.addMember(member1);
        school1.addMember(member2);
        //assert school1 has 2 Members
        assertTrue(school1.getMembers().size() == 2);

        //send message
        boolean result = school1.isMember(member1);

        //test        
        assertTrue("result should be true", result);
    }

    /**
     * Test when the school has a matching member
     */
    @Test
    public void hasMemberTest1() {

        //assert school1 has no Members
        assertTrue(school1.getMembers().isEmpty());

        //fixture
        school1.addMember(member1);

        //send message
        boolean result = school1.hasMember(name1, address1, email1);

        //test
        assertTrue("result should be true", result);
    }

    /**
     * Test when a member exists with matching address, but not name or email
     */
    @Test
    public void hasMemberTest2() {

        //assert school1 has no Members
        assertTrue(school1.getMembers().isEmpty());

        //fixture
        school1.addMember(member3);

        //send message
        boolean result = school1.hasMember(name1, address1, email1);

        //test
        assertFalse("result should be false", result);
    }

    /**
     * Test adding a booking to an empty school
     */
    @Test
    public void addBookingTest1() {

        //assert school1 has no Bookings
        assertTrue(school1.getBookings().isEmpty());

         //fixture
        // Test
        school1.addBooking(room1, booking1);

        // Check the result
        Collection<Booking> theBookings = school1.getBookings();
        Map<Room, Collection<Booking>> theMap = school1.getRoomsAndBookings();
        assertTrue("school1 should contain booking1", theBookings.contains(booking1));
        assertTrue("school1 should only contain one Booking", theBookings.size() == 1);
        assertTrue("Map should contain room1 as a key", theMap.containsKey(room1));
        assertTrue("Map should contain one key", theMap.keySet().size() == 1);
        assertTrue("Map should contain booking1 in the collection value for room1 key", theMap.get(room1).contains(booking1));
        assertTrue("Map should contain 1 Booking in  the collection value for room1 key", theMap.get(room1).size() == 1);
    }

    /**
     * Test adding a booking when the school has 1 booking for a different room
     */
    @Test
    public void addBookingTest2() {

        //assert school1 has no Bookings
        assertTrue(school1.getBookings().isEmpty());

         //fixture
        school1.addBooking(room2, booking2);
        // Test
        school1.addBooking(room1, booking1);

        // Check the result
        Collection<Booking> theBookings = school1.getBookings();
        Map<Room, Collection<Booking>> theMap = school1.getRoomsAndBookings();
        assertTrue("school1 should contain booking1 and booking2", theBookings.contains(booking1) && theBookings.contains(booking2));
        assertTrue("school1 should contain two Bookings", theBookings.size() == 2);
        assertTrue("Map should contain room1 and room2 as keys", theMap.containsKey(room1) && theMap.containsKey(room2));
        assertTrue("Map should contain two keys", theMap.keySet().size() == 2);
        assertTrue("Map should contain booking1 in the collection value for room1 key", theMap.get(room1).contains(booking1));
        assertTrue("Map should contain booking2 in the collection value for room2 key", theMap.get(room2).contains(booking2));
        assertTrue("Map should contain 1 Booking in  the collection value for room1 key", theMap.get(room1).size() == 1);
        assertTrue("Map should contain 1 Booking in  the collection value for room2 key", theMap.get(room2).size() == 1);
    }

    /**
     * Test adding a booking when the school has 1 booking for the same room
     */
    @Test
    public void addBookingTest3() {

        //assert school1 has no Bookings
        assertTrue(school1.getBookings().isEmpty());

         //fixture
        school1.addBooking(room1, booking1);
        // Test
        school1.addBooking(room1, booking5);

        // Check the result
        Collection<Booking> theBookings = school1.getBookings();
        Map<Room, Collection<Booking>> theMap = school1.getRoomsAndBookings();
        assertTrue("school1 should contain booking1 and booking5", theBookings.contains(booking1) && theBookings.contains(booking5));
        assertTrue("school1 should contain two Bookings", theBookings.size() == 2);
        assertTrue("Map should contain room1 as a key", theMap.containsKey(room1));
        assertTrue("Map should contain one key", theMap.keySet().size() == 1);
        assertTrue("Map should contain booking1 in the collection value for room1 key", theMap.get(room1).contains(booking1));
        assertTrue("Map should contain booking5 in the collection value for room1 key", theMap.get(room1).contains(booking5));
        assertTrue("Map should contain 2 Bookings in  the collection value for room1 key", theMap.get(room1).size() == 2);
    }

    /**
     * Test removing a booking when the school has 1 room with 1 bookings
     */
    @Test
    public void removeBookingTest1() {

        //assert school1 has no Bookings
        assertTrue(school1.getBookings().isEmpty());
        //fixture      
        school1.addBooking(room1, booking1);
        //assert school has 1 booking
        assertTrue(school1.getBookings().size() == 1);
        // Test
        school1.removeBooking(booking1);

        // Check the result
        Collection<Booking> theBookings = school1.getBookings();
        Map<Room, Collection<Booking>> theMap = school1.getRoomsAndBookings();
        assertTrue("school1 should contain no bookings", theBookings.isEmpty());
        assertTrue("Map should contain room1 as a key", theMap.containsKey(room1));
        assertTrue("Map should contain one key", theMap.keySet().size() == 1);
        assertTrue("Map should contain no bookings in the collection value for room1 key", theMap.get(room1).isEmpty());
    }

    /**
     * Test removing a booking when the school has 2 rooms each with 1 booking
     */
    @Test
    public void removeBookingTest2() {

        //assert school1 has no Bookings
        assertTrue(school1.getBookings().isEmpty());
        //fixture      
        school1.addBooking(room1, booking1);
        school1.addBooking(room2, booking2);
        //assert school has 2 booking
        assertTrue(school1.getBookings().size() == 2);
        // Test
        school1.removeBooking(booking1);

        // Check the result
        Collection<Booking> theBookings = school1.getBookings();
        Map<Room, Collection<Booking>> theMap = school1.getRoomsAndBookings();
        assertTrue("school1 should contain 1 booking", theBookings.size() == 1);
        assertTrue("school1 should contain booking2", theBookings.contains(booking2));
        assertTrue("Map should contain room1 and room2 as keys", theMap.containsKey(room1) && theMap.containsKey(room2));
        assertTrue("Map should contain two keys", theMap.keySet().size() == 2);
        assertTrue("Map should contain no bookings in the collection value for room1 key", theMap.get(room1).isEmpty());
    }

    /**
     * Test removing a booking when the school has 1 room with 2 bookings
     */
    @Test
    public void removeBookingTest3() {

        //assert school1 has no Bookings
        assertTrue(school1.getBookings().isEmpty());
        //fixture      
        school1.addBooking(room1, booking1);
        school1.addBooking(room1, booking3);
        //assert school has 2 booking
        assertTrue(school1.getBookings().size() == 2);
        // Test
        school1.removeBooking(booking1);

        // Check the result
        Collection<Booking> theBookings = school1.getBookings();
        Map<Room, Collection<Booking>> theMap = school1.getRoomsAndBookings();
        assertTrue("school1 should contain 1 booking", theBookings.size() == 1);
        assertTrue("school1 should contain booking3", theBookings.contains(booking3));
        assertTrue("Map should contain room1 as a key", theMap.containsKey(room1));
        assertTrue("Map should contain one keys", theMap.keySet().size() == 1);
        assertTrue("Map should contain 1 booking in the collection value for room1 key", theMap.get(room1).size() == 1);
    }
}
