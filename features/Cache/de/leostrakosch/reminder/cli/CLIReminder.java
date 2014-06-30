package de.leostrakosch.reminder.cli;

import java.io.IOException;
import java.util.List;

import de.leostrakosch.reminder.common.Task;
import de.leostrakosch.reminder.persistence.DataManager;
import de.leostrakosch.reminder.persistence.TaskCache;

/**
 * TODO description
 */
public class CLIReminder {

  private TaskCache cache;
  
  @Override
  public List getTasks() {
    if (cache == null) {
      createCache();
    }

    return cache.getTasks();
  }
  
  private boolean commit(List tasks) {
    try {
      cache.updateCacheAndCommit(tasks);
      return true;

    } catch (IOException e) {
      error("Error while commiting: " + e.getMessage());
      return false;
    }
  }
  
  private void createCache() {
    cache = new TaskCache(DataManager.getInstance());
  } 
}