package core;

import java.math.BigDecimal;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 *
 * @author Team
 * Account objects represent accounts in the system.
 */
@NodeEntity
public class Account
{
     //attributes
    @GraphId 
    private Long graphId;
    
     /*
     * the cost of the Account
     */
    private BigDecimal cost;
    
    //constructor
    
    /**
     * No-arg constructor for persistence.
     */
    private Account(){
    }
    
    /**
     * Initialises a new Account object with the given cost.
     * 
     * @param aCost the Cost of the member
     */
    Account(BigDecimal aCost)
    {
      this.cost = aCost;
    }
    
    //public protocol
    
    /**
     * Returns the cost of this Account.
     *
     * @return cost
     */
    public BigDecimal getCost()
    {
        return this.cost;
    }
    
     /**
     * Sets the cost of this Account.
     *
     * @param aCost the new cost of the account
     */
    public void setCost(BigDecimal aCost)
    {
        this.cost = aCost;
    }
    
    /**
     *
     * @return the id of the underlying node in the graphDb
     */
    public Long getGraphId(){
        return graphId;
    }
    
     /**
     * Returns a string representation of the Account.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString()
    {
        return "Account cost: £" + this.cost.toString();
    }
    
}
