package de.leostrakosch.reminder.format;

import de.leostrakosch.reminder.common.Task;
import de.leostrakosch.reminder.common.Configuration;
import de.leostrakosch.reminder.common.IllegalLineFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by leo on 05.06.14.
 */
public class SeparatorFormatter implements TaskFormat {

  private String separator;

  public SeparatorFormatter(String separator) {
    this.separator = separator;
  }

  @Override
  public String getString(Task t) {
    DateFormat dateFormat = Configuration.DATE_FORMATTER;
    String dueDate = dateFormat.format(t.getDueDate());

    return t.getTaskID() + separator + dueDate + separator + t.getName() + separator + t.getDescription() + separator;
  }

  @Override
  public Task getTask(String s) throws IllegalLineFormatException {
    DateFormat dateFormat = Configuration.DATE_FORMATTER;

    List<String> attributes = splitInput(s, separator);

    if (attributes.size() != 4) { // at least taskID and name needed
      throw new IllegalLineFormatException("Illegal format: " + s);
    }

    long id = Long.valueOf(attributes.get(0));
    String name = attributes.get(2);
    String description = attributes.get(3);
    Date dueDate;

    try {
      dueDate = dateFormat.parse(attributes.get(1));

    } catch (ParseException e1) {
      try {
        dueDate = dateFormat.parse(Configuration.DEFAULT_DATE_STRING);

      } catch (ParseException e2) {
        throw new AssertionError(e2);
      }
    }

    return Task.create(name, description, dueDate, id);
  }

  private List<String> splitInput(String input, String separator) {
    ArrayList<String> output = new ArrayList<String>(5);
    int pointer = 0;
    int separatorSize = separator.length();
    String currString = input;
    String currPeek;

    while (pointer < currString.length()) {
      currPeek = currString.substring(pointer, pointer + separatorSize);

      if (currPeek.equals(separator)) {
        output.add(currString.substring(0, pointer));
        currString = currString.substring(pointer + separatorSize);
        pointer = 0;

      } else {
        pointer++;
      }
    }

    return output;
  }
}
