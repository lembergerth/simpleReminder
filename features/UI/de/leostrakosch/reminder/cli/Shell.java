package de.leostrakosch.reminder.cli;

import java.util.Observable;
import java.util.Observer;

public class Shell implements Observer {
  
  @Override
  public void update(Observable arg0, Object arg1) {
    throw new AssertionError("Unsupported update " + arg1);
  }
  
  private void display(String message) {
    throw new AssertionError("Unsupported - no feature supports this");
  }
}