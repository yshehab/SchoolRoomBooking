

package gui;

//import gui.*;
import static gui.Start.shortString;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
* This class replaces the default 
* renderer which returns Object.toString()
* so we can display the short first line only in lists.
* It is used by ALL lists in all sub classes.
* 
* @author Andrea & Simon
* @version 15/1/2014
*/
    public class ListRenderer extends JLabel implements ListCellRenderer {

        //constructor - this extends a JLabel so set to opaque.
        public ListRenderer() 
        {
            setOpaque(true);
        }
        
        /**
         * Takes the first line of the object toString to show in the list - will
         * return the full String if the string is not deliminated.
         * @param list any list
         * @param value Objects in the list
         * @param index the index (not used)
         * @param isSelected colours when selected / not selected
         * @param cellHasFocus (not used)
         * @return 
         */
        @Override
        public Component getListCellRendererComponent(  JList list, 
                                                        Object value, 
                                                        int index, 
                                                        boolean isSelected, 
                                                        boolean cellHasFocus) {
            
            setText(shortString(value));          
            if (isSelected) {
                setBackground(Color.red);
            }
            if (!isSelected) {
                setBackground(Color.white);
            }
            return this;
        }

    }
