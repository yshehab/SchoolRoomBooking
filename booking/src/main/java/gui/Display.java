

package gui;
//these are the core objects imported so far
import core.Booking;
import core.LettingCoord;
import core.Member;
import core.Organization;
import core.Permit;
import core.Room;
import core.School;

import static gui.Start.*;
import static gui.Start.shortString;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import static javax.swing.BoxLayout.*;
import javax.swing.*;

/**
 * This class wraps core objects in order to display them in the GUI.
 * It isn't truly polymorphic as we can't access the individual core objects.
 * 
 * The work around is via an inner enum CORE which lists the classes this will
 * recognise & has methods to add Lists & buttons to each display.
 * 
 * The method getPage() is the calling method that returns a JPanel constructed 
 * for the contained core object.
 * 
 * Each displayed page has: A Bread Crumb bar to navigate backwards.
 *                          A display text area of the object.toString().
 *                          A panel of JLists (optional).
 *                          A panel of JButtons for (setter)use cases (optional).
 * 
 * It needs some tidying up.
 * 
 * @author Andrea & Simon
 * @version 15/1/2014
 */
public class Display {
    
    //EnumSet of CORE
    static final EnumSet<CORE> CORE_SET = EnumSet.allOf(CORE.class);
    
    //set of all Classes that this class recognises 
    static final Set<Class> ALL_CORE_CLASSES = CORE.getAllCoreClasses();
            
    //ref to the held core object                                              
    protected final Object coreObject;
    
    //ref to the coreObjects CORE match
    protected final CORE coreEnum;
    
    //this is the basis of the bread crumb
    protected final List<Display> parentPath;
    
    /**
     * Display constructor
     * Changed 15/1/14 to be fault tolerant.
     * If the argument object is enumerated in CORE then sets the coreObject
     * to the argument object & the coreEnum to whatever CORE class corresponds
     * to the Objects runtime class.
     * Else creates a new UnrecognizedObject which wraps the argument object &
     * sets the coreEnum to CORE.UNIDENTIFIED.
     * 
     * @param o Object
     * @param parent Display
     */
    Display(Object o, Display parent) 
    {
        
        if (isCoreClass(o)) {
           this.coreObject = o;
           this.coreEnum = getCoreEnum(o); 
        }   else    {
           this.coreObject = new UnrecognizedObject(o);
           this.coreEnum = CORE.UNIDENTIFIED; 
        }
                 
        if (parent != null) {
           this.parentPath = new ArrayList<>(parent.parentPath);          
        }    else    {
           this.parentPath = new ArrayList<>();
        }
        parentPath.add(this);
    }
       
    /**
     * This helper method matches the arguments class to Core.enum. 
     * @param o
     * @return a CORE Enum or null if no match
     */
    static final CORE getCoreEnum(Object o) {
        Class c = o.getClass();
        Iterator it = CORE_SET.iterator();
        boolean found = false;
        CORE e = null;
        while (it.hasNext() && !found) {
            CORE n = (CORE)it.next();
            if (n.getCoreClass().equals(c)) {
                found = true;
                e = n;
            }
        }
        return e;
    }
    
    /**
     * Helper method called by constructor
     * evaluates whether the argument is a core class
     * @param o
     * @return boolean true if o is a core class
     */
    private boolean isCoreClass(Object o) {
        Class c = o.getClass();
        return ALL_CORE_CLASSES.contains(c);
    }

    /**
     * Called to display this core object
     *  - Needs tidying up
     * 
     * @return the display as a JPanel 
     */
    JPanel getPage() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(Start.fullPageSize);
        panel.setLayout(new BoxLayout(panel, PAGE_AXIS));
        panel.add(makeBreadCrumb());
        panel.add(createDisplayPanel());
        panel.add(createListsPanel());
        panel.add(createButtonPanel());
        panel.setBorder(Start.PanelEdge);
        return panel;
    }
 
    /**
     * Called by getPage()to make the breadcrumb
     * - needs tidying up
     * @return a JPanel
     */
    private JPanel makeBreadCrumb() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(Start.thirdBar);
        panel.setBorder(Start.PanelEdge);
        panel.setLayout(new BoxLayout(panel,  LINE_AXIS));
        for (Display ea: parentPath) {
            panel.add(getCrumb(ea));
        }
        panel.add(Box.createHorizontalGlue());
        return panel;
    }
    
    /**
     * Called as a helper to makeBreadCrumb()
     * @param ea an individual Display
     * @return a JButton
     */
    private JButton getCrumb(Display ea) {
        JButton crumb = new JButton();
        crumb.setText(shortString(ea.coreObject));
        crumb.setMinimumSize(new Dimension(crumb.getText().length()+ 5, 20));
        crumb.addActionListener(new BreadCrumbListener(parentPath.indexOf(ea)));
        return crumb;
    }
    
    /**
     * Called by getPage() to make the display
     * 
     * needs tidying up
     * @return the display as a JPanel
     */
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setColumns(20);
        textArea.setRows(8);
        textArea.setWrapStyleWord(true);
        textArea.setText(coreObject.toString());
        panel.setPreferredSize(Start.thirdBar);
        panel.setLayout(new BoxLayout(panel, LINE_AXIS));
        panel.add(textArea);
        panel.setBorder(Start.PanelEdge);
        return panel;
    }
    
    /**
     * Called by getPage() to make the Lists (if any)
     * 
     * - needs tidying up
     * @return a JPanel of lists or an empty panel if none.
     */
    private JPanel createListsPanel() {
        JPanel panel = new JPanel();
        JList[] lists = CORE.getLists(this);
        
        if (lists.length > 0) {
            panel.setPreferredSize(Start.thirdBar);
            panel.setLayout(new BoxLayout(panel, LINE_AXIS));
            panel.add(Box.createVerticalStrut(25));
            
            for (JList ea: lists) {
                panel.add(createPanel(ea));
                panel.add(Box.createHorizontalGlue());
            }
            panel.setBorder(Start.PanelEdge);
        }
        return panel;
    }
    
    /**
     * Called by createListsPanel() to display a list.
     * @param list (any list)
     * @return a JPanel with a Label/List & Scroll
     */
    private JPanel createPanel(JList list) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(Start.listBox);
        panel.setLayout(new BoxLayout(panel, PAGE_AXIS));
        String s = list.getName();
        JLabel label = new JLabel("Select "+s);
        JScrollPane listScroller = new JScrollPane();
        listScroller.setViewportView(list);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(listScroller); 
        panel.setBorder(Start.PanelEdge);   
        return panel;
    }
    
    /**
     * Called by getPage() to create a button panel  
     * @return JPanel
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton[]buttons = CORE.getButtons(this);
        
        if (buttons.length > 0) {
            panel.setPreferredSize(Start.thirdBar);
            panel.setLayout(new BoxLayout(panel, LINE_AXIS));
            panel.setBorder(Start.PanelEdge);
            panel.add(Box.createVerticalStrut(12));
            
            for (JButton ea: buttons) {
                panel.add(ea);
                panel.add(Box.createHorizontalStrut(10));
                panel.add(Box.createHorizontalGlue());
            }
        }
        return panel;
    }
    
     /**
      * Inner enum that matches Core classes
      * Placing of lists & Buttons is done here.
      * 
      * 15/1/14 UNIDENTIFIED added to enum
      */
    public enum CORE {
        LETTING_COORD(LettingCoord.class),
        ORGANIZATION(Organization.class) ,
        SCHOOL(School.class) ,
        MEMBER(Member.class) ,
        PERMIT(Permit.class) ,
        BOOKING(Booking.class) ,
        ROOM(Room.class) ,
        
        UNIDENTIFIED(UnrecognizedObject.class);//THIS SHOULD be last
        
        //ref to tie enum to core.class
        protected Class coreClass;
 
        
        //constructor for enum       
        private CORE(Class aClass) 
        {
            coreClass = aClass;
        }
        
        /**
         * 
         * @return the Class that the object should be
         */
        final Class getCoreClass() {
            return coreClass;
        }
        
        /**
         * The set of all possible core classes
         * @return Set<Class>
         */
        static final Set<Class> getAllCoreClasses() {
            Set<Class> allClasses = new HashSet<>();
            for (CORE ea: CORE.values()) {
                allClasses.add(ea.getCoreClass());
            }
            return allClasses;
        }
        
        /**
         * returns the use case buttons for each core class
         * 
         * @param display
         * @return JButton[]
         */
        public static final JButton[] getButtons(Display display) {
            Object ob = display.coreObject;
            CORE en = display.coreEnum;
            JButton[]ret = {};
            switch (en) {
                case LETTING_COORD: 
                    ret = new JButton[2];
                    ret[0] = UC15AddNewOrganization((LettingCoord)ob);
                    ret[1] = UC18AddSchool((LettingCoord)ob);
                    break;
                case ORGANIZATION:
                    ret = new JButton[2];
                    ret[0] = UC1CreatePermit((Organization)ob, null);
                    ret[1] = UC16_17AddMember((Organization)ob);
                    break;
                case SCHOOL:
                    ret = new JButton[2];
                    ret[0] = UC1CreatePermit(null, (School)ob);
                    ret[1] = UC19AddRoom((School)ob); 
                    break;
                case MEMBER:
                    ret = new JButton[1];
                    ret[0] = UC2AddBooking((Member)ob, null);
                    break;
                case PERMIT:
                    ret = new JButton[2];
                    ret[0] = UC3AddBooking((Permit)ob, null);
                    ret[1] = UC9CreateAccount((Permit)ob);                             
                    break;
                case ROOM:
                    ret = new JButton[1];
                    ret[0] = UC2AddBooking(null, (Room)ob); 
                    break;
                case BOOKING:
                    ret = new JButton[1];
                    ret[0] = UC4CancelBoooking((Booking) ob);
                    break;
                default:
                    //default returns an empty list 
            }
            return ret;
        }

        /**
         * Returns the lists for each class
         * 
         * @param display
         * @return JList[] 
         */
        public final static JList[] getLists(Display display) {
            Object ob = display.coreObject;
            CORE en = display.coreEnum;
            JList[]ret = {};
            switch (en) {
                case LETTING_COORD: 
                    ret = new JList[2];
                    ret[0] = getOrganizations();
                    ret[1] = getSchools();               
                    break;
                case ORGANIZATION:
                    ret = new JList[2];
                    ret[0] = getPermits((Organization)ob);
                    ret[1] = getMembers((Organization)ob);
                    
                    break;
                case SCHOOL:
                    ret = new JList[1];
                    ret[0] = getMembers((School)ob);
                    //getRooms & getPermits yet to be designed
                    break;
                case MEMBER:
                    //nothing here - Member has no Jlists
                    break;
                case PERMIT:
                    ret = new JList[1];
                    ret[0] = getBookings((Permit)ob);
                    break;
                case ROOM:
                    ret = new JList[1];
                    ret[0] = getBookings((Room)ob);
                    break;
                case BOOKING:
                    //nothing here - Booking has no Jlists
                    break;
                default:
                    //default returns an empty list           
            }
            return ret;
        }        
    }
    
    /**
     * Inner generic class attached to each BreadCrumb button 
     */
    class BreadCrumbListener implements ActionListener {
        
        //reference to the position of the crumb in the page heirachy.
        int index;
        
        //constructor
        BreadCrumbListener(int index)
        {
            this.index = index;
        }

        /**
         * The button has no knowledge of it's display.
         * ((JContainer)e.getSource()).getRootPane().getParent() will return the
         * parent frame - JFrame, JDialog Etc.
         * 
         * @param e event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Start st =(Start)((JButton)e.getSource()).getRootPane().getParent();
            Display display = st.activeOb.parentPath.get(index);
            st.backtrackSelectionLayout(display);
        }
    }
    
    /**
     * 15/1/14 Inner class added - UnrecognizedObject
     * This class is used to wrap objects that are not enumerated in CORE.
     * 
     * Overridden toString method to show what the object is when displayed 
     */
    class UnrecognizedObject {
        Object anyOb;
        
        UnrecognizedObject(Object o)
        {
            anyOb = o;
        }
        
        @Override
        public String toString() {
            return "Unrecocnised object" +
                    "\n runtime class = " + anyOb.getClass().toString() +
                    "\n" + anyOb.toString();
        }
    }
}
