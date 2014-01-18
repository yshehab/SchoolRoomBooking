/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import core.LettingCoord;
import core.Organization;
import java.awt.*;

import javax.swing.*;

/**
 *
 * @author Simon
 */
public class UIFrame extends JFrame{
    
    private static final long serialVersionUID = 1L;//leave untill coded
    
    //reference to core
    //private LettingCoord core; - turned off!
    
    //use this to set size
    static final Dimension framesize = new Dimension(746, 675);//666, 675
    
    TabbedPane mainTab;
    
    public UIFrame()
    {
        
        //core = LettingCoord.getInstance();turned off for now
        //exit x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set overall size
        //setMinimumSize(framesize);
        setPreferredSize(framesize);
        mainTab = new TabbedPane(this);//ref added to parent
        add(mainTab);
        
        //Display the window.
        pack();
        setVisible(true);
    }
    
    /**
     * use case 15 a addOrganization
     * @param form 
     */
    public void addOrganization(AddOrganizationModalForm form) {           
        LettingCoord.getInstance().addOrganization( form.orgName, 
                                                    form.orgAddress, 
                                                    form.memName, 
                                                    form.memAddress,
                                                    form.memE);
        //update the Organizations List
        mainTab.panel1.updateOrgs();
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UIFrame();
         //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
            }
        });
    }
}
