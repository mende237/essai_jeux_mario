package com.mathmaurer.objet;

import javax.swing.ImageIcon;

public class Tuyau extends Objet{
	
	public Tuyau(int x, int y) {
		super(x, y, 65 , 43);
		super.icoObjet = new ImageIcon(getClass().getResource("/images/tuyauRouge.png"));
		super.imgObjet = icoObjet.getImage();
	}
	
}
