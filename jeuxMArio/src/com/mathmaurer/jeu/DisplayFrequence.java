package com.mathmaurer.jeu;

public class DisplayFrequence implements Runnable{
	private int pause = 3; //waiting time between two loops turn
	
	
	@Override
	public void run() {
		while(true) {
//			if(GameManagement.isInterupt() == true) {
//				pause = 3000;
//				System.out.println("pause");
//			}
			if(GameManagement.DOWN() >= 0) {
				Main.scene.repaint();
				GameManagement.UP();
			}
			//Character.generalPause = true;
			
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				
			}
			
//			Character.generalPause = false;

//			System.out.println(pause);
//			GameManagement.setInterupt(false);
//			pause = 3;
			
		}
	}
	

}
