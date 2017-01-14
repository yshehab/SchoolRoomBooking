/**
 * GPL 2.0
 *
 * Version 1.0 December 2013
 *
 * May be reused as long as we don't share your blame.
 */
package utilities;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import persistence.GraphStore;

/**
 * Utility class for Address
 *
 * @author team
 */
@NodeEntity
public class Address {

    private final String houseNum;
    private final String firstLine;
    private final String secondLine;
    private final String town;
    private final String county;
    private final String postCode;

    /* for persistence */
    @GraphId
    private Long graphId;

    @Indexed
    private final String identifier;

//    private static Map<String, Address> addresses = new HashMap<>(); //no linger required
    /**
     * No-arg constructor for persistence.
     */
    private Address() {
        houseNum = null;
        firstLine = null;
        secondLine = null;
        town = null;
        county = null;
        postCode = null;
        identifier = null;
    }

    /**
     * Constructor for Address
     *
     * Initializes a new Address object with the parameters passed.
     *
     * @param aHouseNum may not be null
     * @param aFirstLine
     * @param aSecondLine
     * @param aTown
     * @param aCounty
     * @param aPostCode may not be null
     */
    private Address(String aHouseNum, String aFirstLine,
            String aSecondLine, String aTown, String aCounty, String aPostCode) {
        houseNum = aHouseNum;
        firstLine = aFirstLine;
        secondLine = aSecondLine;
        town = aTown;
        county = aCounty;
        postCode = aPostCode.toUpperCase();
        identifier = aHouseNum + aPostCode;
    }

    /**
     * Factory method to create a new Address object
     *
     * @param aHouseNum the house number or name. May not be null.
     * @param aFirstLine the first line
     * @param aSecondLine the second line
     * @param aTown the village/town/city
     * @param aCounty the county
     * @param aPostCode the post code. May not be null.
     * @return Address
     */
    public static Address createNewAddress(String aHouseNum, String aFirstLine, String aSecondLine,
            String aTown, String aCounty, String aPostCode) {
        /* I think this check should be the responsibility of the GUI? - ys 18jan2014*/
        final boolean NO_NUMBER = (aHouseNum == null);
        final boolean NO_POSTCODE = (aPostCode == null);
        if (NO_NUMBER || NO_POSTCODE) {
            throw new RuntimeException("Post code and house name/number are required");
        }

        Address newAddress = GraphStore.retrieveAddressByIdentifier(aHouseNum + aPostCode);
        if (newAddress == null) {
            newAddress = new Address(aHouseNum, aFirstLine, aSecondLine, aTown, aCounty, aPostCode);
        }
        return newAddress;
    }

    /**
     * @return the houseNum
     */
    public String getHouseNum() {
        return houseNum;
    }

    /**
     * @return the firstLine
     */
    public String getFirstLine() {
        return firstLine;
    }

    /**
     * @return the secondLine
     */
    public String getSecondLine() {
        return secondLine;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return the id of the node storing this object.
     */
    public Long getGraphId() {
        return graphId;
    }

    /**
     * Returns a string representation of this Address.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString() {
        return this.houseNum + " " + this.firstLine + ","
                + "\n" + this.secondLine + ","
                + "\n" + this.town + ","
                + "\n" + this.county + "."
                + "\n" + this.postCode.toUpperCase();
    }
}
