package de.leostrakosch.reminder.common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class Configuration {

  private Configuration() {

  }

  private final static String DATE_FORMAT = "d.M.y";
  public final static DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

  public final static String APP_DIRECTORY = System.getProperty("user.home") + File.separator + ".reminder";
  public final static String DEFAULT_DATE_STRING = "01.01.1900";
}
