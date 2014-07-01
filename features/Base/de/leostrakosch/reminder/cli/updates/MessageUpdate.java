package de.leostrakosch.reminder.cli.updates;

public class MessageUpdate extends Update {

  private String msg;
  
  public MessageUpdate(Update.Type type, String msg) {
    super(type);
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}