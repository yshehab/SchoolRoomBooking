/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import core.LettingCoord;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the container for the 3 tabbed panes - Organization, Schools & Reports
 * 
 * @author Andrea &  Simon
 */
public class TabbedPane extends JPanel{
    
    //main tab
    JTabbedPane mainTab;
    
    //3tabbed panes
    OrganizationPanel panel1;
    SchoolsPanel panel2;
    ReportsPanel panel3;
    
    //changed to Frame
    Frame frame;//reference to frame container - UIFrame 
    
    //constructor
    public TabbedPane(Frame aFrame)
    {
        //1 square grid - fills the entire page
        super(new GridLayout(1, 1));
        this.frame = aFrame;
        
        //Sets size based on frame frame
        Dimension pDim = aFrame.getPreferredSize();        
        //setPreferredSize(new Dimension(pDim.width - 25, pDim.height - 91));
        setPreferredSize(new Dimension(UIFrame.framesize.width - 25, UIFrame.framesize.height - 91));
   
        mainTab = new JTabbedPane();
        //ref to frame passed in constructor?
        panel1 = new OrganizationPanel(aFrame);
        //test!
        panel1.setBackground(Color.orange);
        panel2 =  new SchoolsPanel();
        //test!
        panel2.setBackground(Color.green);
        panel3 =  new ReportsPanel();
        //test!
        panel3.setBackground(Color.pink);
                 
        //add tabs to mainTab
        mainTab.addTab("OrganiZations", panel1); 
        mainTab.addTab("skools",panel2);       
        mainTab.addTab("Reports", panel3);
                 
        //Add the tabbed pane to this panel.
        add(mainTab);
    }
     
}
