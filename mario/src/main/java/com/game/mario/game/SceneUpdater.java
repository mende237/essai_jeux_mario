package com.game.mario.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import com.game.mario.App;
import javafx.util.Duration;

public class SceneUpdater {
	private final static double pause = 3; // waiting time between two loops turn

	public static void update(GraphicsContext gc) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(pause), event -> {
			App.scene.paint(gc);
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
		timeline.play(); // Start the timeline
	}

}
