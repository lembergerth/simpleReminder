package de.leostrakosch.reminder.common;

/**
 * TODO description
 */
public class Task {

  private boolean userNotified = false;
  
  public boolean isNoted() {
    return userNotified;
  }
  
  public void setNoted() {
    userNotified = true;
  }
}