/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author team
 */
public class AddressTest {

    private Address address1, address2, address3, address4, address5, address6;

    /**
     *
     */
    public AddressTest() {
    }

    /**
     * Set up for AddressTest before tests.
     */
    @Before
    public void setUp() {
        address1 = Address.createNewAddress("25", "Penny Lane", "Silverton", "Liverpool",
                "Merseyslide", "li6b28");
        address2 = Address.createNewAddress("25", "Penny Lane", "Silverton", "Liverpool",
                "Merseyslide", "li6b28");
        address3 = Address.createNewAddress("26", "Penny Lane", "Silverton", "Liverpool",
                "Merseyslide", "li6b28");
        address4 = Address.createNewAddress("25", "Penny Lane", null, "Liverpool",
                null, "li6b28");
    }

    /**
     * Test of factory method Address.createNewAddress of Address class.
     */
    @Ignore //Address implementation has changed.
    @Test
    public void testFactory() {
        assertTrue("A new address object should not have been created.", address1 != address2);
        assertTrue("A new address object should have been created", address2 != address3);
        assertTrue("A new address object should have been created", address1.equals(address2));
        assertTrue("A new address object should have been created", !address2.equals(address3));
        assertTrue("A new address object should not have been created", address2.equals(address4));
    }

    /**
     * Test of factory method Address.createNewAddress of Address class.
     */
    @Test
    public void testDefaults_1() {
        try {
            address5 = Address.createNewAddress(null, "Penny Lane", "Silverton", "Liverpool",
                    "Merseyslide", "li6b28");
            fail("This should generate an exception.");
        } catch (RuntimeException re) {
            //we should end up here
        }
    }

    /**
     * Test of factory method Address.createNewAddress of Address class.
     */
    @Test
    public void testDefaults_2() {
        try {
            address6 = Address.createNewAddress("22", "Penny Lane", "Silverton", "Liverpool",
                    "Merseyslide", null);
            fail("This should generate an exception.");
        } catch (RuntimeException re) {
            //we should end up here
        }
    }
}