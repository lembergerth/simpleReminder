package de.leostrakosch.reminder.format;

import java.text.ParseException;
import java.util.List;

import de.leostrakosch.reminder.common.Configuration;
import de.leostrakosch.reminder.common.IllegalLineFormatException;
import de.leostrakosch.reminder.common.Task;
import de.leostrakosch.reminder.common.Date;

/**
 * TODO description
 */
public class SeparatorFormatter {

	@Override
	public String getString(Task t) {
		String dueDate = t.getDueDate().getStringRepresentation();

		return t.getTaskID() + separator + dueDate + separator + t.getName()
				+ separator;
	}

	@Override
	public Task getTask(String s) throws IllegalLineFormatException {

		List<String> attributes = splitInput(s, separator);

		if (attributes.size() != 3) { // at least taskID and name needed
			throw new IllegalLineFormatException("Illegal format: " + s);
		}

		long id = Long.valueOf(attributes.get(0));
		String name = attributes.get(2);
		Date dueDate;

		dueDate = Date.valueOf(attributes.get(1));

		return new Task(name, dueDate, id);
	}
}