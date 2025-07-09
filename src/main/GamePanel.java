package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ball.Ball;
import Block.Block;
import Bricks.Bricks;
import Score.Score;
import Sound.Sound;

public class GamePanel extends JPanel implements Runnable{
	
	public final int borderWidth = 550;
	public final int borderHeight = 700;
	final int FPS = 120;
	BufferedImage background;
	
	// Objects 
	public Ball ball = new Ball(this);
	public Block block = new Block(this);
	public Bricks bricks = new Bricks(this);
	public Sound sound = new Sound();
	Score score = new Score(this);
	
	public Thread gameThread;
	public int SCore = 0;
	
	public GamePanel() {
		setPreferredSize(new Dimension(borderWidth, borderHeight));
		setBackground(Color.black);
		addKeyListener(block);
		setFocusable(true);
		
		// try {
		// 	background = ImageIO.read(getClass().getResourceAsStream("/Images/background.jpg"));			
		// }catch(IOException e) {
		// 	e.printStackTrace();
		// }
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1) {
            	move();
            	bricks.collision();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS : "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
	}
	
	public void move() {
		ball.move();
		block.move();
		if(ball.y + ball.height >= borderHeight || bricks.array.size() == 0) {
			gameThread = null;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		SCore = 45-bricks.array.size();
		Graphics2D g2 = (Graphics2D)g;
//		g2.drawImage(background, 0, 0, borderWidth, borderHeight, null);
		ball.draw(g2);
		block.draw(g2);
		bricks.draw(g2);
		
		if(gameThread == null) {
			score.draw(g2);
		}
		
		g2.dispose();
	}
}
