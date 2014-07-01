package de.leostrakosch.reminder.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observer;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.leostrakosch.reminder.common.Reminder;

/**
 * 
 */
public class UI extends JFrame implements Observer {

  private final Reminder reminder;

  public UI(Reminder reminder) {
    this.reminder = reminder;
    
    initialize();
  }
  
  private void initialize() {
    FormLayout layout = new FormLayout("2dlu, fill:pref:grow, 2dlu",
                                       "2dlu, fill:pref, 2dlu, fill:pref:grow, 2dlu, fill:pref, 2dlu");
    CellConstraints cc = new CellConstraints();
    
    setLayout(layout);
    
    add(createInputPanel(), cc.xy(2, 2));
    add(createMainPanel(), cc.xy(2, 4));
    add(createStatusPanel(), cc.xyw(1, 6, 3));
    
    setLookAndFeel();
  }
  
  private void setLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      
    } catch (ClassNotFoundException e) {
    } catch (InstantiationException e) {
    } catch (IllegalAccessException e) {
    } catch (UnsupportedLookAndFeelException e) {
    }
  }
  
  protected Component createInputPanel() {
    return new TaskInputPanel(reminder);
  }
  
  protected Component createMainPanel() {
    return null;
  }
  
  protected Component createStatusPanel() {
    return null;
  }

  @Override
  public void update(Observable observable, Object obj) {

  }

}