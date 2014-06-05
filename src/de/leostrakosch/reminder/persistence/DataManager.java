package de.leostrakosch.reminder.persistence;

import de.leostrakosch.reminder.common.Task;

import java.io.IOException;
import java.util.List;

/**
 * Created by leo on 14.05.14.
 */
public abstract class DataManager {

    public static DataManager getInstance() {
        return new TextFileDataManager();
    }

    public abstract void save(List<Task> tasks) throws IOException;

    public abstract List<Task> getTasks();


}
