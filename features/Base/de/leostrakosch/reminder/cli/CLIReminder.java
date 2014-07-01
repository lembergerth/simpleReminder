package de.leostrakosch.reminder.cli;

import de.leostrakosch.reminder.common.*;
import de.leostrakosch.reminder.format.SeparatorFormatter;
import de.leostrakosch.reminder.format.TaskFormat;
import de.leostrakosch.reminder.persistence.DataManager;
import de.leostrakosch.reminder.common.Command;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class CLIReminder implements Reminder {

  private static final String COLUMN_SEPARATOR = " | ";

  private List tasks = createList();

  public static void main(String[] args) {
    CLIReminder reminder = new CLIReminder();

    if (args.length < 1) {
      reminder.error(Errors.ERR_WRONG_ARG_NUMBER);
      reminder.displayHelp();
      return;
    }

    try {
      Command cmd = reminder.getCommand(args[0]);

      reminder.execute(cmd, args);

    } catch (WrongArgumentException e) {
      reminder.error(e.getMessage());
      reminder.displayHelp();
    }
  }

  private void execute(Command c, String[] args) {
    // TODO remove
  }
  
  public Command getCommand(String command) throws WrongArgumentException {
    try {
      return Command.valueOf(command.toUpperCase());

    } catch (IllegalArgumentException e) {
      throw new WrongArgumentException(e);
    }
  }

  private void listTasks() {
    List<Task> tasks = getTasks();
    
    displayTasks(tasks);
  }

  private void listTasks(Date lastDate) {
    List<Task> tasks = getTasks();
    Iterator<Task> it = tasks.iterator();
    Task currTask;

    while (it.hasNext()) {
      currTask = it.next();
      if (currTask.getDueDate().after(lastDate)) {
        it.remove();
      }
    }

    displayTasks(tasks);
  }

  private void displayTasks(List tasks) {
    throw new AssertionError("Not implemented by feature UI");
//    TaskFormat formatter = new SeparatorFormatter(COLUMN_SEPARATOR);
//
//    display("ID" + COLUMN_SEPARATOR + "due date" + COLUMN_SEPARATOR + "name" + COLUMN_SEPARATOR + "description");
//
//    Iterator it = tasks.iterator();
//    while (it.hasNext()) {
//      Task task = (Task) it.next();
//      display(formatter.getString(task));
//    }
//
//    display("\n" + tasks.size() + " task(s) displayed.");
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
      display("Nothing removed");

    } else {
      commit(tasks);
      display("Removed task " + removedTask.getName());
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

  @Override
  public List getTasks(Date d) {
    List tasks = getTasks();
    Iterator taskIterator = tasks.iterator();
    Task currentTask;

    while (taskIterator.hasNext()) {
      currentTask = (Task) taskIterator.next();

      if (!currentTask.getDueDate().equals(d)) {
        taskIterator.remove();
      }
    }

    return null;
  }
 
  private List createList(List list) {
    return new ArrayList(list);
  }
  
  private List createList() {
    return new ArrayList();
  }
  
  private void display(String s) {
    System.out.println(s);
  }

  private void error(String s) {
    System.err.println(s);
  }

  @Override
  public void displayHelp() {
    throw new AssertionError("Not implemented by UI feature");
  }

  private String getHelp() {
    return "Possible commands are:\n" + "\tadd <task> <date>\t\t- adds the given task for the given date\n"
        + "\tlist [date]\t\t- lists all currently existing tasks [up to the given date]\n"
        + "\tdelete <task_id>\t\t- deletes the task with the given id\n" + "\thelp\t\t- shows this message";
  }
}
