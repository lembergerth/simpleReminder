package de.leostrakosch.reminder.common;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Observer;
import java.util.List;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import java.util.TimerTask;

import de.leostrakosch.reminder.cli.updates.ReminderUpdate;

/**
 * 
 */
public class SoundAlarmClock implements Observer{

	public final String audioFilePath;
	public final File audiofile;
	public final long alarmTime = 5 * 1000;
	public int duetaskcount = 0;
	
	public SoundAlarmClock() throws URISyntaxException {
		audioFilePath = SoundAlarmClock.class.getProtectionDomain()
				.getCodeSource().getLocation().toURI().getPath()
				+ "../features/Sound/de/leostrakosch/reminder"
				+ "/BOMB_SIREN-BOMB_SIREN-247265934.wav";
		audiofile = new File(audioFilePath);
	}

	public void activateAlarm() {
		System.out.println(audiofile.getPath());
		TimerTask timerTask = null;
		try {
		final AudioClip sound = Applet.newAudioClip(audiofile.toURI().toURL());
		sound.play();
		
		timerTask = new TimerTask() {
			
			@Override
			public void run() {
				sound.stop();
			}
		};
		}catch (MalformedURLException e) {
			System.out.println("Mist passiert");
		}
		
		Timer alarmDuration = new Timer(true);
		alarmDuration.schedule(timerTask, alarmTime);
		
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof ReminderUpdate) {
			ReminderUpdate tasksUpdate = (ReminderUpdate) arg1;
			List tasks = tasksUpdate.getTasks();
			if(!tasks.isEmpty()) {
				activateAlarm();
			}
		}
	}
}