package com.mathmaurer.jeu;

import javax.swing.JFrame;

public class Main {
	public static Scene scene;
	
	public static void  main(String args[]) {
		//creating game window
		JFrame window = new JFrame("mario");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 380);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setAlwaysOnTop(true);
//		window.setLayout();
		
		scene = new FirstStage();
		//here we add scene to ours windowApp
		window.setContentPane(scene);			
		window.setVisible(true);

	}

}