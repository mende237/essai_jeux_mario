package com.mathmaurer.objet;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathmaurer.jeu.Main;

public class Objet {
/****************************************property*********************************************/
	private int height , width;
	private int x , y;
	protected Image imgObjet;
	protected ImageIcon icoObjet;
	protected int sem;
	protected static int globalSem = 1;
/***************************************constructor********************************************/	
	public Objet(int x , int y , int height , int width) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
/*************************************getter****************************************************/
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
/***********************************setter******************************************************/
	public void setWidth(int width) {
		this.width = width;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	public void displacement() {
		if(Main.scene.getxPos() != -1)
			this.x = this.x - Main.scene.getDx();
	}
/*******************************************methods********************************************/	
	public void DOWN() {
		while(this.sem - 1 < 0) {
			
		}
		this.sem = this.sem - 1;
	}
	
	public static int DOWNALL() {
		if(Objet.globalSem > 0) {
			Objet.globalSem = Objet.globalSem - 1;
			return Objet.globalSem;
		}else
			return -1;		
	}
	public void UP() {
		this.sem = 1;
	}
	
	public static void UPALL() {
		Objet.globalSem = 1;
	}
}
