package de.leostrakosch.reminder.common;

import java.util.Date;
import java.util.List;

/**
 * Created by leo on 14.05.14.
 */
public interface Reminder {

    public enum Command {ADD, DELETE, LIST, HELP}

    public abstract long addTask(Task t);

    public abstract void deleteTask(long taskID);

    public abstract List<Task> getTasks();

    public abstract List<Task> getTasks(Date d);

    public abstract void displayHelp();
}
