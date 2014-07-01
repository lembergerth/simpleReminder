package de.leostrakosch.reminder.cli;

import java.text.ParseException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import de.leostrakosch.reminder.cli.CLIReminder;
import de.leostrakosch.reminder.cli.Command;
import de.leostrakosch.reminder.cli.WrongArgumentException;
import de.leostrakosch.reminder.format.TaskFormat;
import de.leostrakosch.reminder.common.Task;

/**
 * TODO description
 */
public class Shell implements Observer {
	CLIReminder model;

	public Shell(CLIReminder reminder, String[] args) {
		this.model = reminder;

		if (args.length < 1) {
			// @see simpleGui
			// TODO GUI musst override the reaction to no arguement
			this.reactToNoArguments();
		} else {
			try {
				Command cmd = Shell.getCommand(args[0]); 
				execute(cmd, args);
			} catch (WrongArgumentException e) {
				System.out.print("Invalid arguments: ");
				for (String argument : args) {
					System.out.print(argument + " ");
				}
				System.out.println();
			}
		}
	}

	public void reactToNoArguments() {
		this.displayHelp();
	}

	private void displayHelp() {
		System.out.println(model.getHelp());
	}

	public static Command getCommand(String commandName)
			throws WrongArgumentException {
		try {
			return Command.valueOf(commandName.toUpperCase());

		} catch (IllegalArgumentException e) {
			throw new WrongArgumentException(e);
		}
	}

	public void execute(Command command, String[] args)
			throws WrongArgumentException {
		switch (command) {
		case ADD:
			try {
				if (args.length <= 2) {
					long taskId = model.addTask(args[1]);
					displayAddedtask(taskId);
				} else {
					throw new ParseException("Invalid amount of arguments", 2);
				}
			} catch (ParseException e) {
				throw new WrongArgumentException(e);
			}
			break;

		case DELETE:
			try {
				long taskID = Long.parseLong(args[1]);
				model.deleteTask(taskID);

			} catch (NumberFormatException e) {
				throw new WrongArgumentException(e);
			}
			break;

		case LIST:
			try {
				displayTasks();

			} catch (ParseException e) {
				throw new WrongArgumentException(e);
			}
			break;

		case HELP:
		default:
			this.displayHelp();
		}
	}

	private void displayAddedtask(long taskId) {
		System.out.println("Task added to list with id " + taskId);
	}
	
	private void displayTasks() throws ParseException {
		TaskFormat formatter = model.getTaskFormatter();
		List taskList = model.getTasks();

		for (Object task : taskList) {
			display(formatter.getString(((Task) task)));
		}
	}

	private void display(String message) {
		System.out.println(message);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}
}