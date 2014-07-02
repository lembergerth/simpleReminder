package de.leostrakosch.reminder.format;


public class SeparatorFormatter {

  private static final char FAVORITE_MARKER = 'F';
  
  @Override
  public String getString(Task t) {
    char isFavorite = t.isFavorite() ? FAVORITE_MARKER : ' ';
    
    return original(t) + isFavorite + separator;
  }
  
  @Override
  public Task getTask(String s) throws IllegalLineFormatException {
    Task task;
    boolean isFavorite;
    List attributes = splitInput(s, separator);
    String originalString = "";
    String newAttribute = (String) attributes.get(attributes.size() -1); // get last attribute
    
    for (Object o : attributes) {
      originalString += (String) o + separator;
    }
    task = original(originalString);
    
    isFavorite = newAttribute.equals(FAVORITE_MARKER);
    task.setFavorite(isFavorite);
    
    return task;
  }
}