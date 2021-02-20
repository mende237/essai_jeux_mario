package com.mathmaurer.objet;

import javax.swing.ImageIcon;

public class Bloc extends Objet{

	
	public Bloc(int x , int y) {
		super(x, y, 30 , 30);
		super.icoObjet = new ImageIcon(getClass().getResource("/images/bloc.png"));
		super.imgObjet = icoObjet.getImage();
	}
	
}
