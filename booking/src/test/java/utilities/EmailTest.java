/*
 * GPL 2.0
 */
package utilities;

import org.junit.Test;

/**
 *
 * @author neo
 */
public class EmailTest {

    public EmailTest() {
    }

    /**
     * Test of invalid inputs to class Email.
     */
    @Test (expected = Exception.class)
    public void testInvalidEmails() {
        Email eMail1 = new Email("@somewhere.co.uk"); //nothing before the @
        Email eMail2 = new Email("me@"); //nothing after the @
        Email email3 = new Email("me@somewhere"); //no dot something
        Email eMail4 = new Email(""); //nothing
        Email eMail5 = new Email("me@somewhere..co.uk"); //double dot
    }
}
