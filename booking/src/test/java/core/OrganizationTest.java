package core;

import dateTime.Range;
import dateTime.RepeatRate;
import dateTime.TimeSlot;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 * @author Team
 * 
 * Note: All tests written as per class description 13.12.13 - Ben
 */
public class OrganizationTest {

    private Address address1;
    private Address address2;
    private Member member1;
    private Member member2;
    private Member member3;
    private School school1;
    private Permit permit1;
    private Permit permit2;
    private Account account1;
    private dateTime.Date date1, date2, date3;
    private Range<dateTime.Date> dates1, dates2;
    private Range<TimeSlot> times;
    private Booking booking1, booking2, booking3, booking4;
    private Room room1, room2;
    private Collection<Asset> assets;

    @Before
    public void setUp() {
        assets = new HashSet<>();
        //assets.add(Asset.);
        //assets.add(Asset.);
        address1 = Address.createNewAddress("23", "High Street", "Painterly",
                "London", "Greater London", "Nw7 8yu");
        address2 = Address.createNewAddress("25", "Penny Lane", "Silverton",
                "Liverpool", "Merseyslide", "li6b28");
        school1 = new School("", address1, room1);
        Email email1 = new Email("teacher@artgroup.org.uk ");
        Name name1 = Name.createNewName(Name.Title.MS, "Claude", "Monet");
        member1 = new Member(name1, address1, email1);

        Email email2 = new Email("assistant@artgroup.org.uk ");
        Name name2 = Name.createNewName(Name.Title.MS, "Pablo", "Picasso");
        member2 = new Member(name2, address2, email2);
        Email email3 = new Email("assistant2@artgroup.org.uk ");
        Name name3 = Name.createNewName(Name.Title.MS, "P", "Picasso");
        member3 = new Member(name3, address2, email3);
        date1 = new dateTime.Date(10, 11, 13);
        date2 = new dateTime.Date(10, 11, 14);
        date3 = new dateTime.Date(2010, 11, 15);
        dates1 = new Range<>(date1, date1, RepeatRate.SINGLE);
        dates2 = new Range<>(date2, date3, RepeatRate.NEXT);
        times = new Range<>(TimeSlot.EVENING, TimeSlot.EVENING, RepeatRate.SINGLE);
        room1 = new Room("1-01", assets, 30, RoomType.CLASSROOM);
        room2 = new Room("Games Hall", assets, 30, RoomType.GYM);
        booking1 = new Booking(dates1, times, room1);
        booking2 = new Booking(dates1, times, room2);
        booking3 = new Booking(dates2, times, room1);
        booking4 = new Booking(dates2, times, room2);
    }

    /**
     * Tests the constructor works as expected
     */
    @Test
    public void constructorTest() {
        // Test
        Organization organization1 = new Organization("Pottery", address1, member3);

        //Check the result
        assertSame("Address should be address1", address1, organization1.getAddress());
        assertSame("Name should be Pottery", "Pottery", organization1.getName());
        Collection<Member> members = organization1.getMembers();
        assertTrue("organization1 should contain member1", members.contains(member3));
        assertTrue("organization1 should only contain one member", members.size() == 1);

    }

    /**
     * Test getting permits when permits contains 0 members
     */
    @Test
    public void getPermitsTest1() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        
        // Test
        Collection<Permit> permitsResult = organization1.getPermits();

        // Check the result
        assertTrue("Returned collection should be empty", permitsResult.isEmpty());
    }

    /**
     * Test getting permits when permits contains 2 permits
     */
    @Ignore /* The link between permit and organisation is not being created - 
               Organization.addPermit() creates new permits so pemit1 and permit2 are not contained in permits
               ys 18jan2014 */
    @Test
    public void getPermitsTest2() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);
        organization1.addMember(member2);
        permit1 = new Permit(organization1, member1);
        permit2 = new Permit(organization1, member2);

        // Test
        Collection<Permit> permitsResult = organization1.getPermits();

        // Check the result
        assertTrue("Returned collection should contain exactly 2 objects", permitsResult.size() == 2);
        assertTrue("Returned collection should contain permit1 and permit2", (permitsResult.contains(permit1) && (permitsResult.contains(permit2))));
    }

    /**
     * Test that the returned collection is immutable.
     */
    @Ignore //the link between permit and organisation is not being created
    @Test
    public void getPermitsTest3() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);
        organization1.addMember(member2);
        permit1 = new Permit(organization1, member1);
        permit2 = new Permit(organization1, member2);
        Collection<Permit> permitsResult = organization1.getPermits();

        // Test
        try {
            permitsResult.remove(permit1);
            assertTrue("Returned collection should be immutable.", false);
        } catch (UnsupportedOperationException ex) {
        }

        // Check the result
        assertTrue("Returned collection should be immutable.", permitsResult.size() == 2);
    }

    /**
     * Test adding a member to a newly initialised organization
     */
    @Test
    public void addMemberTest1() {
        // Fixture
        Organization organization1 = new Organization("Art Group2", address2, member3);
        // Test
        organization1.addMember(member1);
        

        // Check the result
        Collection<Member> members = organization1.getMembers();
        assertTrue("organization1 should contain member1", members.contains(member1));
        assertTrue("organization1 should only contain two members", members.size() == 2);
    }

    /**
     * Test adding a member to an organization which already contains two members
     */
    @Test
    public void addMemberTest2() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member2);

        // Test
        organization1.addMember(member1);

        // Check the result
        Collection<Member> members = organization1.getMembers();
        assertTrue("organization1 should only contain two members", members.size() == 3);
        assertTrue("organization1 should contain member1 and member2 and member 3",
                members.contains(member1) && members.contains(member2) && members.contains(member3));
    }

    /**
     * Test adding a member to an organization which already contains that member
     */
    @Test
    public void addMemberTest3() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member1);

        // Test
        organization1.addMember(member1);

        // Check the result
        Collection<Member> members = organization1.getMembers();
        assertTrue("organization1 should contain member1", members.contains(member1));
        assertTrue("organization1 should only contain one member", members.size() == 1);
    }

    /**
     * Test a reference to a new permit is recorded correctly when no Permits are referenced.
     */
    @Test
    public void addPermitTest1() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address1, member3);
        organization1.addMember(member1);

        // Test
        permit1 = organization1.addPermit(member1);

        // Check the result
        Collection<Permit> orgPermits = organization1.getPermits();
        assertTrue("org1 should have a reference to 1 permit", orgPermits.size() == 1);
        assertTrue("org1 should hold a reference to permit1.", orgPermits.contains(permit1));
    }

    /**
     * Test a reference to a new Permit is recorded correctly when a Permit is already referenced.
     */
    @Test
    public void addPermitTest2() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address1, member3);
        organization1.addMember(member1);
        organization1.addMember(member2);

        // Test
        permit1 = organization1.addPermit(member1);
        permit2 = organization1.addPermit(member2);

        // Check the result
        Collection<Permit> orgPermits = organization1.getPermits();
        assertTrue("org1 should have a reference to 2 permits", orgPermits.size() == 2);
        assertTrue("org1 should hold a reference to permit1 and permit2.", orgPermits.contains(permit1) && orgPermits.contains(permit2));
    }

    /**
     * Test a reference to a new permit is returned correctly when a Permit is already referenced.
     */
    @Test
    public void addPermitTest3() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address1, member3);
        organization1.addMember(member1);

        // Test
        permit1 = organization1.addPermit(member1);

        // Check the result
        Collection<Permit> orgPermits = organization1.getPermits();
        assertTrue("The returned permit should have a reference to org1", permit1.getMember() == member1);
        assertTrue("The returned permit should have a reference to member1", permit1.getOrganization() == organization1);
    }

    /**
     * Test getting Members when members contains 2 members
     */
    @Test
    public void getMembersTest1() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);

        // Test
        Collection<Member> result = organization1.getMembers();

        // Check the result
        assertTrue("Collection returned should contain member1 and member3", result.contains(member1) && result.contains(member3));
        assertTrue("Collection returned should contain 2 members", result.size() == 2);
    }

    /**
     * Test that the returned collection is immutable.
     */
    @Test
    public void getMembersTest2() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);
        organization1.addMember(member2);
        Collection<Member> members = organization1.getMembers();

        // Test
        try {
            members.remove(member1);
            assertTrue("Returned collection should be immutable.", false);
        } catch (UnsupportedOperationException ex) {
        }

        // Check the result
        assertTrue("Returned collection should be immutable.", members.size() == 3);
    }

    /**
     * Test getting the accounts when the organization has no permits.
     */
    @Test
    public void getAccountsTest1() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);

        // Test
        Collection<Account> accounts = organization1.getAccounts();

        // Check the result
        assertTrue("The result should be an empty collection.", accounts.isEmpty());
    }

    /**
     * Test getting the accounts when the organization has a permit but that permit has no account.
     */
    @Test
    public void getAccountsTest2() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);
        permit1 = new Permit(organization1, member1);

        // Test
        Collection<Account> accounts = organization1.getAccounts();

        // Check the result
        assertTrue("The result should be an empty collection.", accounts.isEmpty());
    }

    /**
     * Test getting the accounts when the organization has a permit and that permit has an account.
     */
    @Ignore //the link between permit and organisation is not being created
    @Test
    public void getAccountsTest3() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);
        permit1 = new Permit(organization1, member1);
        account1 = new Account(BigDecimal.ZERO);
        permit1.setAccount(account1);

        // Test
        Collection<Account> accounts = organization1.getAccounts();

        // Check the result
        assertTrue("The result should contain a single account.", accounts.size() == 1);
    }

    /**
     * Test getting the accounts when the organization has multiple permits with and without
     * accounts.
     */
    @Ignore //the link between permit and organisation is not being created
    @Test
    public void getAccountsTest4() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        organization1.addMember(member1);
        organization1.addMember(member2);
        permit1 = new Permit(organization1, member1);
        permit2 = new Permit(organization1, member1);
        account1 = new Account(BigDecimal.ZERO);
        permit1.setAccount(account1);

        // Test
        Collection<Account> accounts = organization1.getAccounts();

        // Check the result
        assertTrue("The result should contain a single account.", accounts.size() == 1);
    }

    /**
     * Test getting bookings when permits contains 0 members
     */
    @Test
    public void getBookingsTest1() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);

        // Test
        Collection<Booking> bookingsResult = organization1.getBookings();

        // Check the result
        assertTrue("Returned collection should be empty", bookingsResult.isEmpty());
    }
    
     /**
     * Tests the correct collection of bookings is returned when the organization contains 1 permits
     */
    @Ignore //method Organization.getBookings() has not been implemented yet.
    @Test
    public void getBookingsTest2() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        permit1 = organization1.addPermit(member1);
        permit1.addBooking(booking1);
        permit1.addBooking(booking2);
        
        // Test
        Collection<Booking> bookingsResult = organization1.getBookings();

        // Check the result
        assertTrue("Returned collection should contain 2 objects", bookingsResult.size() == 2);
        assertTrue("Returned collection should contain booking 1 & booking 2", bookingsResult.contains(booking1) && bookingsResult.contains(booking2));
    }
    
     /**
     * Tests the correct collection of bookings is returned when the organization contains 2 permits
     */
    @Ignore //method Organization.getBookings() has not been implemented yet.
    @Test
    public void getBookingsTest3() {
        // Fixture
        Organization organization1 = new Organization("Art Group", address2, member3);
        permit1 = organization1.addPermit(member1);
        permit2 = organization1.addPermit(member2);
        permit1.addBooking(booking1);
        permit1.addBooking(booking2);
        permit2.addBooking(booking3);
        permit2.addBooking(booking4);
        
        // Test
        Collection<Booking> bookingsResult = organization1.getBookings();

        // Check the result
        assertTrue("Returned collection should contain 4 objects", bookingsResult.size() == 4);
        assertTrue("Returned collection should contain booking1, booking2, booking3 and booking4", bookingsResult.contains(booking1) && bookingsResult.contains(booking2) && bookingsResult.contains(booking3) && bookingsResult.contains(booking4));
    }
}
