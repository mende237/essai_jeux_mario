package com.mathmaurer.personnage;

import java.awt.Image;

import com.mathmaurer.objet.Objet;
import com.mathmaurer.utilitaire.Axe;

public abstract class Character {

	private int height, width;// the height and the width of a character
	private int x, y; // the position of character
	private boolean walke; // verify if the character is immobile or not
	private boolean toRight; // is true when the character is turned in right position
	private int counter; // the displacement frequency
	private boolean living;

		
	public Character(int x, int y , int width , int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.toRight = false;
		this.walke = false;
	}
	
	

//**************************************getter******************************************//
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isWalke() {
		return walke;
	}

	public boolean isToRight() {
		return toRight;
	}

	public int getCounter() {
		return counter;
	}

	public void setLiving(boolean living) {
		this.living = living;
	}

	// **************************************setter****************************************//
	public boolean isLiving() {
		return living;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setToRight(boolean toRight) {
		this.toRight = toRight;
	}

	public void setWalke(boolean walke) {
		this.walke = walke;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// *************************************methods****************************************//
	protected abstract Image walk(String name, int frequency);
	
//	protected abstract void kill(Mario mario);

	public abstract Image die();

	public boolean near(Objet objet) {
		if (this.getX() > objet.getX() + objet.getWidth() 
			|| this.getX() + this.getWidth() < objet.getX()
			|| this.getY() > objet.getY() + objet.getHeight() 
			|| this.getY() + this.getHeight() < objet.getY()) {
			return false;
		}
		return true;
	}

	public boolean near(Character personnage) {
		if (personnage.getX() > this.getX() + this.getWidth()
				|| personnage.getX() + personnage.getWidth() < this.getX()
				|| personnage.getY() > this.getY() + this.getHeight()
				|| personnage.getY() + personnage.getHeight() < this.getY()) {
			return false;
		}
		return true;
	}

	public boolean near(Character personnage, Axe axe, int doubt) {
		switch (axe) {
		case HORIZONTAL:
			if (personnage.getX() > this.getX() + this.getWidth() + doubt
					|| personnage.getX() + personnage.getWidth() < this.getX() - doubt
					|| personnage.getY() > this.getY() + this.getHeight()
					|| personnage.getY() + personnage.getHeight() < this.getY()) {
				return false;
			}
			return true;
		case VERTICAL:
			if (personnage.getX() > this.getX() + this.getWidth()
					|| personnage.getX() + personnage.getWidth() < this.getX()
					|| personnage.getY() > this.getY() + this.getHeight() + doubt
					|| personnage.getY() + personnage.getHeight() < this.getY() - doubt) {
				return false;
			}
			return true;
		default:
			if (personnage.getX() > this.getX() + this.getWidth() + doubt
					|| personnage.getX() + personnage.getWidth() < this.getX() - doubt
					|| personnage.getY() > this.getY() + this.getHeight() + doubt
					|| personnage.getY() + personnage.getHeight() < this.getY() - doubt) {
				return false;
			}
			return true;
		}

	}

	public boolean frontCollision(Objet objet) {
		if (this.x + this.width < objet.getX() || this.x + this.width > objet.getX()
				|| this.y + this.height <= objet.getY() || this.y >= objet.getY() + objet.getHeight()) {
			return false;
		} else {
			// System.out.println("front collision");
			return true;
		}
	}

	public boolean backCollision(Objet objet) {
		if (this.x > objet.getX() + objet.getWidth() || this.x < objet.getX() + objet.getWidth()
				|| this.y + this.height <= objet.getY() || this.y >= objet.getY() + objet.getHeight()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean bottomCollision(Objet objet) {
		if (this.getX() >= objet.getX() + objet.getWidth() || this.getX() + this.getWidth() <= objet.getX()
				|| this.getY() + this.getHeight() >= objet.getY() + objet.getHeight()
				|| this.getY() + this.getHeight() <= objet.getY()) {
			return false;
		}
		// System.out.println("bottom collision");
		return true;
	}

	public boolean topCollision(Objet objet) {
		if (this.getX() >= objet.getX() + objet.getWidth() || this.getX() + this.getWidth() <= objet.getX()
				|| this.getY() >= objet.getY() + objet.getHeight() || this.getY() <= objet.getY()) {
			return false;
		}
		return true;

	}

	// collision avec un personnage
	public boolean frontCollision(Character personnage) {
		if (this.x + this.width < personnage.getX() - 1
				|| this.x + this.width > personnage.getX() + 1
				|| this.y + this.height <= personnage.getY() 
				|| this.y >= personnage.getY() + personnage.getHeight()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean backCollision(Character personnage) {
		if (this.x > personnage.getX() + personnage.getWidth() + 1
				|| this.x < personnage.getX() + personnage.getWidth() - 1
				|| this.y + this.height <= personnage.getY()
				|| this.y >= personnage.getY() + personnage.getHeight()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean bottomCollision(Character personnage) {
		if (this.getX() > personnage.getX() + personnage.getWidth()
				|| this.getX() + this.getWidth() < personnage.getX()
				|| this.getY() + this.getHeight() > personnage.getY() + personnage.getHeight()
				|| this.getY() + this.getHeight() < personnage.getY()) {
			return false;
		}
		return true;
	}

	public boolean topCollision(Character personnage) {
		if (this.getX() > personnage.getX() + personnage.getWidth()
				|| this.getX() + this.getWidth() < personnage.getX()
				|| this.getY() > personnage.getY() + personnage.getHeight()
				|| this.getY() < personnage.getY()) {
			return false;
		}
		return true;

	}

}
