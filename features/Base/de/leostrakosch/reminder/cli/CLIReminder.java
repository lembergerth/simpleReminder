package de.leostrakosch.reminder.cli;

import de.leostrakosch.reminder.common.*;

import de.leostrakosch.reminder.format.SeparatorFormatter;
import de.leostrakosch.reminder.format.TaskFormat;
import de.leostrakosch.reminder.persistence.DataManager;
import de.leostrakosch.reminder.common.Command;
import de.leostrakosch.reminder.cli.updates.*;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class CLIReminder extends Observable implements Reminder  {

  private static final String COLUMN_SEPARATOR = " | ";

  private List tasks = createList();
  
  public static void main(String[] args) {
    System.err.print("Nothing to do with arguments ");
    for (String s : args) {
      System.err.print(s + " ");
    }
    System.err.println();
  }
		  
  @Override
  public String getHelp() {
    return "Possible commands are:\n" + "\tadd <task> <date>\t\t- adds the given task for the given date\n"
        + "\tlist [date]\t\t- lists all currently existing tasks [up to the given date]\n"
        + "\tdelete <task_id>\t\t- deletes the task with the given id\n" + "\thelp\t\t- shows this message";
  }
  
  private void updateTaskList(List tasks) {
    setChanged();
    notifyObservers(new TaskUpdate(Update.Type.TASKS, tasks));
  }
  
  private void updateStatus(String msg) {
    setChanged();
    notifyObservers(new MessageUpdate(Update.Type.STATUS, msg));
  }
  
  private void updateError(String msg) {
    setChanged();
    notifyObservers(new MessageUpdate(Update.Type.ERROR, msg));
  }

  private long getNextTaskID() {
    LinkedList tasks = new LinkedList(getTasks());
    Comparator comp = new TaskIDComparator();
    long taskId;

    if (tasks.isEmpty()) {
      taskId = 1;
      
    } else {

      Collections.sort(tasks, comp); // sort by task id

      taskId = ((Task) tasks.getLast()).getTaskID() + 1;
    }

    return taskId;
  }

  @Override
  public long addTask(String name) {
    long id = getNextTaskID();
    
    addTask(new Task(name, id)); // TODO : change Task to allow this
    return id;
  }
  
  @Override
  public long addTask(Task t) {
    List<Task> tasks = getTasks();

    tasks.add(t);
    commit(tasks);

    return t.getTaskID();
  }

  @Override
  public void deleteTask(long taskID) {
    List<Task> tasks = getTasks();
    Iterator<Task> taskIterator = tasks.iterator();
    Task currentTask;
    Task removedTask = null;

    while (taskIterator.hasNext()) {
      currentTask = taskIterator.next();

      if (currentTask.getTaskID() == taskID) {
        removedTask = currentTask;
        taskIterator.remove();
      }
    }

    if (removedTask == null) {
      updateStatus("Nothing removed");

    } else {
      commit(tasks);
      updateStatus("Removed task " + removedTask.getName());
    }
  }

  private boolean commit(List tasks) {
    this.tasks = createList(tasks);
    return true;
  }

  @Override
  public List getTasks() {
    return createList(tasks);
  }
 
  private List createList(List list) {
    return new ArrayList(list);
  }
  
  private List createList() {
    return new ArrayList();
  }

}
