package com.mathmaurer.jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mathmaurer.audio.Audio;

public class Clavier implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		//Objet.DOWNALL();
		// on va l'executer tanque mario est en vie
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
			Main.scene.mario.setWalke(true);
			Main.scene.mario.setToRight(true);
			Main.scene.setDx(1);// displacement to right of background
		} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
			Main.scene.mario.setWalke(true);
			Main.scene.mario.setToRight(false);
			Main.scene.setDx(-1);// displacement to left of background
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Main.scene.mario.setJump(true);
//			Main.scene.mario.setIsOnObjet(false);
			Audio.playSong("/audio/saut.wav");	
		}
		//Objet.UPALL();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// if(Main.scene.mario.isLiving() == true) {
		//Objet.DOWNALL();
		Main.scene.mario.setWalke(false);
		Main.scene.setDx(0);// immobilization of background
		// }
		//Objet.UPALL();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
