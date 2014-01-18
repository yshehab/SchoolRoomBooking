/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import utilities.Address;
import utilities.Email;
import utilities.Name;

/**
 *  
 * @author Andrea & Simon
 */
public class AddOrganizationModalForm extends JPanel {

    //reference to anonymous JDialog added here & constructor
    JDialog parent;
    
    //reference to UIFrame instance added here & constructor
    Frame frame;
    
    //2 address panels to be included - this is the hand coded bit
//    AddressUtilityPanel organizationAddress, memberAddress;
    InputAddressSubForm organizationAddress, memberAddress;
    
    //Name sub form added
    InputNameSubForm memberName;
    
    JLabel memberEmailLabel, orgNameLabel;// memberNameLable,
    
    JTextField  orgNameTextField, memberEmailTextField;//memberNameTextField,
    
    JButton AddButton, backButton;
    
    //these are the arguments for core use case 15a
    String orgName;
    Address orgAddress;
    Name memName;
    Address memAddress;
    Email memE;
    
    
    public AddOrganizationModalForm(JDialog parent, Frame frame) {
        
        this.frame = frame;
        this.parent = parent;
        setPreferredSize(new Dimension(666, 675));
        createComponents();
        layoutComponents();
    }
    
    private void createComponents() {
//        organizationAddress = new AddressUtilityPanel();
//        memberAddress = new AddressUtilityPanel();
        organizationAddress = new InputAddressSubForm();
        memberAddress = new InputAddressSubForm();
        
        memberName = new InputNameSubForm();
        
        orgNameLabel = new JLabel();
        orgNameTextField = new JTextField("");
        orgNameTextField.setBackground(Color.red);
        orgNameLabel.setText("Name of Organization");
    
//        memberNameLable = new JLabel("Name of member");
//        memberNameLable.setToolTipText("<html>Does the user need to access existing Members here?<br>All members of all organizations?<br>How do we know that new Members are not duplicated in the core?<html/>");
//        
//        memberNameTextField = new JTextField();
//        memberNameTextField.setBackground(Color.red);
        
        memberEmailTextField = new JTextField();
        memberEmailLabel = new JLabel("Members e mail");
        
        
        AddButton = new JButton("Add");
        AddButton.setToolTipText("<html>Confirmation button to add new Organization with new Member<br>Not hooked up<html>");
        AddButton.addActionListener(new ActionListener(){    
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonAction();
            }
            
        });
        
        backButton = new JButton("Back");
        backButton.setToolTipText("<html>Cancel button to dispose of this form<br>Hooked up<html>");
        backButton.addActionListener(new ActionListener(){    
            @Override
            public void actionPerformed(ActionEvent e) {
                //disposes of the anonymous JDialog which owns this form
                //returns the user to main panel
                parent.dispose();
            }
            
        });
        
    }
    
    private void layoutComponents() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                
                                //change here: this.organizationAddress instance variable inserted for AddOrg.addressPanel(Detailed LH panel)     
                                .addComponent(organizationAddress)//, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(172, 172, 172))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(orgNameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orgNameTextField, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
//                              .addComponent(memberNameLable)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(memberName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                                //change here: this.memberAddress instance variable inserted for AddOrg.addressPanel1 (this is the empty RH) 
                                .addComponent(memberAddress,  GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))//GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(memberEmailLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)//45
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(memberEmailTextField, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(orgNameLabel)
                    .addComponent(orgNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                    .addComponent(memberNameLable)
                    .addComponent(memberName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    
                    //change here: this.organizationAddress instance variable inserted for AddOrg.addressPanel(Detailed LH panel)     
                    .addComponent(organizationAddress)//, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    
                    //Here again    
                    .addComponent(memberAddress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(memberEmailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberEmailLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))//groupLayout added here
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButton)
                    .addComponent(backButton))
                .addContainerGap())
        );
    }
    
    /**
     * This is (overloaded use case 15) - add a new Organization
     * 
     * TODO - method to create Address should be within AddressUtilityPanel
     * TODO - method to validate Address - ditto -
     * ISSUE - AddOrganization form needs 3 fields for Member name
     */
    private void addButtonAction() {
        orgName = orgNameTextField.getText();//first arg
        
        //organization address:
        if (organizationAddress.validateForm()) {            
            orgAddress = organizationAddress.constructAddress(); 
        }   else {
            //TODO Error output?
            System.out.println("Unable to create Address");
            return;
        }        
        
        if (memberName.validateForm()) {
            memName = memberName.constructAddress();
        }   else {
            //TODO Error output?
            System.out.println("Unable to create Member Name");
            return;
        }
        //memberAddress
        if (memberAddress.validateForm()) {
            memAddress = memberAddress.constructAddress(); 
        }   else {
            //TODO Error output?
            System.out.println("Unable to create Address");
            return;
        }
               
        //member E Mail
        String memEMail = memberEmailTextField.getText();
        //altered here by neo to cope with a refactor
        //cheeky I know! ;-) but that's me
        try{
            memE = new Email(memEMail);
        }
        catch(IllegalArgumentException iaex){
            System.out.println(iaex.toString());
        }
        //parcel it all up to the UIFrame:
        UIFrame uiFrame = (UIFrame)frame;
        uiFrame.addOrganization(this);
        
        parent.dispose();
    }
}
