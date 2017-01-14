

package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

/**
 * Generic mouse listener works with all selection lists
 * 
 * @author Andrea & Simon
 * @version 15/1/2014
 */
public class ListMouseAdapter  extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() > 1) {
               JList l = ((JList)e.getSource());
               Object o = l.getSelectedValue();
               Display.CORE en = Display.getCoreEnum(o);
               if (en != null) {
                   ((Start)l.getRootPane().getParent()).newSelectionLayout(o);
               }
            }
        }
    }   
