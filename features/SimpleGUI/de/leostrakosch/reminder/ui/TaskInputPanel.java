package de.leostrakosch.reminder.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.leostrakosch.reminder.common.Reminder;

public class TaskInputPanel extends JPanel {

  private static final String DEFAULT_TEXT = "Add a task!";
  private static final String LAYOUT_COL_SPEC = "fill:pref:grow, 2dlu, fill:pref";
  private static final String LAYOUT_ROW_SPEC = "fill:pref";
  
  private Reminder reminder;
  
  private JTextField inputField;

  public TaskInputPanel(Reminder reminder) {

    this.reminder = reminder;
    
    initialize();
    inputField.setText(DEFAULT_TEXT);

  }
  
  
  protected void initialize() {
    FormLayout layout = new FormLayout(LAYOUT_COL_SPEC, LAYOUT_ROW_SPEC);
    CellConstraints cc = new CellConstraints();
    
    setLayout(layout);

    add(createInputField(), cc.xy(1, 1));
    add(createAddButton(), cc.xy(3, 1));
  }
  
  protected JTextField createInputField() {
    inputField = new JTextField();
    inputField.addActionListener(new AddTaskListener());
    return inputField;
  }
  
  protected Component createAddButton() {
    return new AddTaskButton();
  }
  
  protected class AddTaskButton extends JButton {
    
    public AddTaskButton() {
      addActionListener(new AddTaskListener());
      setText("Add");
    }
  }
  
  protected class AddTaskListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      reminder.addTask(inputField.getText());
      inputField.setText(DEFAULT_TEXT);
      TaskInputPanel.this.requestFocus();
    }
  }
}