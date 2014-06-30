package de.leostrakosch.reminder.common;

import java.util.Comparator;
import java.util.Date;

import de.leostrakosch.reminder.common.TaskIDComparator;

public class Task implements Comparable {

    private String name;
    private String description;
    private Date dueDate;
    private long id;

    protected Task(String name, String description, Date dueDate, long id) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.id = id;
    }

    protected Task(String name, Date dueDate, long id) {
        this.name = name;
        this.description = "";
        this.dueDate = dueDate;
        this.id = id;
    }

    public static Task create(String name, String description, Date dueDate, long id) {
        return new Task(name, description, dueDate, id);
    }

    public static Task create(String name, Date dueDate, long id) {
        return new Task(name, dueDate, id);
    }

    public final Date getDueDate() {
        return dueDate;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final long getTaskID() {
        return id;
    }

    @Override
    public int compareTo(Object obj) {
        Task task;
        long difference;
        Comparator comp = new TaskIDComparator();
        
        if (!(obj instanceof Task)) {
            throw new AssertionError("Task not compareable to " + obj);
        }
        
        task = (Task) obj;
        return comp.compare(this, obj);
    }
}
