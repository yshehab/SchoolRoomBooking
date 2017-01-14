

package gui;

import core.*;
import dateTime.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * This is the entry point to the GUI
 * 
 * @author Andrea & Simon
 * @version 15/1/2014
 */
public class Start extends JFrame{
    
    //static ref to core
    static final LettingCoord core = LettingCoord.getInstance();
          
    //width                         //height
    static final int W = 500;   static final int H = 600;
    
    //full page size w x h
    static final Dimension fullPageSize = new Dimension(W, H);
    
    //ribbon panel
    static final Dimension thirdBar = new Dimension(W * 24/25, H/3);
    
    //shallower ribbon panel
    static final Dimension quarterBar = new Dimension(W * 24/25, H/4);
    
    //A listbox or similar
    static final Dimension listBox = new Dimension(W/4, H/4);
    
    //border
    static final Border etch = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    
    //empty border
    static final Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    //compound border
    static final Border PanelEdge = BorderFactory.createCompoundBorder
                        (BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                             BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    //reference to the current display
    Display activeOb;
    
    //no argument constructor
    public Start()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this line not required because BorderLayout is default layout for JFrame
        getContentPane().setLayout(new BorderLayout());
        //activeOb set to LettingCoord with no parent by default
        activeOb = new Display(core,null);
        //displays the new page
        add(activeOb.getPage(), BorderLayout.CENTER);
        pack();
    }
    
    /**
     * Method to return the short version of toString()
     *  - Only works if text is properly \n deliminated
     * Cheap & cheerful!
     * 
     * @param o Any object
     * @return The toString() method of o truncated at the first \n.
     */
    static final String shortString(Object o) {
        return o.toString().split("\n")[0];
    }
    
    /**
     * Method called when a new object is selected.
     * 
     * Always polls the core & then redraws.
     * 
     *  - Will throw an Exception in the Display constructor if the object is
     *  - not a core object. Changed 15/1/14 to not throw an exception 
     * 
     * @param selectedOb The object to be displayed
     */
    void newSelectionLayout(Object selectedOb) {
        //clears the content pane
        getContentPane().removeAll();
        //sets a local var to activeOb - the last displayed page
        Display currentPage = activeOb;
        //sets activeOb to a new Display with the selectedOb & last page as args. 
        activeOb = new Display(selectedOb, currentPage);
        //adds it to the centre panel
        add(activeOb.getPage(), BorderLayout.CENTER);
        //These 2 methods ensure the screen is redrawn
        pack();
        revalidate();
    }
    
    /**
     * Method called when the Bread Crumb is selected.
     * 
     * If the selected button is the first (ie LettingCoord) then that is it
     * otherwise constructs the new page from it's parent.
     * 
     * Always polls the core & then redraws.
     * 
     * @param display the newly selected Display
     */
    void backtrackSelectionLayout(Display display) {
        getContentPane().removeAll();
        Display currentDisplay = activeOb;
        int index = currentDisplay.parentPath.indexOf(display);
        if (index == 0) {
            activeOb = new Display(display.coreObject,null);
        }   else {
            Display parent = currentDisplay.parentPath.get(index - 1);
            activeOb = new Display(display.coreObject,parent);
        }
        add(activeOb.getPage(), BorderLayout.CENTER);
        pack();
        revalidate();
    }
    
    //*************METHODS THAT RETURN LISTS****************
    //******************************************************
    //ALL UCs THAT RETURN A COLLECTION RETURN A NAMED JLIST
    
    /**
     * private helper method to set all lists generally
     * 
     * @param list any JList
     */ 
    @SuppressWarnings("unchecked")
    private static void prepList(JList list) {
        list.addMouseListener(new ListMouseAdapter());
        list.setCellRenderer(new ListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSize(listBox);
    }
    
    /**
     * UC All schools
     * @return JList<School>   
     */
    protected static final JList getSchools() {
       JList<School> list = new JList<>();
       prepList(list);
       Collection<School> cSchools = core.getSchools();
       list.setListData(cSchools.toArray(new School[cSchools.size()]));
       list.setName(Display.CORE.SCHOOL.toString());
       System.out.println("Schools list name: "+ list.getName());
       return list;
    }
    
    /**
     * UC All organizations
     * @return JList<Organization> 
     */
    protected static final JList getOrganizations() {
        JList<Organization> list = new JList<>();
        prepList(list);
        Collection<Organization> cOrgs = core.getOrganizations();
        list.setListData(cOrgs.toArray(new Organization[cOrgs.size()]));
        list.setName(Display.CORE.ORGANIZATION.toString());
        return list;
    }
    
    /**
     * UC All members
     * @return JList<Member> 
     */
    protected static final JList getMembers() {
        JList<Member> list = new JList<>();
        prepList(list);
        Collection<Member> cMembers = core.getMembers();
        list.setListData(cMembers.toArray(new Member[cMembers.size()]));
        list.setName(Display.CORE.MEMBER.toString());
        return list;
    }
    
   /**
    * UC Members of an Organization
    * @param org the Organization
    * @return JList<Member>
    */
    protected static final JList getMembers(Organization org) {
        JList<Member> list = new JList<>();
        prepList(list);
        Collection<Member> cMembers = core.getMembers(org);
        list.setListData(cMembers.toArray(new Member[cMembers.size()]));
        list.setName(Display.CORE.MEMBER.toString());
        return list;
    }
    
    /**
     * UC Members of a School
     * @param school
     * @return JList<Member>
     */
    protected static final JList getMembers(School school) {
        JList<Member> list = new JList<>();
        prepList(list);
        Collection<Member> cMembers = core.getMembers(school);
        list.setListData(cMembers.toArray(new Member[cMembers.size()]));
        list.setName(Display.CORE.MEMBER.toString());
        return list;
    }
    
    /**
     * UC Members of a school & an Organization
     * @param school
     * @param org
     * @return JList<Member>
     */
    protected static final JList getMembers(School school, Organization org) {
        JList<Member> list = new JList<>();
        prepList(list);
        Collection<Member> cMembers = core.getMembers(school, org);
        list.setListData(cMembers.toArray(new Member[cMembers.size()]));
        list.setName(Display.CORE.MEMBER.toString());
        return list;
    }
    
    /**
     * UC Permits for an Organization
     * @param org
     * @return JList<Permit>
     */
    protected static final JList getPermits(Organization org) {
        JList<Permit> list = new JList<>();
        prepList(list);
        Collection<Permit> cPermits = core.getPermits(org);
        list.setListData(cPermits.toArray(new Permit[cPermits.size()]));
        list.setName(Display.CORE.PERMIT.toString());
        return list;
    }
    
    /**
     * UC Bookings for a date
     *  - This not called because Date is not a core class
     * @param aDate
     * @return JList<Booking>
     */
    protected static final JList getBookings(Date aDate) {
        JList<Booking> list = new JList<>();
        prepList(list);
        Collection<Booking> cBookings = core.getBookings(aDate);
        list.setListData(cBookings.toArray(new Booking[cBookings.size()]));
        list.setName(Display.CORE.BOOKING.toString());
        return list;
    }
    
    /**
     * UC Bookings for an organization
     * @param org
     * @return JList<Booking>
     */
    protected static final JList getBookings(Organization org) {
        JList<Booking> list = new JList<>();
        prepList(list); 
        Collection<Booking> cBookings = core.getBookings(org);
        list.setListData(cBookings.toArray(new Booking[cBookings.size()]));
        list.setName(Display.CORE.BOOKING.toString());
        return list;
    }
    
    /**
     * UC Bookings for a permit
     * @param permit
     * @return JList<Booking>
     */
    protected static final JList getBookings(Permit permit) {
        JList<Booking> list = new JList<>();
        prepList(list); 
        Collection<Booking> cBookings = core.getBookings(permit);
        list.setListData(cBookings.toArray(new Booking[cBookings.size()]));
        list.setName(Display.CORE.BOOKING.toString());
        return list;
    }
    
    /**
     * UC Bookings for a room
     * @param room
     * @return JList<Booking>
     */
    protected static final JList getBookings(Room room) {
        JList<Booking> list = new JList<>();
        prepList(list);
        Collection<Booking> cBookings = core.getBookings(room);
        list.setListData(cBookings.toArray(new Booking[cBookings.size()]));
        list.setName(Display.CORE.BOOKING.toString());
        return list;
    }
        
    //*************METHODS THAT RETURN BUTTONS****************
    //********************************************************
    //ALL UCs THAT REQUIRE EXTRA PARAMETERS OR MODIFY THE*****
    //CORE RETURN A JBUTTON***********************************
    
    /**
     * UC1 not action yet
     * @param org
     * @param school
     * @return JButton - listener attached
     */
    protected static final JButton UC1CreatePermit( final Organization org, 
                                                    final School school) {
        JButton button = new JButton("Create new Permit");
        button.addActionListener(new ActionListener() {
            Organization o = org;
            School s = school;
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });
        return button;
    }
    /**
     * UC2 not action yet
     * @param member
     * @param room
     * @return JButton - listener attached
     */
    protected static final JButton UC2AddBooking(final Member member,
                                                 final Room room) {
        JButton button = new JButton("Add a new Booking");
        button.addActionListener(new ActionListener() {
            Member m = member;
            Room r = room;
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    /**
     * UC3 not action yet
     * @param permit
     * @param room
     * @return JButton - listener attached 
     */
    protected static final JButton UC3AddBooking(final Permit permit,
                                                 final Room room) {
        JButton button = new JButton("Add a new Booking");
        button.addActionListener(new ActionListener() {
            Permit p = permit;
            Room r = room;
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    /**
     * UC4 not action yet
     * @param booking
     * @return  JButton - listener attached 
     */
    protected static final JButton UC4CancelBoooking(final Booking booking) {
        JButton button = new JButton("Cancel Booking");
        button.addActionListener(new ActionListener() {
            Booking b = booking;
      
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    
    /**
     * UC9 not action yet
     * @param permit
     * @return  JButton - listener attached  
     */
    protected static final JButton UC9CreateAccount(final Permit permit) {
        JButton button = new JButton("Create Account");
        button.addActionListener(new ActionListener() {
            Permit p = permit;
      
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    
    /**
     * UC15 not
     * @param lCoord
     * @return  JButton - listener attached 
     */
    protected static final JButton UC15AddNewOrganization(final LettingCoord lCoord) {
        JButton button = new JButton("Add New Organization");
        button.addActionListener(new ActionListener() {
      
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    
    /**
     * UC16/UC17
     * @param org
     * @return  JButton - listener attached 
     */
    protected static final JButton UC16_17AddMember(final Organization org) {
        JButton button = new JButton("Add or Select Member");
        button.addActionListener(new ActionListener() {
            Organization o = org;
      
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    
    /**
     * UC18 not action yet
     * @param lCoord
     * @return  JButton - listener attached 
     */
    protected static final JButton UC18AddSchool(final LettingCoord lCoord) {
        JButton button = new JButton("Add New School");
        button.addActionListener(new ActionListener() {
      
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
    
    /**
     * UC19 not action yet
     * @param school
     * @return  JButton - listener attached  
     */
    protected static final JButton UC19AddRoom(final School school) {
        JButton button = new JButton("Add New Room");
        button.addActionListener(new ActionListener() {
            School s = school;
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO uc form 
            }
        });        
        return button;
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start().setVisible(true);
            }
        });
    }
}
