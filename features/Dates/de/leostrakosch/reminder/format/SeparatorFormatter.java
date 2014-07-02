package de.leostrakosch.reminder.format;

import java.text.ParseException;
import java.util.List;

import de.leostrakosch.reminder.common.Configuration;
import de.leostrakosch.reminder.common.IllegalLineFormatException;
import de.leostrakosch.reminder.common.Task;
import de.leostrakosch.reminder.common.Date;


public class SeparatorFormatter {

	@Override
	public String getString(Task t) {
		String dueDate = t.getDueDate().getStringRepresentation();

		return original(t) + dueDate + separator;
	}

	@Override
	public Task getTask(String s) throws IllegalLineFormatException {
    Task task;
    Date dueDate;
		List attributes = splitInput(s, separator);
		String originalString = "";
		String newAttribute = (String) attributes.get(attributes.size() - 1); // get last attribute
		
		for (Object o : attributes) {
		  originalString += (String) o + separator;
		}
		
		task = original(originalString);
		
		dueDate = Date.valueOf(newAttribute);

		task.setDate(dueDate);
		
		return task;
	}
}