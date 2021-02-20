package com.mathmaurer.objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece extends Objet{
/****************************************property*****************************************/
	private static int cmpt = 0;
//	private final int PAUSE = 15;
	private static Image imgPiece;
/****************************************constructor**************************************/	
	public Piece(int x, int y) {
		super(x, y, 30 , 30);
		super.icoObjet = new ImageIcon(getClass().getResource("/images/piece1.png"));
		Piece.imgPiece = icoObjet.getImage();
	}
/******************************************getters*****************************************/
	public Image getImagePiece() {
		return Piece.imgPiece;
	}

/******************************************methods*****************************************/
	public  static void startAlternation() {
		ImageIcon ico;
		String str;
		Piece.cmpt++;
		if(Piece.cmpt / 100 == 0)
			str = "src/images/piece1.png";
		else
			str = "src/images/piece2.png";
		if(Piece.cmpt == 200)
			Piece.cmpt = 0;
		
		ico = new ImageIcon(str);
		Piece.imgPiece = ico.getImage();
	}
	
//	@Override
//	public void run() {
//		try {
//			Thread.sleep(20);
//			System.out.println(true);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		while(true) {
//			this.pieceAlternation();
//			try {
//				Thread.sleep(PAUSE);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
}
