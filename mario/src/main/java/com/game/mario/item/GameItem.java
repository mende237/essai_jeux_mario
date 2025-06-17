package com.game.mario.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.game.mario.App;

public class GameItem {
	/****************************************
	 * property
	 *********************************************/
	private int height, width;
	private int x, y;
	protected Image imgObjet;
	protected ImageView icoObjet;
	protected int sem;
	protected static int globalSem = 1;

	/***************************************
	 * constructor
	 ********************************************/
	public GameItem(int x, int y, int height, int width) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public GameItem(int x, int y, int height, int width, Image imgObjet) {
		this(x, y, height, width);
		icoObjet = new ImageView(imgObjet);
		this.imgObjet = imgObjet;
		icoObjet.setX(x);
		icoObjet.setY(y);
		icoObjet.setFitWidth(width);
		icoObjet.setFitHeight(height);
	}

	/*************************************
	 * getter
	 ****************************************************/
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public Image getImgObjet() {
		return imgObjet;
	}

	public void setImageObjet(Image img) {
		imgObjet = img;
	}

	/***********************************
	 * setter
	 ******************************************************/
	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	// public void displacement() {
	// if (App.scene.getxPos() != -1)
	// this.x = this.x - App.scene.getDx();
	// }

	public void displacement() {
		if (App.scene.getxPos() != -1)
			this.x = this.x - ((int) App.scene.getDx());
	}

	/*******************************************
	 * methods
	 ********************************************/
	public void DOWN() {
		while (this.sem - 1 < 0) {

		}
		this.sem = this.sem - 1;
	}

	public static int DOWNALL() {
		if (GameItem.globalSem > 0) {
			GameItem.globalSem = GameItem.globalSem - 1;
			return GameItem.globalSem;
		} else
			return -1;
	}

	public void UP() {
		this.sem = 1;
	}

	public static void UPALL() {
		GameItem.globalSem = 1;
	}
}
