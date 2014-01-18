/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import core.LettingCoord;
import core.Member;
import core.Organization;
import core.Permit;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This is the entry point for use cases 1, 3, 4, 6, 9, 11, 12, 13, 
 *                                      14 (not added yet), 15, 16, & 17
 * @author Andrea & Simon
 */
public class OrganizationPanel extends JPanel{
    private static final long serialVersionUID = 1L;//delay real value
    
    //All components are instance variables here
    private JButton addOrganizationButton, addBookingButton, addMemberButton,
            addPermitButton, cancelBookingButton, createAccountButton, editMembersDetailsButton;
    
    private JLabel bookingCostLabel, bookingDateLabel, bookingRoomLabel,bookingTimeSlotLabel,
            bookingsListLabel, membersAddressLabel, membersEmailLabel, membersListName, 
            orgListLabel, permitListLabel, selectedBookingLabel;
    
    private JPanel jP1, jP2, jP3;//3 panels that make up the form
    
    //members address area changed to utility address panel
    private AddressUtilityPanel membersAddressArea;
    
    private JTextField membersEmailTextField, selectedBookingCostTextField, selectedBookingDateField,
            selectedBookingRoomTextField, selectedBookingTimeSlotTextField;
    
    //Lists are just dummy objects so far
    JList membersList, orgList, permitList, bookingsList;
    
    private JScrollPane membersAddressScroll, membersListScroll, orgScroll, permitScroll, bookingsListScrol; 
    
    //AddOrganizationModalForm added here?
    AddOrganizationModalForm addOrg;
    //ref to parent frame added here?
    Frame frame;
    //test font
    public static final Font tempF = new Font("Calibri", 2, 10);
    
    static final String[] noOrgSelection = {"No Organization", "is selected"};
    
    //UIFrame passed in constructor
    OrganizationPanel(Frame aFrame)
    {        
        this.frame = aFrame;
        //addOrg = new AddOrganizationModalForm();
        //Test!
        setBackground(Color.orange);
        // parent form - 25 w & 91h 
        setPreferredSize(new Dimension(UIFrame.framesize.width - 25, UIFrame.framesize.height - 91));
        //splits the form into 3 horizontal
        setLayout(new GridLayout(0, 1));
        
        //top panel
        jP1 = new JPanel();
        //Test!
        jP1.setBackground(Color.cyan);
        
        //organization list - temp
        orgScroll = new JScrollPane();
        orgList = new JList();
//        orgList.
        //OrganizationListModel substituted here
//        orgList.setModel(new AbstractListModel() {
//            String[] strings = { "Hells Angels ", "Bagpipe club", "Tap Dancing", "OAP Kung Fu", "Meditation" };
//            @Override
//            public int getSize() { return strings.length; }
//            @Override
//            public Object getElementAt(int i) { return strings[i]; }
//        });
          orgList.setModel(new AbstractListModel() {
            //collection from core:
            Collection<Organization> orgsCol = LettingCoord.getInstance().getOrganizations();
            //converted to typesafe array
            Organization[] orgsArray = orgsCol.toArray(new Organization[orgsCol.size()]);
            @Override
            public int getSize() { return orgsArray.length; }
            @Override
            public Object getElementAt(int i) { return orgsArray[i]; }
        });
        orgScroll.setViewportView(orgList);
        orgListLabel = new JLabel();
        orgListLabel.setText("Please select an organization");
        
        //list selectionListener added here
        orgList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                onSelectedOrganization(); 
            }
        });
        
        addOrganizationButton = new JButton();
        addOrganizationButton.setText("Add Organization");
        addOrganizationButton.setToolTipText("<html>Always enabled <br>Anonymous action listener added<br>Now hooked up to form</html>");
        addOrganizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JDialog dialog = new JDialog(frame);
                dialog.setTitle("Add Organization modal form");
                dialog.setSize(UIFrame.framesize);
                dialog.getContentPane().setLayout(new GridLayout(1,1));
                dialog.add(new AddOrganizationModalForm(dialog, frame));
                frame.pack();
                dialog.setLocationRelativeTo(frame);
                dialog.setModal(true);
                dialog.setVisible(true);
            }

});
        //permitList - temp
        permitScroll = new JScrollPane();
        permitList = new JList();
        //test
        permitList.setFont(new Font("Calibri", 2, 10)); 
        permitList.setModel(new AbstractListModel() {
            Permit[] permits = {};//init with empty list 
            @Override
            public int getSize() {
                return permits.length; 
            }

            @Override
            public Object getElementAt(int index) {
                return permits[index];
            }
        });
            
 
//            String[] strings = { "The permits ..", "..for the selected..", "..organization..", "..will show here..", "..should be empty if no", "..org is selected" };
//            @Override
//            public int getSize() { return strings.length; }
//            @Override
//            public Object getElementAt(int i) { return strings[i]; }
//        });
        permitScroll.setViewportView(permitList);        
        permitListLabel = new JLabel();
        permitListLabel.setText("selected organizations permits");
        
        addPermitButton = new JButton();
        addPermitButton.setText("Add Permit");
        addPermitButton.setToolTipText("Enabled when Organization is selected");
        addPermitButton.setEnabled(false);
        
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        createAccountButton.setToolTipText("Enabled when a permit is selected");
        createAccountButton.setEnabled(false);
        
        //middle panels
        jP2 = new JPanel();
        jP2.setBackground(Color.blue);
        
        //membersList - temp
        membersListScroll = new JScrollPane();
        membersList = new JList();
        membersList.setFont(tempF);
        //memberslistmodel substituted here
        //membersList.setModel(new MembersListModel());
        membersList.setModel(new AbstractListModel() {
            Member[] memArray = {};//init with empty list
            @Override
            public int getSize() { return memArray.length; }
            @Override
            public Object getElementAt(int i) { return memArray[i]; }
        });
        membersListScroll.setViewportView(membersList);
        membersListName = new JLabel();
        membersListName.setText("members of Eg Hells Angels - sel");
        membersListName.setToolTipText("This label is updated to reflect the Organization selected");
        
        addMemberButton = new JButton();
        addMemberButton.setText("Add a Member");
        addMemberButton.setToolTipText("Enabled when an Organization is selected");
        addMemberButton.setEnabled(false);
        
        //members address
        membersAddressLabel = new JLabel();
        membersAddressLabel.setText("Members Address");
        membersAddressScroll = new JScrollPane();
        //members address area changed to utility address panel
        membersAddressArea = new AddressUtilityPanel();//will need Member argument.
        //membersAddressArea.setEditable(false);
        //membersAddressArea.setColumns(20);
        //membersAddressArea.setFont(tempF);
        //membersAddressArea.setRows(5);
        //membersAddressArea.setText("This area will show ..\nthe members address..\n..only if a member is selected\n.. it is not directly editable");
        membersAddressScroll.setViewportView(membersAddressArea);
        
        editMembersDetailsButton = new JButton();
        editMembersDetailsButton.setText("Edit Members Details");
        editMembersDetailsButton.setToolTipText("Enabled when a Member of the Selected Organization is selected");
        editMembersDetailsButton.setEnabled(false);
        
        membersEmailLabel = new JLabel();
        membersEmailLabel.setText("Members e mail");
        
        membersEmailTextField = new JTextField();
        membersEmailTextField.setEditable(false);
        membersEmailTextField.setFont(tempF);
        membersEmailTextField.setText("boz@hellsangels.sco.com");
        
        //bottom panel
        jP3 = new JPanel();
        jP3.setBackground(Color.pink);
        
        //bookings list
        bookingsListScrol = new JScrollPane();
        bookingsList = new JList();
        bookingsList.setFont(tempF);
        bookingsList.setModel(new AbstractListModel() {
            String[] strings = { "Will show the...", "..bookings for the ..", "..selected permit..", "..will be blank if there is no..", "..permit selected" };
            @Override
            public int getSize() { return strings.length; }
            @Override
            public Object getElementAt(int i) { return strings[i]; }
        });        
        bookingsListScrol.setViewportView(bookingsList);
        bookingsListLabel = new JLabel();
        bookingsListLabel.setText("Bookings for selected permit");
        
        selectedBookingLabel = new JLabel();
        selectedBookingLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        selectedBookingLabel.setText("Selected Booking:");
        
        selectedBookingDateField = new JTextField();
        selectedBookingDateField.setFont(tempF); 
        selectedBookingDateField.setText("25,12,2013");
        
        selectedBookingTimeSlotTextField = new JTextField();
        selectedBookingTimeSlotTextField.setFont(tempF); 
        selectedBookingTimeSlotTextField.setText("Evening session");
        
        selectedBookingRoomTextField = new JTextField();
        selectedBookingRoomTextField.setFont(tempF); 
        selectedBookingRoomTextField.setText("Science Lab");
        selectedBookingCostTextField = new JTextField();
        selectedBookingCostTextField.setFont(tempF);
        selectedBookingCostTextField.setText("£25.40");
        
        addBookingButton = new JButton();
        addBookingButton.setText("Add a Booking ");
        addBookingButton.setToolTipText("Enabled when a Permit is selected");
        addBookingButton.setEnabled(false);
        
        bookingDateLabel = new JLabel();
        bookingDateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        bookingDateLabel.setText("Date");
        
        bookingTimeSlotLabel = new JLabel();
        bookingTimeSlotLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        bookingTimeSlotLabel.setText("Time slot");
        
        bookingRoomLabel = new JLabel();
        bookingRoomLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        bookingRoomLabel.setText("room - school?");
        
        bookingCostLabel = new JLabel();
        bookingCostLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        bookingCostLabel.setText("Cost");
        
        cancelBookingButton = new JButton();
        cancelBookingButton.setText("Cancel this Booking");
        cancelBookingButton.setToolTipText("Enabled when a booking is selected");
        cancelBookingButton.setEnabled(false);
        
        //private methods - each panel internal layouts done here
        setLayoutTopPanel();
        setLayoutMiddlePanel();
        setLayoutBottomPanel();
        
        //add the 3 panels to this 
        add(jP1);
        add(jP2);
        add(jP3);
       
    }
    
    private void setLayoutTopPanel() {
        GroupLayout jP1Layout = new GroupLayout(jP1);
        jP1.setLayout(jP1Layout);
        jP1Layout.setHorizontalGroup(
            jP1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jP1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jP1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addOrganizationButton)
                    .addComponent(orgScroll, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(orgListLabel,GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(jP1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jP1Layout.createSequentialGroup()
                        .addGroup(jP1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(jP1Layout.createSequentialGroup()
                                .addComponent(addPermitButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                .addComponent(createAccountButton))
                            .addComponent(permitScroll))
                        .addGap(27, 27, 27))
                    .addGroup(jP1Layout.createSequentialGroup()
                        .addComponent(permitListLabel,GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jP1Layout.setVerticalGroup(
            jP1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jP1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(orgListLabel, GroupLayout.PREFERRED_SIZE, 18,GroupLayout.PREFERRED_SIZE)
                    .addComponent(permitListLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(permitScroll, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(orgScroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jP1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addOrganizationButton, GroupLayout.PREFERRED_SIZE, 23,GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPermitButton)
                    .addComponent(createAccountButton))
                .addGap(32, 32, 32))
        );
        

    }
    
    private void setLayoutMiddlePanel() {
        GroupLayout jP2Layout = new GroupLayout(jP2);
        jP2.setLayout(jP2Layout);
        jP2Layout.setHorizontalGroup(
            jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jP2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addMemberButton)
                    .addComponent(membersListScroll, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(membersListName, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(editMembersDetailsButton)
                    .addGroup(jP2Layout.createSequentialGroup()
                        .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(membersAddressLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                            .addComponent(membersAddressScroll, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(membersEmailLabel, GroupLayout.PREFERRED_SIZE, 109,GroupLayout.PREFERRED_SIZE)
                            .addComponent(membersEmailTextField, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jP2Layout.setVerticalGroup(
            jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jP2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(membersListName)
                    .addComponent(membersAddressLabel)
                    .addComponent(membersEmailLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jP2Layout.createSequentialGroup()
                        .addComponent(membersEmailTextField, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jP2Layout.createSequentialGroup()
                        .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(membersListScroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jP2Layout.createSequentialGroup()
                                .addComponent(membersAddressScroll, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                .addGap(5, 5, 5)))
                        .addGap(18, 18, 18)))
                .addGroup(jP2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addMemberButton)
                    .addComponent(editMembersDetailsButton))
                .addGap(32, 32, 32))
        );
        
    }
    
    private void setLayoutBottomPanel() {
        javax.swing.GroupLayout jP3Layout = new GroupLayout(jP3);
        jP3.setLayout(jP3Layout);
        jP3Layout.setHorizontalGroup(
            jP3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jP3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(bookingsListLabel, GroupLayout.PREFERRED_SIZE, 185,GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBookingButton)
                    .addComponent(bookingsListScrol, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(selectedBookingLabel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jP3Layout.createSequentialGroup()
                        .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(bookingCostLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookingRoomLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                            .addComponent(bookingTimeSlotLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectedBookingCostTextField,GroupLayout.Alignment.TRAILING)
                            .addComponent(selectedBookingRoomTextField, GroupLayout.Alignment.TRAILING)
                            .addComponent(selectedBookingTimeSlotTextField,GroupLayout.Alignment.TRAILING)
                            .addGroup(jP3Layout.createSequentialGroup()
                                .addComponent(cancelBookingButton)
                                .addGap(0, 53, Short.MAX_VALUE))))
                    .addGroup(jP3Layout.createSequentialGroup()
                        .addComponent(bookingDateLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(selectedBookingDateField)))
                .addContainerGap())
        );
        jP3Layout.setVerticalGroup(
            jP3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jP3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bookingsListLabel)
                    .addComponent(selectedBookingLabel)
                    .addComponent(bookingDateLabel)
                    .addComponent(selectedBookingDateField, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jP3Layout.createSequentialGroup()
                        .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(selectedBookingTimeSlotTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookingTimeSlotLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(selectedBookingRoomTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookingRoomLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(selectedBookingCostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookingCostLabel)))
                    .addComponent(bookingsListScrol, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jP3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addBookingButton)
                    .addComponent(cancelBookingButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    /**
     * This is the action when an Organization is selected from OrgList
     * 1) Sets the membersList to display Members of the selected org.
     * 2) Sets the permitList to display Permits of the selected org.
     * 3) Enables the addPermitButton.
     * 4) Enables the addMemberButton.
     * 
     * TODO Refactor!
     */
    private void onSelectedOrganization() {
      Organization theOrg =  (Organization)orgList.getSelectedValue();
      if (theOrg != null) {
          // 1) Members
      Collection<Member> membersColl = LettingCoord.getInstance().getMembers(theOrg);
      Member[] memberArray = membersColl.toArray(new Member[membersColl.size()]);
      membersList.setListData(memberArray);
      membersList.clearSelection();//may not be needed?
      // 2) Permits
      Collection<Permit> permitsColl = LettingCoord.getInstance().getPermits(theOrg);
      Permit[]permitArray = permitsColl.toArray(new Permit[permitsColl.size()]);
      permitList.setListData(permitArray);
      permitList.clearSelection();//may not be needed?

      // 3) activate button
      addPermitButton.setEnabled(true);
      // 4)
      addMemberButton.setEnabled(true);
      }
      
   }
    
    /**
     * this is called when the form needs to reset 
     * - Eg: When an Organization has been added 
     */
    void updateOrgs() {
        Collection<Organization> orgsCol = LettingCoord.getInstance().getOrganizations();
        //converted to typesafe array
        Organization[] orgsArray = orgsCol.toArray(new Organization[orgsCol.size()]);
        orgList.setListData(orgsArray);
        membersList.setListData(noOrgSelection);
        permitList.setListData(noOrgSelection);
        // de activate buttons
        addPermitButton.setEnabled(false);
        addMemberButton.setEnabled(false);
    }
       
}
