package de.leostrakosch.reminder.persistence;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.leostrakosch.reminder.persistence.DataManager;

public class TaskCache {
  private List cache;
  private DataManager manager = DataManager.getInstance();

  public TaskCache(DataManager manager) {
    cache = new LinkedList(manager.getTasks());
  }

  public void updateCacheAndCommit(List tasks) throws IOException {
    if (!tasks.equals(cache)) {
      cache = new LinkedList(tasks);
      manager.save(cache);
    }
  }

  public List getTasks() {
    return new LinkedList(cache);
  }
}