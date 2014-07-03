package de.leostrakosch.reminder.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;

import de.leostrakosch.reminder.cli.CLIReminder;
import de.leostrakosch.reminder.common.Date;

public class TaskInputPanel extends JPanel {

  private JTextField inputField;
  private JTextField dateField;
  private CLIReminder reminder;
  
  protected Component createInputField() {
    FormLayout layout = new FormLayout("fill:pref, fill:pref:grow", "fill:pref");
    CellConstraints cc = new CellConstraints();
    JPanel panel = new JPanel(layout);
    
    panel.add(createDateInput(), cc.xy(1, 1));
    panel.add(original(), cc.xy(2, 1));
    
    return panel;
  }
  
  protected Component createDateInput() {
    dateField = new JTextField(Date.FORMAT, 10);
    
    dateField.addFocusListener(new FocusListener() {
      
      @Override
      public void focusLost(FocusEvent arg0) {
        if (dateField.getText().isEmpty()) {
          dateField.setText(Date.FORMAT);
        }
      }
      
      @Override
      public void focusGained(FocusEvent arg0) {
        if (dateField.getText().equals(Date.FORMAT)) {
          dateField.setText("");
        }
      }
    });
    
    return dateField;
  }
  
  protected class AddTaskListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent event) {
      String name = inputField.getText();
      String dateString  = dateField.getText();
      Date dueDate = Date.valueOf(dateString);
      
      reminder.addTask(name, dueDate);
    }
  }
}