package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		JFrame window = new JFrame();
		window.setSize(550, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		window.requestFocus();
		
		window.setVisible(true);
		gamePanel.startGameThread();
	}
}
