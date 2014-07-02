package de.leostrakosch.reminder.format;

import de.leostrakosch.reminder.common.Task;
import de.leostrakosch.reminder.common.IllegalLineFormatException;

public interface TaskFormat {
  String getString(Task t);

  Task getTask(String s) throws IllegalLineFormatException;
}
