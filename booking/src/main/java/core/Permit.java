package core;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 *
 * @author Elodie 
 * Constructor changed & links to Member, Organization & bookings added 31/10/13 Simon Page 
 * Permit objects represent permits in the system.
 */
@NodeEntity
public class Permit
{
     //attributes
    
    @GraphId 
    private Long graphId;

    /*
     * class variable to ensure all Permit objects have a unique number
     */
    private static int number = 100000000;

    /*
     * the unique number of the permit
     */
    
    private  String uniqueNumber;
    
    //The menber this permit is linked to
    @Fetch
    private  Member member;
    
    //The organization this permit is linked to
    @Fetch
    private  Organization organization;
    
    //The linked Booking objects
    @Fetch
    @RelatedTo(elementClass = Booking.class)
    private Collection<Booking> bookings;
    
    @Fetch
    private Account account;
    
    //constructor
    
    /**
     * No-arg constructor for persistence.
     */
    private Permit(){

    }
    
    /**
     * Initializes a new Permit object with a unique number.
     * Increments class variable Permit.number
     */
    Permit(Organization anOrg, Member aMember)
          // changed to agree with addPermit() in LettingCoord -- neo
          //removed School argument - no need for a Permit to record which school it is linked to - Ben 4/12/13
    {
        uniqueNumber = Integer.toString(Permit.number);
        Permit.number = Permit.number + 1;
        organization = anOrg;
        member = aMember;
        bookings = new HashSet<>();
    }

    //public protocol
    
    /**
     * Returns the number of this Permit.
     *
     * @return name
     */
    public String getUniqueNumber()
    {
        return this.uniqueNumber;
    }

    /**
     * Returns a string representation of this Permit.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString()
    {
         return "Permit no: " + uniqueNumber +
                "\nOrganization: " + organization.getName() +
                "\nLinked member: " + member.getName().toString();
    }
    
    /**
     * 
     * @return a collection of Bookings for this Permit 
     */
    Collection<Booking> getBookings()
    {
       return new HashSet<>(bookings); 
    }
    
    /**
     * 
     * @param aBooking 
     */
    void addBooking(Booking aBooking)
    {
       bookings.add(aBooking); 
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @return the organization
     */
    public Organization getOrganization() {
        return organization;
    }
    
        
    /**
     * @param theAccount 
     */
    void setAccount(Account theAccount)
    {
        this.account = theAccount;
    }
    
    /**
     * @return The Account linked to this Permit or null if no Account is linked.
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * @return the graphId
     */
    public Long getGraphId() {
        return graphId;
    }

}
