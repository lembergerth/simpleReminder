package de.leostrakosch.reminder.ui;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;

public class TaskPanel extends JPanel {

  private FormLayout createLayout() {
    return original();
  }
  
  private void addComponents(Task t) {
    original(t);
    
    CellConstraints cc = new CellConstraints();
    FormLayout currLayout = (FormLayout) getLayout();
    
    currLayout.insertColumn(1, ColumnSpec.decode("fill:pref"));
    currLayout.insertColumn(2, ColumnSpec.decode("4dlu"));
    
    add(new JLabel(t.getDueDate().getStringRepresentation()), cc.xy(1, 1));
  }
}