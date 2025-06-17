package com.game.mario.game;

import javafx.scene.input.KeyEvent;

public class Clavier {
	private final Scene scene;

	public Clavier(Scene scene) {
		this.scene = scene;
	}

	public void handleKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
			case NUMPAD6:
				scene.mario.setWalke(true);
				scene.mario.setToRight(true);
				scene.setDx(1); // displacement to right of background
				break;
			case NUMPAD4:
				scene.mario.setWalke(true);
				scene.mario.setToRight(false);
				scene.setDx(-1); // displacement to left of background
				break;
			case SPACE:
				scene.mario.setJump(true);
				// com.mathmaurer.audio.Audio.playSong("/audio/saut.wav");
				break;
			default:
				// Handle other keys if needed
				break;
		}
	}

	public void handleKeyReleased(KeyEvent event) {
		scene.mario.setWalke(false);
		scene.setDx(0); // immobilization of background
	}
}