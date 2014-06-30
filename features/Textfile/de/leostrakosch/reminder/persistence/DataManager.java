package de.leostrakosch.reminder.persistence;

import java.io.File;

import de.leostrakosch.reminder.common.Configuration;
import de.leostrakosch.reminder.persistence.TextFileDataManager;

public class DataManager {
  
  private static final String SAVE_FILE_PATH = Configuration.APP_DIRECTORY + File.separator + "tasks.txt";
  private static final String SEPARATOR = "||";
  
  
  public static DataManager getInstance() {
    return new TextFileDataManager(SAVE_FILE_PATH, SEPARATOR);
  }
}