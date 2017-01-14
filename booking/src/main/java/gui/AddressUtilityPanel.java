/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.*;
//import java.awt.Dimension;
import javax.swing.*;
import utilities.Address;

/**
 *
 * @author Andrea & Simon
 */
public class AddressUtilityPanel extends JPanel{
    //collected values for user input - mirrors utilities.Address class fields
    private String houseNum;
    private String firstLine;
    private String secondLine;
    private String town;
    private String county;
    private String postCode;
    
    JLabel buildingNoLabel, address1Label, townLabel, countyLabel, postcodeLabel;
    JTextField buildingNoField, address1Field, address2Field, townField, countyField, postcodeField;
    
    public AddressUtilityPanel()
    {
        //standard size & tasteful colour
        setPreferredSize(new Dimension(240, 179));
        setBackground(Color.magenta);
        
        //instanciate components
        createComponents();
        
        //layout components
        layoutComponents();       
    }
    
    //constructor for given address added called when you need to display a known address
    public AddressUtilityPanel(Address anAddress)
    {
        //hold given values from anAddress
        houseNum = anAddress.getHouseNum();
        firstLine = anAddress.getFirstLine();
        secondLine = anAddress.getSecondLine();
        town = anAddress.getTown();
        county = anAddress.getCounty();
        postCode = anAddress.getPostCode();
        
        setBackground(Color.magenta);
        
        
        //instanciate components
        createUneditableComponents();
        
        //layout components
        layoutComponents();       
    }
    
    /**
     * 5 labels & 6 textfields
     */
    private void createComponents() {
        buildingNoLabel = new JLabel("Building No:");
        buildingNoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        buildingNoField = new JTextField("");
        //buildingNoField.addActionListener(null);
        
        address1Label = new JLabel("Address:");
        address1Label.setHorizontalAlignment(SwingConstants.TRAILING);
        
        address1Field = new JTextField("");
        address2Field = new JTextField("");
        
        townLabel = new JLabel("Town:");
        townLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        townField = new JTextField("");
        
        countyLabel = new JLabel("County:");
        countyLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        countyField = new JTextField("");
        
        postcodeLabel = new JLabel("Postcode:");
        postcodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        
        postcodeField = new JTextField("");     
    }
    
    /**
     * Called from constructor with Address argument
     * 
     * in this version fields display the Address arguments & are not editable
     */
    private void createUneditableComponents() {
        buildingNoLabel = new JLabel("Building No:");
        buildingNoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        buildingNoField = new JTextField(houseNum);
        buildingNoField.setEditable(false);
        
        address1Label = new JLabel("Address:");
        address1Label.setHorizontalAlignment(SwingConstants.TRAILING);
        
        address1Field = new JTextField(firstLine);
        address1Field.setEditable(false);
        address2Field = new JTextField(secondLine);
        address2Field.setEditable(false);
        
        townLabel = new JLabel("Town:");
        townLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        townField = new JTextField(town);
        townField.setEditable(false);
        
        countyLabel = new JLabel("County:");
        countyLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        countyField = new JTextField(county);
        countyField.setEditable(false);
        
        postcodeLabel = new JLabel("Postcode:");
        postcodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        
        postcodeField = new JTextField(postCode);
        postcodeField.setEditable(false);
    }
    
    /**
     * copied from addOrg.java!
     */
    private void layoutComponents() {
        GroupLayout addressPanelLayout = new GroupLayout(this);
        setLayout(addressPanelLayout);
        addressPanelLayout.setHorizontalGroup(
            addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buildingNoLabel, GroupLayout.Alignment.TRAILING)
                    .addComponent(address1Label, GroupLayout.Alignment.TRAILING)
                    .addComponent(townLabel, GroupLayout.Alignment.TRAILING)
                    .addComponent(countyLabel, GroupLayout.Alignment.TRAILING)
                    .addComponent(postcodeLabel, GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(address1Field)
                    .addComponent(address2Field)
                    .addGroup(addressPanelLayout.createSequentialGroup()
                        .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(buildingNoField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addComponent(townField, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(postcodeField, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                            .addComponent(countyField))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        addressPanelLayout.setVerticalGroup(
            addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(buildingNoLabel)
                    .addComponent(buildingNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(address1Label)
                    .addComponent(address1Field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(address2Field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(townLabel)
                    .addComponent(townField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(countyLabel)
                    .addComponent(countyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(postcodeLabel)
                    .addComponent(postcodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

    }
}
