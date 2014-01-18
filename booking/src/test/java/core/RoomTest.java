package core;

import dateTime.Range;
import dateTime.RepeatRate;
import dateTime.TimeSlot;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import utilities.Address;

/**
 *
 * @author Team
 *
 *Note: All tests written as per class description 13.12.13 - Ben
 * Note: No tests for getSchool(), getType(), getName() as they are simply returning attributes.
 */
@Ignore //method Room.getBookings has not been implemented yet and 
public class RoomTest {

    private Collection<Asset> assets;
    private dateTime.Date pastDate1, pastDate2, todayDate3, futureDate4;
    private Range<dateTime.Date> pastDates1, pastDates2, todaysDates1, futureDates1, overlapDates1;
    private Range<TimeSlot> times;
    private Booking booking1, booking2, booking3, booking4, booking5;
    private Room room1, room2, room3;
    private utilities.Address address1;
    private School school1;

    @Before
    public void setUp() {
        assets = new HashSet<>();
        //assets.add(Asset.);
        //assets.add(Asset.);
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
        room2 = new Room("1-01", assets, 30, RoomType.CLASSROOM);
        room3 = new Room("Games Hall", assets, 30, RoomType.GYM);
        address1 = Address.createNewAddress("5", "Some street", "", "Some Town", "Some County", "EE12 5WE");
        school1 = new School("Bash Street", address1, room1);
        booking1 = new Booking(pastDates1, times, room1);//all dates in the past
        booking2 = new Booking(pastDates2, times, room2);//all dates in the past
        booking3 = new Booking(todaysDates1, times, room1);//booking with todays date
        booking4 = new Booking(futureDates1, times, room2);//booking with all future dates
        booking5 = new Booking(overlapDates1, times, room1);//booking with overlapping dates
    }

    /**
     * Tests the constructor works as expected
     */
    @Test
    public void constructorTest() {

        // Test
        room1 = new Room("1-01", assets, 30, RoomType.CLASSROOM);

        //Check the result
        assertSame("Name should be 1-01", "1-01", room1.getName());
        assertSame("Assets should be assets", assets, room1.getAssets());
        assertSame("Capacity should be 30", 30, room1.getCapacity());
        assertSame("RoomType should be CLASSROOM", RoomType.CLASSROOM, room1.getType());

    }

    /**
     * Test getting bookings when there are no bookings
     */
    @Test
    public void getBookingsTest1() {

        // Test
        Collection<Booking> bookingsResult = room2.getBookings();

        // Check the result
        assertTrue("Returned collection should be empty", bookingsResult.isEmpty());
    }

    /**
     * Tests getting bookings when there are two bookings in the past.
     */
    @Test
    public void getBookingsTest2() {
        
        //fixture
        
        school1.addRoom("1-01", assets, 30, RoomType.CLASSROOM);
        school1.addBooking(room2, booking1);
        school1.addBooking(room2, booking2);

        // Test
        Collection<Booking> bookingsResult = room2.getBookings();

        // Check the result
        assertTrue("Returned collection should be empty", bookingsResult.isEmpty());
    }
    
     /**
     * Tests getting bookings when there are two bookings in the future.
     */
    @Test
    public void getBookingsTest3() {
        
        //fixture
        
        school1.addRoom("1-01", assets, 30, RoomType.CLASSROOM);
        school1.addBooking(room2, booking3);
        school1.addBooking(room2, booking4);

        // Test
        Collection<Booking> bookingsResult = room2.getBookings();

        // Check the result
        assertTrue("Returned collection should contain booking3 and booking4", bookingsResult.contains(booking3) && bookingsResult.contains(booking4));
        assertTrue("Returned collection should contain 2 objects", bookingsResult.size()==2);

    }
    
     /**
     * Tests getting bookings when there are two bookings in the future and two in the past.
     */
    @Test
    public void getBookingsTest4() {
        
        //fixture
        
        school1.addRoom("1-01", assets, 30, RoomType.CLASSROOM);
        school1.addBooking(room2, booking1);
        school1.addBooking(room2, booking2);
        school1.addBooking(room2, booking3);
        school1.addBooking(room2, booking4);

        // Test
        Collection<Booking> bookingsResult = room2.getBookings();

        // Check the result
        assertTrue("Returned collection should contain booking3 and booking4", bookingsResult.contains(booking3) && bookingsResult.contains(booking4));
        assertTrue("Returned collection should contain 2 objects", bookingsResult.size()==2);

    }
    
     /**
     * Tests getting bookings when there is one booking with past and future dates.
     */
    @Test
    public void getBookingsTest5() {
        
        //fixture
        
        school1.addRoom("1-01", assets, 30, RoomType.CLASSROOM);
        school1.addBooking(room2, booking5);

        // Test
        Collection<Booking> bookingsResult = room2.getBookings();

        // Check the result
        assertTrue("Returned collection should contain booking5", bookingsResult.contains(booking5));
        assertTrue("Returned collection should contain 1 objects", bookingsResult.size()==1);

    }

}
