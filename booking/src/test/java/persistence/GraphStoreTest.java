package persistence;

import core.Member;
import core.Organization;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *
 * @author Youssef Shehab
 */
public class GraphStoreTest {

    Name name1 = new Name(Name.Title.MR, "Joe2", "Bloggs");
    Address address1 = Address.createNewAddress("789", "Test Street", "Second line", "TestTown", "county", "A12 3B");
    Email email1 = new Email("email2@domain.com");
    Member member1 = new Member(name1, address1, email1);
    Organization org1 = new Organization("TestOrg2", address1, member1);
    

    /**
     * Test of persistMember method, of class GraphStore.
     */
    @Test
    public void persistMemberTest() {

        Member retrievedMember = GraphStore.persistMember(member1);

        assertEquals("member1 and retrievedMember should be the same.",
                member1.toString(), retrievedMember.toString());

        assertEquals("address1 and the retrieved address should be the same.",
                address1.toString(), retrievedMember.getAddress().toString());

        assertEquals("name1 and the retrieved name should be the same.",
                name1.toString(), retrievedMember.getName().toString());

        assertEquals("email1 and the retrieved email should be the same.",
                email1.toString(), retrievedMember.getEmail().toString());
    }

    /**
     * Test of retrieving a Member by id
     */
    @Test
    public void retrieveMemberTest() {
        GraphStore.persistMember(member1);
        Member retrievedMember = GraphStore.retrieveMember(member1);
        assertTrue("object retrieved should be an instance of core.Member", retrievedMember instanceof core.Member);
        assertEquals("member1 and retrievedMember should be the same.",
                member1.toString(), retrievedMember.toString());
    }

    /**
     * Test of retrieve an Address by identifier -uses index
     */
    @Test
    public void retrieveAddressByIdentifierTest() {
        Address retrievedAddress = GraphStore.retrieveAddressByIdentifier(address1.getIdentifier());

        assertEquals("address1 and the retrieved address should be the same.",
                address1.toString(), retrievedAddress.toString());

    }
       
    
    /**
     * Low level test for visibility of how objects are saved as nodes in the graph. 
     */
    @Test
    public void printAllNodesTest() {
        GraphStore.printAllNodes();
    }

}
