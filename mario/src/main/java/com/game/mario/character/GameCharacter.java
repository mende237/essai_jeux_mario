package com.game.mario.character;

import com.game.mario.item.GameItem;
import com.game.mario.util.Axe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameCharacter {

	private int height, width;// the height and the width of a character
	private int x, y; // the position of character
	private boolean walke; // verify if the character is immobile or not
	private boolean toRight; // is true when the character is turned in right position
	private int counter; // the displacement frequency
	private boolean living;
	protected ImageView icoCharacter;
	protected Image imgCharacter;

	public GameCharacter(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.toRight = false;
		this.walke = false;
	}

	// public GameCharacter(int x, int y, int width, int height, Image imgCharacter)
	// {
	// this.x = x;
	// this.y = y;
	// this.height = height;
	// this.width = width;
	// this.toRight = false;
	// this.walke = false;
	// this.icoCharacter = new ImageView(imgCharacter);
	// this.icoCharacter.setX(x);
	// this.icoCharacter.setY(y);
	// this.icoCharacter.setFitWidth(width);
	// this.icoCharacter.setFitHeight(height);

	// }

	// **************************************getter******************************************//
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

	public Image getImgCharacter() {
		return imgCharacter;
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

	public void setImgCharacter(Image imgCharacter) {
		this.imgCharacter = imgCharacter;
		// if (icoCharacter != null) {
		// icoCharacter.setImage(imgCharacter);
		// }
	}

	// *************************************methods****************************************//
	protected abstract Image walk(String name, int frequency);

	// protected abstract void kill(Mario mario);

	public abstract Image die();

	public boolean near(GameItem gameItem) {
		if (this.getX() > gameItem.getX() + gameItem.getWidth()
				|| this.getX() + this.getWidth() < gameItem.getX()
				|| this.getY() > gameItem.getY() + gameItem.getHeight()
				|| this.getY() + this.getHeight() < gameItem.getY()) {
			return false;
		}
		return true;
	}

	public boolean near(GameCharacter personnage) {
		if (personnage.getX() > this.getX() + this.getWidth()
				|| personnage.getX() + personnage.getWidth() < this.getX()
				|| personnage.getY() > this.getY() + this.getHeight()
				|| personnage.getY() + personnage.getHeight() < this.getY()) {
			return false;
		}
		return true;
	}

	public boolean near(GameCharacter personnage, Axe axe, int doubt) {
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

	public boolean frontCollision(GameItem gameItem) {
		if (this.x + this.width < gameItem.getX() || this.x + this.width > gameItem.getX()
				|| this.y + this.height <= gameItem.getY() || this.y >= gameItem.getY() + gameItem.getHeight()) {
			return false;
		} else {
			// System.out.println("front collision");
			return true;
		}
	}

	public boolean backCollision(GameItem gameItem) {
		if (this.x > gameItem.getX() + gameItem.getWidth() || this.x < gameItem.getX() + gameItem.getWidth()
				|| this.y + this.height <= gameItem.getY() || this.y >= gameItem.getY() + gameItem.getHeight()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean bottomCollision(GameItem gameItem) {
		if (this.getX() >= gameItem.getX() + gameItem.getWidth() || this.getX() + this.getWidth() <= gameItem.getX()
				|| this.getY() + this.getHeight() >= gameItem.getY() + gameItem.getHeight()
				|| this.getY() + this.getHeight() <= gameItem.getY()) {
			return false;
		}
		// System.out.println("bottom collision");
		return true;
	}

	public boolean topCollision(GameItem gameItem) {
		if (this.getX() >= gameItem.getX() + gameItem.getWidth() || this.getX() + this.getWidth() <= gameItem.getX()
				|| this.getY() >= gameItem.getY() + gameItem.getHeight() || this.getY() <= gameItem.getY()) {
			return false;
		}
		return true;

	}

	// collision avec un personnage
	public boolean frontCollision(GameCharacter personnage) {
		if (this.x + this.width < personnage.getX() - 1
				|| this.x + this.width > personnage.getX() + 1
				|| this.y + this.height <= personnage.getY()
				|| this.y >= personnage.getY() + personnage.getHeight()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean backCollision(GameCharacter personnage) {
		if (this.x > personnage.getX() + personnage.getWidth() + 1
				|| this.x < personnage.getX() + personnage.getWidth() - 1
				|| this.y + this.height <= personnage.getY()
				|| this.y >= personnage.getY() + personnage.getHeight()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean bottomCollision(GameCharacter personnage) {
		if (this.getX() > personnage.getX() + personnage.getWidth()
				|| this.getX() + this.getWidth() < personnage.getX()
				|| this.getY() + this.getHeight() > personnage.getY() + personnage.getHeight()
				|| this.getY() + this.getHeight() < personnage.getY()) {
			return false;
		}
		return true;
	}

	public boolean topCollision(GameCharacter personnage) {
		if (this.getX() > personnage.getX() + personnage.getWidth()
				|| this.getX() + this.getWidth() < personnage.getX()
				|| this.getY() > personnage.getY() + personnage.getHeight()
				|| this.getY() < personnage.getY()) {
			return false;
		}
		return true;

	}

}
