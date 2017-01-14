

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.*;
import utilities.Address;

/**
 * Class for inputing Address
 * @author Simon
 */
public class InputAddressSubForm extends JPanel{
    private static final long serialVersionUID = 1L;
   
    JLabel  address1Label, address2Label, townLabel, countyLabel, postcodeLabel;
    JTextField  address1Field, address2Field, townField, countyField, postcodeField;
    
    public InputAddressSubForm()
    {
        //USE A GridLayout for this form
        super(new GridLayout(0, 2));
        setPreferredSize(new Dimension(150,150));
        
        //tasteful colour
        setBackground(Color.pink);
        
        //instanciate components
        createComponents();
        
        //layout components - basic version!
        //left hand panel for labels
        // Use a GridLayout (row, col)     
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(address1Label);
        labelPane.add(address2Label);
        labelPane.add(townLabel);
        labelPane.add(countyLabel);
        labelPane.add(postcodeLabel);
        
        //right hand panel for fields
        JPanel fieldPane =  new JPanel(new GridLayout(0,1));
        fieldPane.add(address1Field);
        fieldPane.add(address2Field);
        fieldPane.add(townField);
        fieldPane.add(countyField);
        fieldPane.add(postcodeField);

        //Put the panels in this panel, labels on left,
        //text fields on right.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(labelPane);
        add(fieldPane);    
    }
    
    /**
     * 5 labels & 5 textfields
     */
    private void createComponents() {
        
        address1Label = new JLabel("Address:First line");
        address1Label.setHorizontalAlignment(TRAILING);
        
        //general input verifier added here.
        address1Field = new JTextField("First line");
        address1Field.setInputVerifier(new GenInputVerifier());
//        address1Field.addPropertyChangeListener(TOOL_TIP_TEXT_KEY, null);
        
        address2Label = new JLabel("Address:line 2");
        address2Label.setHorizontalAlignment(TRAILING);
        
        //..and here
        address2Field = new JTextField("Second Line");
        address2Field.setInputVerifier(new GenInputVerifier());
        
        townLabel = new JLabel("Town:");
        townLabel.setHorizontalAlignment(TRAILING);
        
        townField = new JTextField("Town");
        //..and here
        townField.setInputVerifier(new GenInputVerifier());
        
        countyLabel = new JLabel("County:");
        countyLabel.setHorizontalAlignment(TRAILING);
        
        countyField = new JTextField("County");
        //..and here
        countyField.setInputVerifier(new GenInputVerifier());
        
        postcodeLabel = new JLabel("Postcode:");
        postcodeLabel.setHorizontalAlignment(TRAILING);
        
        postcodeField = new JTextField("M16 3EA");
        //individual anonymous verifier added here calls private method validatePostcode
        postcodeField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField f = (JTextField) input;
                String formatF = f.getText().replaceAll("\\s+","").toUpperCase();
                return validatePostcode(formatF);
            }        
        });
        
        //Tell accessibility tools about label/textfield pairs.
        address1Label.setLabelFor(address1Field);
        address2Label.setLabelFor(address2Field);
        townLabel.setLabelFor(townField);
        countyLabel.setLabelFor(countyField);
        postcodeLabel.setLabelFor(postcodeField);
    }
    
    
    /**
     * Tests a String is a possible UK postcode format
     *  - does not guarantee that the postcode is correct!
     * @param aString - 
     * @return true if the input is 5 to 8 chars long & consists only of Alphanumerics
     */
    public boolean validatePostcode(String aString) {
        
        final String postcodeStyle = "\\w{5,8}";       
        Pattern pattern = Pattern.compile(postcodeStyle);
        //Creates a matcher that will match the given input against this pattern.
        Matcher matcher = pattern.matcher(aString);
        //Attempts to match the entire region against the pattern.
        return matcher.matches();
        //return aString.matches(pattern);
    }
    
    /**
     * Tests that a textfield is not empty
     * 
     * @param field
     * @return true if the field contains any non empty string
     */
    public boolean validateField(JTextField field) {
        return !field.getText().isEmpty();
    }
    
    /**
     * Creates a new Address object from this forms fields
     * @return 
     */
    protected Address constructAddress() {
        return Address.createNewAddress("",//first line will be removed from utility.Address soon!
                                    address1Field.getText(), 
                                    address2Field.getText(),
                                    townField.getText(),
                                    countyField.getText(),
                                    postcodeField.getText().replaceAll("\\s+","").toUpperCase());
    }
    
    /**
     * Checks that all fields are not empty String
     * & that PostCode is valid
     * @return boolean - true if Class can be constructed
     */
    boolean validateForm() {
        return (validateField(address1Field) && validateField(address2Field) && 
                validateField(townField) && validateField(countyField) && 
                    validatePostcode(postcodeField.getText().replaceAll("\\s+","").toUpperCase()));
    }
    
    //This is attached to all Address fields - does not allow an empty string
    class GenInputVerifier extends InputVerifier{

        @Override
        public boolean verify(JComponent input) {
            JTextField f = (JTextField) input;
            return validateField(f);
        }        
    }   
}
