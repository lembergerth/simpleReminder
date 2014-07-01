package de.leostrakosch.reminder.common;

import java.text.ParseException;
import java.util.StringTokenizer;

import de.leostrakosch.reminder.common.FeatureRuntimeError;

/**
 * TODO description
 */
public class Date {
	private final String DELIM = ".";
	private byte day;
	private byte month;
	private short year;

	public Date(String dateRepresentation) throws IllegalArgumentException {
		StringTokenizer st = new StringTokenizer(dateRepresentation, DELIM);
		interpretDateRepresentation(st);
	}

	public void interpretDateRepresentation(StringTokenizer st)
			throws IllegalArgumentException {
		/*
		 * throw new FeatureRuntimeError(date,
		 * "Date format must be spezified by different features");
		 */
		/* DELIM nicht vergessen */
		// TODO muss von den verschiedenen features ueberschrieben werden.
		day = Byte.parseByte(st.nextToken());
		month = Byte.parseByte(st.nextToken());
		year = Short.parseShort(st.nextToken());
	}

	public static Date valueOf(String dateRepresentation)
			throws IllegalArgumentException {
		return new Date(dateRepresentation);
	}

	public int compareTo(Date date) {
		return this.getDateRepresentation() - date.getDateRepresentation();
	}

	private int getDateRepresentation() {
		return ((int) this.year) * 1000 + ((int) month) * 10 + ((int) day);
	}

	public String getStringRepresentation() {
		return String.valueOf("" + day + DELIM + month + DELIM + year);
	}
}