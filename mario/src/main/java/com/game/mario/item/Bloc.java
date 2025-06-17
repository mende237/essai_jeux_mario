package com.game.mario.item;

import javafx.scene.image.Image;

public class Bloc extends GameItem {

	public Bloc(int x, int y) {
		super(x, y, 30, 30, new Image(Bloc.class.getResource("images/bloc.png").toExternalForm()));
	}

}
