package de.leostrakosch.reminder.cli.updates;

public class Update {
  public enum Type {
    STATUS, ERROR, TASKS, REMINDER
  }
  
  private final Type type;
  
  public Update(Type type) {
    this.type = type;
  }
  
  public boolean hasType(Type type) {
    return this.type.equals(type);
  }
}