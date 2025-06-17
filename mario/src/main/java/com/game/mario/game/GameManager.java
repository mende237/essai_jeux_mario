package com.game.mario.game;

import com.game.mario.util.TransitionState;

public class GameManager {
	private static boolean interupt = false;
	private static boolean begin = false;
	private static TransitionState state;
	private static int sem = 1;

	/******************************** getter *********************************/
	public static TransitionState getState() {
		return GameManager.state;
	}

	public static boolean isInterupt() {
		return interupt;
	}

	public static boolean isBegin() {
		return GameManager.begin;
	}

	/***************************** setter ********************************/
	public static void setState(TransitionState state) {
		GameManager.state = state;
	}

	public static void setInterupt(boolean interupt) {
		GameManager.interupt = interupt;
	}

	public static void setBegin(boolean begin) {
		GameManager.begin = begin;
	}

	/************************** methods *************************************/
	public static int DOWN() {
		if (GameManager.sem > 0) {
			GameManager.sem = GameManager.sem - 1;
			return GameManager.sem;
		} else
			return -1;
	}

	public static void UP() {
		GameManager.sem = 1;
	}
	// public static void printNumberOfLive()
}
