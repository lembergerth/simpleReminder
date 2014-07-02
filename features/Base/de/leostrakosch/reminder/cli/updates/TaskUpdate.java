package de.leostrakosch.reminder.cli.updates;

import java.util.List;

public class TaskUpdate extends Update {

  private List tasks;
  
  public TaskUpdate(Update.Type type, List tasks) {
    super(type);
    this.tasks = tasks;
  }
  
  public List getTasks() {
    return tasks;
  }
}