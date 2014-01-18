package persistence;

import core.Booking;
import core.Member;
import core.Organization;
import dateTime.Range;
import dateTime.TimeSlot;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;
import persistence.dbcore.DbService;
import persistence.repositories.AddressRepository;
import persistence.repositories.BookingRepository;
import persistence.repositories.MemberRepository;
import persistence.repositories.OrganizationRepository;
import persistence.repositories.PermitRepository;
import utilities.Address;

/**
 * A coordinating class to provide the services of the persistence layer. All
 * services required from the persistence layer should be requested from this
 * class. 
 *
 * @author Youssef Shehab
 */
@Transactional
public class GraphStore extends DbService {

    private static final Neo4jTemplate neo4jMediator = DbService.getNeo4jTemplate();
    private static final MemberRepository memberRepository = DbService.getRepository(MemberRepository.class);
    private static final AddressRepository addressRepository = DbService.getRepository(AddressRepository.class);
    private static final OrganizationRepository organizationRepo = DbService.getRepository(OrganizationRepository.class);
    private static final PermitRepository permitRepo = DbService.getRepository(PermitRepository.class);
    private static final BookingRepository bookingRepo = DbService.getRepository(BookingRepository.class);
    

    /**
     * Persists aMember into the graphDb. 
     * 
     * OGM action: create nodes for name, address and email of aMember 
     * and adds called, reached_at and has_email relationships from aMember to 
     * these nodes respectively. 
     *
     * @param aMember A Member object to save.
     */
    public static Member persistMember(Member aMember) {
        return memberRepository.save(aMember);
    }
    
    public static Organization persistOrganization(Organization org){
        return organizationRepo.save(org);
    }
    
    public static Organization updateOrganization(Organization org){
        return organizationRepo.save(org);
    }
    
    public static Booking perisitBooking(Booking aBooking){
        return bookingRepo.save(aBooking);
    }
    
    public static void persistTimeSlot(TimeSlot aSlot){
//        getRepository(TimeSlotRepository.class).save(aSlot);
        
        Transaction tx = getGraphDb().beginTx();
        try{
            neo4jMediator.save(aSlot);
            tx.success();
        } finally {
            tx.finish();
        }
        
    }
    
    public static void persistRange(Range<TimeSlot> slots){
                Transaction tx = getGraphDb().beginTx();
        try{
            neo4jMediator.save(slots);
            tx.success();
        } finally {
            tx.finish();
        }
    }

    /**
     * Searches for and retrieves a Member object with nodeId in a Type-safe 
     * fashion as {@link org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)}
     * method does not check the entity type, it rather returns the entity if it finds the node.
     * 
     * @param aMember The Member object to be retrieved
     * @return        The entity stored in the node if the node exists and, 
     * the entity is an instance of core.Member. Otherwise, null.
     */
    public static Member retrieveMember(Member aMember) {
        Member retrieveMember = memberRepository.findOne(aMember.getGraphId());
        if (!(retrieveMember instanceof core.Member)) {
            retrieveMember = null;
        }
        return retrieveMember;
    }
    
    /**
     *
     * @return  All Member objects in the GraphStore
     */
    public static Iterable<Member> retrieveAllMembers(){
        return memberRepository.findAll();
    }
    
    /**
     * Searches for and retrieves an Address by its identifier.
     *
     * @param addressIdentifier the identifier of the address to retrieve (houseNum + postCode)
     * @return The address with identifier addressIdentifier if it exists,
     * otherwise null.
     */
    public static Address retrieveAddressByIdentifier(String addressIdentifier) {
        Address foundAddress = null;
        Iterable<Address> foundAddresses = addressRepository.findAllByPropertyValue("identifier", addressIdentifier);
        if (foundAddresses.iterator().hasNext()) {
            foundAddress = foundAddresses.iterator().next();
        }
        return foundAddress;
    }

    /**
     * Method for low level testing.
     */
    public static void printAllNodes() {
        String out;
        for (Node n : GlobalGraphOperations.at(DbService.getGraphDb()).getAllNodes()) {
            if (n.hasProperty("__type__")) {
                out = "" + n.getId();
                for(String key: n.getPropertyKeys()){
                   out += "\t" + key + ": " + n.getProperty(key);
                }
                System.out.println(out);
            }
        }

    }

}
