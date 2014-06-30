package de.leostrakosch.reminder.common;

import java.util.Date;
import java.util.List;

/**
 * Created by leo on 14.05.14.
 */
public interface Reminder {

    public abstract long addTask(Task t);

    public abstract void deleteTask(long taskID);

    public abstract List getTasks();

    public abstract List getTasks(Date d);

    public abstract void displayHelp();
}