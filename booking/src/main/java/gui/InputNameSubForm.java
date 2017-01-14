/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import utilities.Name;

/**
 *
 * @author Simon
 */
public class InputNameSubForm extends JPanel{
    JTextField  titleField, firstNameField, surNameField;
    JLabel titleLabel;
    
    InputNameSubForm() {
        setBackground(Color.lightGray);
        setMinimumSize(new Dimension(150, 10));
        
        titleField = new JTextField();
        titleField.setText("Title");

        firstNameField = new JTextField();
        firstNameField.setText("First name");

        surNameField = new JTextField();
        surNameField.setText("Surname");

        titleLabel = new JLabel();
        titleLabel.setText("Enter the new members name here Eg: Mr Bob Hope  ");
        
        setLayout();
    }
    
    private void setLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(firstNameField)
                        .addGap(18, 18, 18)
                        .addComponent(surNameField, PREFERRED_SIZE, 112, PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(surNameField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(firstNameField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(titleField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

    }
    
    /**
     * checks that fields are not empty Strings 
     * @return 
     */
    boolean validateForm() {
        return (!(titleField.getText().isEmpty() && firstNameField.getText().isEmpty() && 
                surNameField.getText().isEmpty()));
    }
    
    /**
     * Creates a new Name object from this forms fields
     * @return Name a new Name
     */
    protected Name constructAddress() {
        return Name.createNewName(Name.Title.valueOf(titleField.getText()), firstNameField.getText(), surNameField.getText());
    }
    
}
