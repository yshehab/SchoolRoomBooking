package core;

import java.math.BigDecimal;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 * @author Team
 */
public class PermitTest {

    private Address address1;
    private Address address2;
    private Member member1;
    private Member member2;
    private School school1;
    private Permit permit1;
    private Permit permit2;
    private Organization organization1;
    private Organization organization2;
    private Room room1;
    private Collection<Asset> assets;

    @Before
    public void setUp() {
        address1 = Address.createNewAddress("23", "High Street", "Painterly",
                "London", "Greater London", "Nw7 8yu");
        address2 = Address.createNewAddress("25", "Penny Lane", "Silverton",
                "Liverpool", "Merseyslide", "li6b28");
        school1 = new School("school name", address1, room1);
        Email email1 = new Email("teacher@artgroup.org.uk ");
        Email email2 = new Email("teacher2@artgroup.org.uk ");
        Name name1 = Name.createNewName(Name.Title.LORD, "Claude", "Monet");
        Name name2 = Name.createNewName(Name.Title.MISS, "C", "Monet");
        member1 = new Member(name1, address1, email1);
        member2 = new Member(name2, address1, email2);
        permit1 = new Permit(organization1, member1);
        organization1 = new Organization("Art Group", address2, member2);
        organization1.addMember(member1);
        organization2 = new Organization("Kung fu", address1, member2);
        room1 = new Room("1-01", assets, 30, RoomType.CLASSROOM);
    }

    /*
     * Tests that Permits are created with unique permit numbers.
     */
    @Test
    public void test1Constructor() {
        // Fixture

        Email email3 = new Email("grasshopper@wuTanClan.org.uk ");
        Name name3 = Name.createNewName(Name.Title.MISS, "Bruce", "Lee");
        Member member3 = new Member(name3, address2, email3);
        organization2.addMember(member3);


        // Test
        permit2 = new Permit(organization2, member3);

        // Check the result
        assertFalse("Permit numbers should be unique.",
                permit1.getUniqueNumber().equals(permit2.getUniqueNumber()));
    }

    /**
     * Tests getting bookings when there are no bookings
     */
    @Test
    public void test1getBookings() {
        // Fixture
        organization1 = new Organization("Art Group", address2, member2);
        organization1.addMember(member1);
        permit1 = new Permit(organization1, member1);

        //send the test message
        Collection<Booking> result = permit1.getBookings();

        //check the result
        assertTrue("result should be an empty collection", result.isEmpty());
    }

    /**
     * Tests getting bookings when there is a single booking
     */
    @Test
    public void test2getBookings() {
        // Fixture
        organization1 = new Organization("Art Group", address2, member2);
        organization1.addMember(member1);
        Booking booking3 = new Booking();
        permit1.addBooking(booking3);

        // Test
        Collection<Booking> result = permit1.getBookings();

        // Check the result
        assertTrue("Permit1 should only be linked to booking3", result.contains(booking3)
                && result.size() == 1);
    }

    /**
     * Tests adding multiple bookings
     */
    @Test
    public void test1AddBookings() {
        // Fixture
        organization1.addMember(member1);
        Booking booking3 = new Booking();
        Booking booking4 = new Booking();

        // Test
        permit1.addBooking(booking3);
        permit1.addBooking(booking4);

        // Check the result
        Collection<Booking> result = permit1.getBookings();
        assertTrue("Result should contain only booking3 & booking4", result.contains(booking3)
                && result.contains(booking4) && result.size() == 2);
    }

    /**
     * Tests that no booking can be added twice.
     */
    @Test
    public void test2AddBooking() {
        // Fixture
        organization1.addMember(member1);
        Booking booking3 = new Booking();

        // Test
        permit1.addBooking(booking3);
        permit1.addBooking(booking3);

        // Check the result
        Collection<Booking> result = permit1.getBookings();
        assertEquals("Result should not contain duplicates.", result.size(), 1);
    }

    /*
     * Test getting the account when there is no account linked to the Permit.
     */
    @Test
    public void getAccountTest1() {
        // Fixture
      
        organization1.addMember(member1);

        // Test
        Account account1 = permit1.getAccount();

        // Check the result
        assertEquals("Null should have been returned.", account1, null);
    }

    /*
     * Test getting the account when there is one account linked to the Permit.
     */
    @Test
    public void getAccountTest2() {
        // Fixture
        organization1 = new Organization("Art Group", address2, member2);
        organization1.addMember(member1);
        Account account1 = new Account(BigDecimal.ZERO);
        permit1.setAccount(account1);

        // Test
        Account result = permit1.getAccount();

        // Check the result
        assertEquals("The correct account should have been returned.", account1, result);
    }
}
