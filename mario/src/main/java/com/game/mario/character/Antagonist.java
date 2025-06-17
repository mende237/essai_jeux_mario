package com.game.mario.character;

import com.game.mario.item.GameItem;
import com.game.mario.App;

public abstract class Antagonist extends GameCharacter {
	private int Dx;
	private Thread thread;
	protected int frontObjet = 0;
	protected int behindObjet = 5000;
	protected Antagonist frontCharacter = null;// contient le personnage qui est devant ce personnage;
	protected Antagonist behindCharacter = null;// contient le personnage qui est deriere ce personnage
	protected boolean zombie;
	protected boolean characterDirectlyFront = false;
	protected boolean characterDirectlyBehind = false;
	protected int nbreOfLive = 2;
	protected boolean remove = false;

	public Antagonist(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	/*********************************
	 * getter
	 ******************************************/
	public Thread getThread() {
		return thread;
	}

	public int getFrontObjet() {
		return this.frontObjet;
	}

	public int getbehindObjet() {
		return this.behindObjet;
	}

	public GameCharacter getFrontCharacter() {
		return this.frontCharacter;
	}

	public GameCharacter getBehindCharacter() {
		return this.behindCharacter;
	}

	public boolean isRemove() {
		return this.remove;
	}

	/***********************************
	 * setter
	 *************************************/
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public void setFrontObjet(int frontObjet) {
		this.frontObjet = frontObjet;
	}

	public void setBehindObjet(int behindObjet) {
		this.behindObjet = behindObjet;
	}

	public void setFrontCharacter(Antagonist frontCharater) {
		this.frontCharacter = frontCharater;
	}

	public void setBehindCharacter(Antagonist behindCharacter) {
		this.behindCharacter = behindCharacter;
	}

	/*************************************** methods ****************************/
	public void contact(GameItem gameItem) {
		// horizontal contact
		if (super.frontCollision(gameItem) == true && super.isToRight() == true) {
			super.setToRight(false);
		}

		if (super.backCollision(gameItem) == true && super.isToRight() == false) {
			super.setToRight(true);
		}
	}

	public void contact(GameCharacter personnage) {
		if (super.frontCollision(personnage) == true && super.isToRight() == true) {
			super.setToRight(false);
			this.Dx = -1;
		}

		if (super.backCollision(personnage) == true && super.isToRight() == false) {
			super.setToRight(true);
			this.Dx = 1;
		}
	}

	@Override
	public boolean near(GameItem gameItem) {
		if (super.getX() > gameItem.getX() + gameItem.getWidth() || super.getX() + super.getWidth() < gameItem.getX()
				|| super.getY() > gameItem.getY() + gameItem.getHeight()
				|| super.getY() + super.getHeight() < gameItem.getY()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean near(GameCharacter personnage) {
		if (personnage.getX() >= super.getX() + super.getWidth()
				|| personnage.getX() + personnage.getWidth() <= super.getX()
				|| personnage.getY() >= super.getY() + super.getHeight()
				|| personnage.getY() + personnage.getHeight() <= super.getY()) {
			return false;
		} else
			return true;

	}

	public void displacement() {
		if (App.scene.getxPos() != -1) {
			super.setX(super.getX() - App.scene.getDx());
			kill(App.scene.mario);
		}
	}

	/*
	 * this function select the between an object and a character the one that come
	 * come before other
	 */
	public int getZoneMax(Antagonist personnage, int maxObjet) {
		if (personnage != null && personnage.remove == false) {

			if (personnage.getX() <= maxObjet && personnage.remove == false) {
				this.characterDirectlyFront = true;
				return personnage.getX();
			} else
				return maxObjet;
		} else {
			this.characterDirectlyFront = false;
			return maxObjet;
		}
	}

	/*
	 * this function select the between an object and a character the one that come
	 * come after other
	 */
	public int getZoneMin(Antagonist personnage, int minObjet) {

		if (personnage != null && personnage.remove == false) {
			if (personnage.getX() + personnage.getWidth() >= minObjet) {
				this.characterDirectlyBehind = true;

				return personnage.getX() + personnage.getWidth();
			} else
				return minObjet;
		} else {
			this.characterDirectlyBehind = false;
			return minObjet;
		}
	}

	public abstract void kill(Mario mario);

}
