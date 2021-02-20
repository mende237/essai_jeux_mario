package com.mathmaurer.jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.mathmaurer.objet.Bloc;
import com.mathmaurer.objet.Piece;
import com.mathmaurer.objet.Tuyau;
import com.mathmaurer.personnage.Champignon;
import com.mathmaurer.personnage.Mario;
import com.mathmaurer.personnage.Tortue;
import com.mathmaurer.utilitaire.Contact;

public class FirstStage extends Scene {
	private Image imgFond1;
	private Image imgFond2;
	private Image imgDepart;
	private Image imgChateau1;

	private Image imgChateauFin;
	private Image imgDrapeau;
	private Image pieceImage;

	private ImageIcon icoFond;
	private ImageIcon icoDepart;
	private ImageIcon icoChateau1;
	private ImageIcon icoChateauFin;
	private ImageIcon icoDrapeau;

	private ArrayList<Bloc> blocTab;
	private ArrayList<Tuyau> tuyauTab;
	private ArrayList<Piece> pieceTab;
	private ArrayList<Champignon> champTab;
	private ArrayList<Tortue> tortueTab;

	private Font font;
	private Champignon champignon1;
	private Champignon champignon2;
	private Champignon champignon3;
	private Champignon champignon4;
	private Champignon champignon5;
	private Champignon champignon6;

	private Tortue tortue1;
	private Tortue tortue2;
	private Tortue tortue3;
	private Tortue tortue4;
	private Tortue tortue5;
	private Tortue tortue6;

	private int xFond1;
	private int xFond2;

//*************************************constructor***************************************//
	public FirstStage() {

		super(293, 0, 0);
		this.xFond1 = -50;
		this.xFond2 = 750;
		mario = new Mario(300, super.getYFloor() - 50);

		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new File("src/police/SuperMario256.ttf"));
			this.font = this.font.deriveFont(20.f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pieceImage = (new ImageIcon(getClass().getResource("/images/piece1.png"))).getImage();

		icoFond = new ImageIcon(getClass().getResource("/images/fondEcran.png"));
		this.imgFond1 = this.icoFond.getImage();
		this.imgFond2 = this.icoFond.getImage();

		this.icoChateau1 = new ImageIcon(getClass().getResource("/images/chateau1.png"));
		this.imgChateau1 = this.icoChateau1.getImage();

		this.icoDepart = new ImageIcon(getClass().getResource("/images/depart.png"));
		this.imgDepart = this.icoDepart.getImage();

		this.icoChateauFin = new ImageIcon(getClass().getResource("/images/chateauFin.png"));
		this.imgChateauFin = this.icoChateauFin.getImage();

		this.icoDrapeau = new ImageIcon(getClass().getResource("/images/drapeau.png"));
		this.imgDrapeau = this.icoDrapeau.getImage();
//
		blocTab = new ArrayList<Bloc>();
		tuyauTab = new ArrayList<>();
		pieceTab = new ArrayList<Piece>();
		champTab = new ArrayList<Champignon>();
		tortueTab = new ArrayList<Tortue>();

		int pas = 0;
		for (int i = 0; i < 7; i++) {
			this.tuyauTab.add(new Tuyau(600 + pas, 228));
			pas = pas + 500;
		}
		pas = 0;
		for (int i = 0; i < 4; i++) {
			this.blocTab.add(new Bloc(800 + pas, 180));
			pas = pas + 30;
		}
		
		pas = 0;
		for (int i = 4; i < 8; i++) {
			this.blocTab.add(new Bloc(1700 + pas, 180));
			pas = pas + 30;
		}

		for (int i = 8; i < 11; i++) {
			this.blocTab.add(new Bloc(1800 + pas, 140));
			pas = pas + 30;
		}

		this.blocTab.add(new Bloc(2200, 180));
		this.blocTab.add(new Bloc(2300, 140));
		this.blocTab.add(new Bloc(2400, 180));

		this.blocTab.add(new Bloc(3200, 180));
		this.blocTab.add(new Bloc(3300, 140));
		this.blocTab.add(new Bloc(3400, 180));

		pas = 0;
		for (int i = 0; i < 4; i++) {
			this.pieceTab.add(new Piece(800 + pas, 145));
			pas += 30;
		}

		pas = 0;
		for (int i = 0; i < 4; i++) {
			this.pieceTab.add(new Piece(1700 + pas, 145));
			pas += 30;
		}

		pas = 0;
		for (int i = 0; i < 3; i++) {
			this.pieceTab.add(new Piece(1910 + pas, 100));
			pas += 40;
		}

		this.pieceTab.add(new Piece(2200, 150));
		this.pieceTab.add(new Piece(2300, 110));
		this.pieceTab.add(new Piece(2400, 150));

		this.pieceTab.add(new Piece(3200, 150));
		this.pieceTab.add(new Piece(3300, 110));
		this.pieceTab.add(new Piece(3400, 150));

		champignon1 = new Champignon(800, 263);
//		champignon2 = new Champignon(800, 263);
//		champignon3 = new Champignon(1500, 263);
//		champignon4 = new Champignon(3000, 263);
//		champignon5 = new Champignon(3200, 263);
//		champignon6 = new Champignon(3500, 263);

		tortue1 = new Tortue(700 , 243);
//		tortue2 = new Tortue(2000, 243);
//		tortue3 = new Tortue(1900, 243);
//		tortue4 = new Tortue(2500, 243);
//		tortue5 = new Tortue(2900, 243);
//		tortue6 = new Tortue(3300, 243);

		this.tortueTab.add(tortue1);
//		this.tortueTab.add(tortue2);
//		this.tortueTab.add(tortue3);
//		this.tortueTab.add(tortue4);
//		this.tortueTab.add(tortue5);
//		this.tortueTab.add(tortue6);

		this.champTab.add(champignon1);
//		this.champTab.add(champignon2);
//		this.champTab.add(champignon3);
//		this.champTab.add(champignon4);
//		this.champTab.add(champignon5);
//		this.champTab.add(champignon6);

//		//int pas = 0;
//		for (int i = 0; i < 500; i++) {
//			this.champTab.add(new Champignon(700 + pas, 260));
//			pas += 40;
//		}

		Thread screenChrono = new Thread(new DisplayFrequence());
		Thread chrono = new Thread(new Chrono());
		chrono.start();
		screenChrono.start();
	}

//****************************************getter*****************************//
	public ArrayList<Bloc> getBlocTab() {
		return blocTab;
	}

	public ArrayList<Champignon> getChampTab() {
		return champTab;
	}

	public ArrayList<Tortue> getTortueTab() {
		return tortueTab;
	}

	// ****************************************setter*******************************//
	public void setxFond1(int xFond1) {
		this.xFond1 = xFond1;
	}

	public void setxFond2(int xFond2) {
		this.xFond2 = xFond2;
	}

//****************************************methods********************************//
	public void backgroundDisplacement() {
		if (super.getxPos() >= 0 && super.getxPos() <= 4100) {
			super.setxPos(super.getxPos() + super.getDx());
			this.xFond1 = this.xFond1 - super.getDx();
			this.xFond2 = this.xFond2 - super.getDx();
		} else if (this.getxPos() >= 4100 && this.mario.isToRight() == false) {
			super.setxPos(super.getxPos() + super.getDx());
			this.xFond1 = this.xFond1 - super.getDx();
			this.xFond2 = this.xFond2 - super.getDx();
		} else if (super.getxPos() <= 0 && super.mario.isToRight() == true) {
			super.setxPos(super.getxPos() + super.getDx());
			this.xFond1 = this.xFond1 - super.getDx();
			this.xFond2 = this.xFond2 - super.getDx();
		}

		if (this.xFond1 == -800)
			this.xFond1 = 800;
		else if (this.xFond2 == -800)
			this.xFond2 = 800;
		else if (this.xFond1 == 800)
			this.xFond1 = -800;
		else if (this.xFond2 == 800)
			this.xFond2 = -800;
	}

	@Override
	public void paintComponent(Graphics g) {// repaint the background each 3ms
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;
		g2.setFont(this.font);
		g2.setColor(Color.WHITE);
		// System.out.println(pieceTab.get(0).getX());
		/*
		 * ici on gere la collision de chaque champignon du tableau de champignon avec
		 * chaque tortue du tableau de tortue . et meme avec mario
		 */
		if (GameManagement.isInterupt() == true) {
			
			g2 = Transition.transition(this , g2, GameManagement.getState());
			GameManagement.setInterupt(false);
			System.out.println("affichage de la transition");
			
		} else {
			if (Main.scene.mario.isLiving() == false && Main.scene.mario.getNumberOfLive() > 0) {
				// System.out.println("enter");
				Main.scene.restart(Main.scene.getxPos());
				Main.scene.mario.setIsOnObjet(false);
				Main.scene.mario.setWalke(false);
	
			}
			//on supprime tous les caractères qui sont deja mort
			Contact.updateTab(champTab);
			Contact.updateTab(tortueTab);
			
			Contact.piece(this.pieceTab, this.mario);
//			Contact.mario(this.tuyauTab, 43, this.mario);
//			Contact.mario(this.blocTab, 30, this.mario);
			
			if(Contact.mario(this.tuyauTab, 43, this.mario) == false 
					&& Contact.mario(this.blocTab, 30, this.mario) == false) {
				Main.scene.setHeightRoof(0);
				Main.scene.setYFloor(293);
			}
			
//
//			Contact.tortue(this.tortueTab, this.tuyauTab);
//			// collision detection from mario with other objects of ours game
//			GameManagement.setBegin(true);
			
			// displacement of fixed objects of ours games

//			if(GameManagement.DOWN() >= 0) {
				this.backgroundDisplacement();
				if (super.getxPos() >= 0 && super.getxPos() <= 4650) {
					
					for (int i = 0; i < this.blocTab.size(); i++) {
						this.blocTab.get(i).displacement();
					}
					
					for (int i = 0; i < this.tuyauTab.size(); i++) {
						this.tuyauTab.get(i).displacement();
					}
					
					for (int i = 0; i < this.pieceTab.size(); i++) {
						this.pieceTab.get(i).displacement();
					}
					
					for (int i = 0; i < this.champTab.size(); i++) {
						this.champTab.get(i).displacement();
					}
					
					for (int i = 0; i < this.tortueTab.size(); i++) {
						this.tortueTab.get(i).displacement();
					}
				}
//				GameManagement.UP();
//			}else {
//				//System.out.println("enter displement");
//			}
			Contact.chamignon(this.champTab , this.tortueTab , this.tuyauTab);
			Contact.tortue(this.tortueTab , this.champTab , this.tuyauTab);
			GameManagement.setBegin(true);

			// ***********************paint of backgrounds' game***********************//
			g2.drawImage(this.imgFond1, this.xFond1, 0, null);
			g2.drawImage(this.imgFond2, this.xFond2, 0, null);

			// ------paint of chateau
			g2.drawImage(this.imgChateau1, 10 - super.getxPos(), 95, null);

			// ------paint of start arrow
			g2.drawImage(this.imgDepart, 220 - super.getxPos(), 234, null);

			// -----paint objet
			for (int i = 0; i < blocTab.size(); i++) {
				g2.drawImage(this.blocTab.get(i).getImgObjet(), this.blocTab.get(i).getX(), this.blocTab.get(i).getY(),
						null);
			}

			for (int i = 0; i < tuyauTab.size(); i++) {
				g2.drawImage(this.tuyauTab.get(i).getImgObjet(), this.tuyauTab.get(i).getX(),
						this.tuyauTab.get(i).getY(), null);
			}

			//
			Piece.startAlternation();
			for (int i = 0; i < pieceTab.size(); i++) {
				g2.drawImage(this.pieceTab.get(i).getImagePiece(), this.pieceTab.get(i).getX(),
						this.pieceTab.get(i).getY(), null);
			}

			// -----paint flag
			g2.drawImage(imgDrapeau, 4000 - super.getxPos(), 115, null);
			// -----paint of chateau end
			g2.drawImage(imgChateauFin, 4100 - super.getxPos(), 145, null);
			
			//print of all champignons of the game
			for (int i = 0; i < this.champTab.size(); i++) {
				if (this.champTab.get(i).isLiving() == true) {// on verifie si le champignon est encore envie
					// avant d'afficher
					g2.drawImage(this.champTab.get(i).walk("champ", 50), this.champTab.get(i).getX(),
							this.champTab.get(i).getY(), null);
				} else {

					g2.drawImage(champTab.get(i).die(), this.champTab.get(i).getX(), 282, null);
				}
			}
			
			//print of all turtles of the scene
			for (int i = 0; i < tortueTab.size(); i++) {
				if (tortueTab.get(i).isLiving() == true) {
					g2.drawImage(this.tortueTab.get(i).walk("tortue", 100), this.tortueTab.get(i).getX(),
							this.tortueTab.get(i).getY(), null);
				} else {
					g2.drawImage(tortueTab.get(i).die(), this.tortueTab.get(i).getX(), (293 - this.tortueTab.get(i).getHeight()) , null);
				}
			}

			// **************************add of mario in scene's
			// game*************************//
			if (super.mario.getNumberOfLive() > 0) {
				if (super.mario.isJump() == true && super.mario.isFall() == false) {
					if (super.mario.isOnObjet() == false) {
						// --------------when mario jumps to suffer from an object
						g2.drawImage(super.mario.jump(293), super.mario.getX(), super.mario.getY(), null);

					} else {
						// --------------when mario jumps to suffer from the floor 
						g2.drawImage(super.mario.jump(super.mario.getYObjetCollision()), super.mario.getX(),
								super.mario.getY(), null);
					}

				} else if (super.mario.isFall() == true) {
					g2.drawImage(super.mario.fall(super.mario.getYObjetCollision()), super.mario.getX(),
							super.mario.getY(), null);

				} else {
					// ---------------when he walk or immobilize
					g2.drawImage(super.mario.walk("mario", 50), super.mario.getX(), super.mario.getY(), null);
				} 

			} else {
				//when mario is dead 
				g2.drawImage(super.mario.die(), super.mario.getX(), super.mario.getY(), null);
			}

			/* printing of score */
			g2.setFont(this.font);
			g2.setColor(Color.WHITE);
			g2.drawString("score " + this.mario.getScore(), 650, 33);
			g2.drawImage(this.pieceImage, 750, 10, null);

			/* printing of time */
			g2.setFont(this.font);
			g2.setColor(Color.WHITE);
			g2.drawString("time Elapsed " + Chrono.getTimeElapse(), 0, 33);
		}
		
	}

	@Override
	//this function puts all the object of the scene in their initial position 
	public void restart(int position) {
		this.xFond1 = -50;
		this.xFond2 = 750;
		super.mario.setX(300);
		super.mario.setWalke(false);
		super.mario.setLiving(true);

		for (int i = 0; i < this.blocTab.size(); i++) {
			this.blocTab.get(i).setX(this.blocTab.get(i).getX() + position);
		}

		for (int i = 0; i < this.tuyauTab.size(); i++) {
			this.tuyauTab.get(i).setX(this.tuyauTab.get(i).getX() + position);
		}

		for (int i = 0; i < this.pieceTab.size(); i++) {
			this.pieceTab.get(i).setX(this.pieceTab.get(i).getX() + position);
		}

		for (int i = 0; i < this.champTab.size(); i++) {
			this.champTab.get(i).setX(this.champTab.get(i).getX() + position);
		}

		for (int i = 0; i < this.tortueTab.size(); i++) {
			this.tortueTab.get(i).setX(this.tortueTab.get(i).getX() + position);
		}

		super.setxPos(0);
	}
}
