package core;


import static org.junit.Assert.assertSame;
import org.junit.Test;
import utilities.Address;
import utilities.Email;
import utilities.Name;
import static utilities.Name.createNewName;

/**
 *
 * @author Team
 */
public class MemberTest {
    public MemberTest() {
        
    }
    
    @Test 
    public void testConstructor()  {
        Name name1 = createNewName(Name.Title.MR, "Joe", "Bloggs");
        Address address1 = Address.createNewAddress("23",
                                                    "High Street", "Painterly",
                                                    "London", "Greater London",
                                                    "Nw7 8yu");
        Email email1 = new Email("joe@scouse.com");
        Member member1 = new Member(name1, address1,email1);
        assertSame(member1.getName(), name1);
        assertSame(member1.getAddress(), address1);
        assertSame(member1.getEmail(), email1);
        
    }
}