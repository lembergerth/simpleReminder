package de.leostrakosch.reminder.common;

import java.util.Date;
import java.util.List;

public interface Reminder {

    long addTask(Task t);
    
    long addTask(String name);

    void deleteTask(long taskID);

    List getTasks();

    String getHelp();

}
