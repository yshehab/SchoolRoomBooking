package core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import utilities.Address;

/**
 *
 * Organization objects represent organizations in the system.
 *
 * @author Team
 */
@NodeEntity
public class Organization {
    //attributes

    @GraphId
    private Long graphId;

    /*
     * the name of the organization
     */
    private String name;

    @Fetch
    private Address address;

    @Fetch
    @RelatedTo
    private Set<Member> members;

    @Fetch
    @RelatedTo(elementClass = Permit.class)
    private Set<Permit> permits;

    /**
     * No-arg constructor for persistence
     */
    private Organization() {
    }

    //constructor
    /**
     * Initializes a new Organization object with the given name, address and
     * member.
     *
     * @param aName the name of the organization
     * @param anAddress the address of the organization
     * @param aMember the initial member of the organisation
     */
    public Organization(String aName, Address anAddress, Member aMember) {
        this.name = aName;
        this.address = anAddress;
        this.members = new HashSet<>();
        this.members.add(aMember);
        this.permits = new HashSet<>();
    }

    //copy constructor
    Organization(Organization organization) {
        this.name = organization.name;
        this.address = organization.address;
        this.members = organization.members;
        this.permits = organization.permits;
    }

    //public protocol
    public Long getGraphId() {
        return graphId;
    }

    /**
     * Returns the name of this Organization.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the members of this Organization.
     *
     * @return members
     */
    public Collection<Member> getMembers() {
        return Collections.unmodifiableCollection(this.members);
    }

    /**
     * Returns the permits of this Organization.
     *
     * @return permits
     */
    public Collection<Permit> getPermits() {
        return Collections.unmodifiableCollection(permits);
    }

    public Collection<Account> getAccounts() {
        Set<Account> accounts = new HashSet<>();
        for (Permit permit : permits) {
            Account account = permit.getAccount();
            if (account != null) // A permit can have 0 or 1 accounts.
            {
                accounts.add(account);
            }
        }
        return accounts;
    }

    /**
     * Returns the bookings linked to the permits of this Organization.
     *
     * @return permits
     */
    public Collection<Booking> getBookings() {
        //stub method for testing
        Set<Booking> result = new HashSet<>();
        return result;
        //TODO write this method
    }

    /**
     * Returns a string representation of the organization.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString() {
        return this.name
                + "\n" + address.toString();
    }

    //package protocol
    /**
     * Adds aMember to the organization.
     *
     * @param aMember the new Member to be added
     */
    void addMember(Member aMember) {
        members.add(aMember);
    }

    Permit addPermit(Member aMember) {
        Permit permit = new Permit(this, aMember);
        permits.add(permit);
        return permit;
    }
}
