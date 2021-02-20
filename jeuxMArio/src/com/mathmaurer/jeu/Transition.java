package com.mathmaurer.jeu;

import java.awt.Color;
import java.awt.Graphics;

import com.mathmaurer.utilitaire.TransitionState;

public class Transition{
//	private Font font;
	
//	private TransitionState state;
	/*********************************constructor**********************************/
//	public Transition() {
//		try {
//			this.font = Font.createFont(Font.TRUETYPE_FONT,
//					new File("src/police/SuperMario256.ttf"));
//			this.font = this.font.deriveFont(20.f);
//		} catch (FontFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		this.setBackground(Color.black);
//	}
/**************************************getter*************************************/	
//	public TransitionState getState() {
//		return state;
//	}
/**************************************setter*************************************/
//	public void setState(TransitionState state) {
//		this.state = state;
//	}

/*************************************methods*************************************/	
	public static Graphics transition(Scene scene , Graphics g , TransitionState state) {
		scene.setBackground(Color.black);
		if(state == TransitionState.DIE) {
			//System.out.println("transition");
			/* we verifie if the is not game over */
			if (Main.scene.mario.isLiving() == false && Main.scene.mario.getNumberOfLive() > 0) {
				// System.out.println("enter");
				Main.scene.restart(Main.scene.getxPos());
				Main.scene.mario.setIsOnObjet(false);
				Main.scene.mario.setWalke(false);

			}
			g.drawString("live ".toUpperCase() + scene.mario.getNumberOfLive(), 350 ,175);	
		}
		
		return g;
	}

}
