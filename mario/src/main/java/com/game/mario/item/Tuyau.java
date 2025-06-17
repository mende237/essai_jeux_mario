package com.game.mario.item;

import javafx.scene.image.Image;

public class Tuyau extends GameItem {

	public Tuyau(int x, int y) {
		super(x, y, 65, 43, new Image(Tuyau.class.getResource("images/tuyauRouge.png").toExternalForm()));
	}

}
