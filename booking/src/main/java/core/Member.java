package core;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *
 * @author Team
 *
 */
@NodeEntity
public class Member {
    //attributes
    /**
     * The id of the member in the graphDb
     */
    @GraphId
    private Long graphId;
    
    /**
     * the name of the member
     */
    @Fetch
    private Name name;
    
    /**
     * the address of the member
     */
    @Fetch
    private Address address;

    /**
     * the email address of the member
     */
    @Fetch
    private Email email;
    

    //constructor
    /**
     * No-arg constructor for persistence.
     */
    private Member() {
    }

    /**
     * Initialises a new Member object with the given name, Address and email.
     *
     * @param aName the Name of the member
     * @param anAddress the Address of the member
     * @param anEmail the Email of the member
     */
    public Member(Name aName, Address anAddress, Email anEmail) {
        this.name = aName;
        this.address = anAddress;
        this.email = anEmail;
    }

    //public protocol
    /**
     * Returns the name of this Member.
     *
     * @return name
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Sets the name of this Member.
     *
     * @param aName the new name of the member
     */
    public void setName(Name aName) {
        this.name = aName;
    }

    /**
     * Returns the address of this Member.
     *
     * @return Address
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Sets the address of this Member.
     *
     * @param anAddress the new address of the member
     */
    public void setAddress(Address anAddress) {
        this.address = anAddress;
    }

    /**
     * Returns the email address of this Member.
     *
     * @return Email
     */
    public Email getEmail() {
        return this.email;
    }

    /**
     * Sets the email of this Member.
     *
     * @param anEmail the new Email address of the member
     */
    public void setEmail(Email anEmail) {
        this.email = anEmail;
    }

    /**
     *
     * @return the id of member in graphDb
     */
    public Long getGraphId(){
        return graphId;
    }

    /**
     * Returns a string representation of the member.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString() {
        return this.name.toString() + 
                "\n" + this.address.toString()+
                "\n" + email.toString();
    }

}
