package de.leostrakosch.reminder.persistence;

import de.leostrakosch.reminder.format.SeparatorFormatter;
import de.leostrakosch.reminder.common.Task;
import de.leostrakosch.reminder.format.TaskFormat;
import de.leostrakosch.reminder.common.Configuration;
import de.leostrakosch.reminder.common.IllegalLineFormatException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by leo on 14.05.14.
 */
public class TextFileDataManager extends DataManager {

  private static final String SEPARATOR = "|^|";

  private static final File SAVE_FILE = new File(Configuration.APP_DIRECTORY + File.separator + "tasks.txt");

  private TaskFormat format = new SeparatorFormatter(SEPARATOR);

  public TextFileDataManager() {
    File appDir = SAVE_FILE.getParentFile();
    if (!appDir.exists()) {
      appDir.mkdirs();
    }
  }

  @Override
  public void save(List<Task> tasks) throws IOException {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(SAVE_FILE);

      fileWriter.write("");

      for (Task t : tasks) {
        fileWriter.append(format.getString(t) + "\n");
      }
    } finally {
      if (fileWriter != null)
          fileWriter.close();
    }
  }

  @Override
  public List<Task> getTasks() {
    List<Task> tasks = new LinkedList<Task>();
    int lineNumber = 0;

    try {
      BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE));
      String line;

      while ((line = reader.readLine()) != null) {
        lineNumber++;
        tasks.add(format.getTask(line));
      }

    } catch (IOException e) {
      // return existing list

    } catch (IllegalLineFormatException e) {
      System.err.println("Illegal format in line " + lineNumber);
    }

    return tasks;
  }
}
