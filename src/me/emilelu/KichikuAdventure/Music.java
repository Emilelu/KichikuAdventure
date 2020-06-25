package me.emilelu.KichikuAdventure;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Music {

	private static File sound;
	private static Sequence seq;
	private static Sequencer midi;
	private static int isEnd = 0;

	static File f = new File("src/me/emilelu/KichikuAdventure");
	static String path = f + "/GodKnows.mid";

	public static int getIsEnd() {
		return isEnd;
	}

	public static void setIsEnd(int isEnd) {
		Music.isEnd = isEnd;
	}

	public static void Play(String filename) {
		try {
			sound = new File(filename);
			seq = MidiSystem.getSequence(sound);
			midi = MidiSystem.getSequencer();
			midi.open();
			midi.setSequence(seq);

			if (!midi.isRunning())
				midi.start();

		} catch (Exception ex) {
		}
	}

	public static void Stop() {
		if (midi.isRunning())
			midi.stop();

		if (midi.isOpen())
			midi.close();
	}

	public static void main(String[] args) {

		while (isEnd == 0) {
			Music.Play(path);
			long time = midi.getMicrosecondLength() / 1000;

			try {
				Thread.currentThread();
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Music.Stop();
		}

	}

}