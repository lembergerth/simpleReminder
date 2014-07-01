package de.leostrakosch.reminder.cli.updates;

public class Update {
  public enum Type {
    STATUS, ERROR, TASKS
  }
  
  private final Type type;
  
  public Update(Type type) {
    this.type = type;
  }
  
  public Type getType() {
    return type;
  }
}