package com.mathmaurer.jeu;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.mathmaurer.personnage.Mario;

@SuppressWarnings("serial")
public abstract class Scene extends JPanel {
//***************************property***********************************//
	public Mario mario;


	private int yFloor;
	private int heightRoof;
	private int dx = 0;
	private int xPos;

//*************************************constructor***************************************//
	public Scene(int yFloor , int heightRoof , int xPos) {
		this.xPos = xPos;
		this.yFloor = yFloor;
		this.heightRoof = heightRoof;
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(new Clavier());
		
	}
//****************************************getter*****************************//
	public int getxPos() {
		return xPos;
	}

	public int getDx() {
		return this.dx;
	}

	public int getYFloor() {
		return this.yFloor;
	}

	public int getHeightRoof() {
		return this.heightRoof;
	}


	// ****************************************setter*******************************//
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setDx(int x) {
		this.dx = x;
	}

	public void setYFloor(int yFloor) {
		this.yFloor = yFloor;
	}

	public void setHeightRoof(int heightRoof) {
		this.heightRoof = heightRoof;
	}

//****************************************methods********************************//
	public abstract void backgroundDisplacement();
	public abstract void restart(int position);
//	public abstract void paintComponent(Graphics g);
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
