package com.mathmaurer.personnage;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.jeu.GameManagement;
import com.mathmaurer.jeu.Main;
import com.mathmaurer.objet.Objet;
import com.mathmaurer.utilitaire.TransitionState;

public class Champignon extends Cheban implements Runnable {
	/***************************************
	 * property
	 *******************************************/
	private ImageIcon icoChamp;
	private Image imageChamp;

	private int dxChamp;
	private final int PAUSE = 20;// the duration of break
		
	/**************************************
	 * constructor
	 *****************************************/
	public Champignon(int x, int y) {
		super(x, y, 27, 30);
		super.nbreOfLive = 1;
		super.setToRight(true);
		super.setWalke(true);

		this.icoChamp = new ImageIcon(getClass().getResource("/images/champArretDroite.png"));
		this.imageChamp = icoChamp.getImage();
		super.setLiving(true);
		super.setThread(new Thread(this));	
		super.getThread().start();
	}

	/*********************************
	 * getter
	 *****************************************/
	public Image getImageChamp() {
		return imageChamp;
	}

	/****************************************
	 * setter
	 **********************************/
	
	/***************************************
	 * methods
	 **************************************/
	@Override
	public void run() {
		// on attend 20ms que tous les objets de la scene s'affiche
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			if(super.isLiving() == true)
				this.move();
			else
				super.getThread().stop();
			
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void setImageChamp(Image imageChamp) {
		this.imageChamp = imageChamp;
	}
	
	@Override
	public void displacement() {
		if (Main.scene.getxPos() != -1) {
			super.setX(super.getX() - Main.scene.getDx());
			kill(Main.scene.mario);
		}
	}

	public void move() {
		if(GameManagement.isBegin() == true) {
			//System.out.println("min " + super.getZoneMin() + "  max " + super.zoneMax);
			if(GameManagement.DOWN() >= 0) {
				//on verifie si mario tue le champignon ou c'est le champignon qui tu mario
				int zomeMin = super.getZoneMin(super.behindCharacter, super.behindObjet);
				int zoneMax = super.getZoneMax(super.frontCharacter, super.frontObjet);
				
				if (super.isWalke() == true && super.isLiving() == true) {
					if(super.getX() + super.getWidth() < zoneMax && super.getX() > zomeMin) {
						if (super.isToRight() == true) {
							this.dxChamp = 1;
							super.setX(super.getX() + this.dxChamp);
						} else {
							this.dxChamp = -1;
							super.setX(super.getX() + this.dxChamp);
						}					
					}
					
					if(super.getX() <= zomeMin) {
						if(this.characterDirectlyBehind == true && this.behindCharacter.zombie == true) {
							this.setLiving(false);
							super.behindCharacter.setLiving(false);
							super.remove = true;
							super.behindCharacter.remove = true;
							if(super.frontCharacter != null && super.behindCharacter.behindCharacter != null) {
								super.frontCharacter.behindCharacter = super.behindCharacter.behindCharacter;
								super.behindCharacter.behindCharacter.frontCharacter =  super.frontCharacter;
							}	
						}
						
						
						
						super.setToRight(true);
						this.dxChamp = 1;
						super.setX(super.getX() + this.dxChamp);
						//super.setWalke(false);
					}
					
					if(super.getX() + super.getWidth() >= zoneMax){
						if(this.characterDirectlyFront == true && this.frontCharacter.zombie == true) {
							this.setLiving(false);
							super.frontCharacter.setLiving(false);
							super.remove = true;
							super.frontCharacter.remove = true;
							if(super.behindCharacter != null && super.frontCharacter.frontCharacter != null) {
								super.behindCharacter.frontCharacter = super.frontCharacter.frontCharacter;	
								super.frontCharacter.frontCharacter.behindCharacter = super.behindCharacter;
							}	
						}
						
						super.setToRight(false);
						this.dxChamp = -1;
						super.setX(super.getX() + this.dxChamp);
						
					}
				}
				
				this.kill(Main.scene.mario);
//				System.out.println("live");
				if(super.isLiving() == false) {
					System.out.println("champignon tuer");
					super.remove = true;
				}
				
				GameManagement.UP();
			}else
				;//System.out.println("champignon bloqué");
		}

	}


	@Override
	public Image walk(String name, int frequency) {
		String str;
		ImageIcon ico;
		Image img;

		if (super.isWalke() == false) {
			if (super.isToRight() == true)
				str = "/images/" + name + "ArretDroite.png";
			else
				str = "/images/" + name + "ArretGauche.png";
		} else {
			super.setCounter(super.getCounter() + 1);
			if (super.getCounter() / frequency == 0) {
				if (super.isToRight() == true)
					str = "/images/" + name + "ArretDroite.png";
				else
					str = "/images/" + name + "ArretGauche.png";
			} else {
				if (super.isToRight() == true)
					str = "/images/" + name + "MarcheDroite.png";
				else
					str = "/images/" + name + "MarcheGauche.png";
			}
			if (super.getCounter() == 2 * frequency)
				super.setCounter(0);
		}
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}


	public void kill(Mario mario) {
		if (mario.near(this) == true && super.isLiving() == true && mario.isLiving() == true) {
			// dans ce cas c'est mario qui tu le champignon
			if (mario.bottomCollision(this) == true && (mario.isJump() == true || mario.isFall() == true)) {
				// System.out.println("tuer champ");
				super.setLiving(false);
				super.remove = true;
				//Audio.playSong("/audio/ecrasePersonnage.wav");
				// System.out.println("mario mort");
			} else {
				// System.out.println("tuer mario");
				mario.setNumberOfLive(mario.getNumberOfLive() - 1);
				mario.setLiving(false);
				//Audio.playSong("/audio/game-over.wav");
				GameManagement.setInterupt(true);
				GameManagement.setState(TransitionState.DIE);
				if(mario.getNumberOfLive() <= 0) {
					GameManagement.setState(TransitionState.GAMEOVER);
				}
			}
		}
	}

	@Override
	public Image die() {
		String str;
		if (super.isToRight() == true) {
			str = "champEcraseDroite.png";
		} else {
			str = "champEcraseGauche.png";
		}
		ImageIcon icoChampMort = new ImageIcon(getClass().getResource("/images/" + str));
		Image imgChamp = icoChampMort.getImage();

		return imgChamp;
	}

}
