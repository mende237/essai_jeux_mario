package com.mathmaurer.jeu;

import com.mathmaurer.utilitaire.TransitionState;

public class GameManagement {
	private static boolean interupt = false;
	private static boolean begin = false;
	private static TransitionState state;
	private static int sem = 1;
	
	/********************************getter*********************************/
	public static TransitionState getState() {
		return GameManagement.state;
	}
	public static boolean isInterupt() {
		return interupt;
	}
	
	public static boolean isBegin() {
		return GameManagement.begin;
	}
	/*****************************setter********************************/
	public static void setState(TransitionState state) {
		GameManagement.state = state;
	}
	
	public static void setInterupt(boolean interupt) {
		GameManagement.interupt = interupt;
	}
	
	public static void setBegin(boolean begin) {
		GameManagement.begin = begin;
	}
	/**************************methods*************************************/
	public static int DOWN() {
		if(GameManagement.sem > 0) {
			GameManagement.sem = GameManagement.sem - 1;
			return GameManagement.sem;
		}else
			return -1;
	}
	
	public static void UP() {
		GameManagement.sem = 1; 
	}
	//public static void printNumberOfLive()
}
