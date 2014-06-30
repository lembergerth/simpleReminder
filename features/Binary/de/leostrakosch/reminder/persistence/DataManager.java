package de.leostrakosch.reminder.persistence;

import java.io.File;

import de.leostrakosch.reminder.common.Configuration;

public class DataManager {
  
  private static final String SAVE_PATH = Configuration.APP_DIRECTORY + File.separator + "tasks";
  
  public static DataManager getInstance() {
    return new BinaryDataManager(SAVE_PATH);
  }

}