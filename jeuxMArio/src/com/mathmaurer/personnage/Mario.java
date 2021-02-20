package com.mathmaurer.personnage;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.jeu.Main;
import com.mathmaurer.objet.Objet;
import com.mathmaurer.objet.Piece;

public class Mario extends Character {
//****************************************property**********************************//
	private ImageIcon icoMario;
	private Image imgMario;

	private boolean jump;// is true if mario is jumping
	private boolean isOnObjet;
	private int yObjetCollision;
	private int counter;// determine the duration and height of jump
	private int dieCounter; // permet de soulever mario lorsqu'il est mort
	private boolean fall; // is true when mario is jumping
	private static int numberOfLive;
	private static int score;
	private static boolean isLiving = true;

//********************************constructor******************************************//
	public Mario(int x, int y) {
		super(x, y, 28, 50);
		this.icoMario = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
		this.imgMario = this.icoMario.getImage();
		super.setLiving(true);
		this.jump = false;
		this.counter = 0;
		this.dieCounter = 0;
		this.fall = false;
		Mario.numberOfLive = 3;
		Mario.score = 0;
	}

//*********************************************getter*******************************//
	public boolean isJump() {
		return jump;
	}

	public Image getImageMario() {
		return this.imgMario;
	}

	public boolean isOnObjet() {
		return this.isOnObjet;
	}

	public int getYObjetCollision() {
		return this.yObjetCollision;
	}

	public boolean isFall() {
		return this.fall;
	}

	public int getScore() {
		return score;
	}

	public int getNumberOfLive() {
		return numberOfLive;
	}

	@Override
	public boolean isLiving() {
		return isLiving;
	}

//*******************************************setter************************************//
	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void setIsOnObjet(boolean onObjet) {
		this.isOnObjet = onObjet;
	}

	public void setNumberOfLive(int numberOfLive) {
		Mario.numberOfLive = numberOfLive;
	}

	public void setScore(int score) {
		Mario.score = score;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	@Override
	public void setLiving(boolean isLiving) {
		Mario.isLiving = isLiving;
	}

//********************************************method************************************//
	public Image jump(int begin) {
		ImageIcon ico;
		Image img;
		String str;
		// System.out.println(begin);
		if (super.isLiving() == true) {
			// monter du saut
			if (this.counter <= 10) {
				if (super.getY() > Main.scene.getHeightRoof() && begin - super.getHeight() - super.getY() <= 130) {
					super.setY(this.getY() - 4);
				} else {
					// System.out.println("la hauteur max est " + this.getY());
					this.counter = 11;
				}

				if (super.isToRight() == true)
					str = "/images/marioSautDroite.png";
				else
					str = "/images/marioSautGauche.png";
				// descente du saut

			} else if (super.getY() + super.getHeight() < Main.scene.getYFloor()) {

				super.setY(super.getY() + 1);

				if (super.isToRight() == true)
					str = "/images/marioSautDroite.png";
				else
					str = "/images/marioSautGauche.png";
				// end of jump
			} else {

				if (super.isToRight() == true)
					str = "/images/marioArretDroite.png";
				else
					str = "/images/marioArretGauche.png";
				this.jump = false;
				this.counter = 0;
			}
		} else {
			str = "/images/marioSautDroite.png";
		}
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}

	public Image fall(int begin) {
		ImageIcon ico;
		Image img;
		String str;
		if (super.isLiving() == true) {
			// descente
			if (super.getY() + super.getHeight() < Main.scene.getYFloor()) {
				super.setY(super.getY() + 1);

				if (super.isToRight() == true)
					str = "/images/marioSautDroite.png";
				else
					str = "/images/marioSautGauche.png";
				// fin de la descente
			} else {
				if (super.isToRight() == true)
					str = "/images/marioArretDroite.png";
				else
					str = "/images/marioArretGauche.png";
				this.fall = false;
			}
		} else {
			str = "/images/marioSautDroite.png";
		}
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;

	}

	public void contact(Objet objet, boolean merge) {
		// horizontal contact
		if ((super.frontCollision(objet) == true && this.isToRight() == true)
				|| (super.backCollision(objet) == true && this.isToRight() == false) && merge == false) {
			this.setWalke(false);
			Main.scene.setDx(0);
		}

		// bottom contact
		if (super.bottomCollision(objet) == true) {// mario jump on an object
			Main.scene.setYFloor(objet.getY());
			this.isOnObjet = true;
			this.yObjetCollision = objet.getY();
		} else if (super.bottomCollision(objet) == false && merge == false) {// mario fall on the initial floor
			Main.scene.setYFloor(293);// altitude initiale
			if (this.jump == false)
				this.fall = true;

			this.isOnObjet = false;
		}

		// top contact
		if (super.topCollision(objet) == true) {
			Main.scene.setHeightRoof(objet.getHeight() + objet.getY());// roof become bottom of object
		} else if (super.topCollision(objet) == false && this.jump == false)
			Main.scene.setHeightRoof(0);// altitude initiale du plafond (ciel)
	}

	@Override
	public Image walk(String name, int frequency) {
		String str;
		ImageIcon ico;
		Image img;

		if (super.isWalke() == false || Main.scene.getxPos() <= 0 || Main.scene.getxPos() >= 4100) {
			if (super.isToRight() == true)
				str = "/images/" + name + "ArretDroite.png";
			else
				str = "/images/" + name + "ArretGauche.png";
		} else {
			super.setCounter(super.getCounter() + 1);
			;
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

	public boolean contactPiece(Piece piece) {
		if (this.near(piece) == true) {
			return true;
		} else
			return false;
	}
	
	@Override
	public Image die() {
		if (this.dieCounter < 300) {
			super.setY(super.getY() - 1);
			this.dieCounter++;
		}

		ImageIcon icoMarioDie = new ImageIcon(getClass().getResource("/images/marioMeurt.png"));
		Image imageMarioDie = icoMarioDie.getImage();

		return imageMarioDie;
	}
}
