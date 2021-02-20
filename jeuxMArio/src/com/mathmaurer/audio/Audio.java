package com.mathmaurer.audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private Clip clip;
	private static Clip previousClip;
//	private static Mixer mixer;
	private static int cmpt = 0; // compte le nombre de song qui ont deja ete lance

	/***************************************
	 * constructor
	 ***********************************/
	public Audio(String song) {
		AudioInputStream audio = null;
		
		if (Audio.cmpt >= 1) {
			Audio.previousClip.stop();
		}
		
		try {
			audio = AudioSystem.getAudioInputStream(getClass().getResource(song));
		} catch (UnsupportedAudioFileException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (clip.isActive() == false) {
				clip.open(audio);
			} else {
				System.out.println("le song se joue deja");

			}
		} catch (LineUnavailableException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("erreur");
		}
		
		Audio.previousClip = this.clip;
		Audio.cmpt++;
//			clip.setFramePosition(0);
	}

	/**************************************
	 * getter
	 ********************************************/
	public static int getCmpt() {
		return cmpt;
	}

	/**************************************
	 * setter
	 ********************************************/
	public static void setCmpt(int cmpt) {
		Audio.cmpt = cmpt;
	}

	/*************************************
	 * methods
	 ********************************************/
	public void play() {
		clip.start();
	}

	public void close() {
		clip.close();
	}

	public static void playSong(String song) {
		Audio audio = new Audio(song);
		audio.play();
		// audio.close();
		// System.out.println(cmpt);
	}

}
