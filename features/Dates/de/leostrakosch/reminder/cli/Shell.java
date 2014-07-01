package de.leostrakosch.reminder.cli;

import de.leostrakosch.reminder.common.Date;
import java.text.DateFormat;
import java.text.ParseException;

import de.leostrakosch.reminder.cli.WrongArgumentException;
import de.leostrakosch.reminder.common.Task;

public class Shell {
	public long addTask(String[] args) throws WrongArgumentException, ParseException {
		if(args.length != 3) {
			throw new WrongArgumentException("Invalid amount of arguments");
		}
		Date dueDate = Date.valueOf(args[2]);
		return this.model.addTask(args[1], dueDate);
	}
	
	public void displayTasks(String[] args) throws ParseException {
		List tasks;
		if(args.length == 2) {
			Date dueDate = Date.valueOf(args[1]);
			tasks = this.model.getTasks(dueDate);
			
		}else if(args.length == 1){
			tasks = this.model.getTasks();
		} else {
			throw new IllegalArgumentException();
		}
		
		for (Object task : tasks) {
			display(model.getTaskFormatter().getString(((Task) task)));
		}
	}
}