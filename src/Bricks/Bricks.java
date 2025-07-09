package Bricks;
import java.io.InputStream;// if you're declaring `image` as BufferedImage

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Ball.Ball;
import main.GamePanel;

public class Bricks {
	
	GamePanel gp;
	public int rows, columns;	
	BufferedImage image;
	
	class Brick{
		int x, y;
		int width, height;
		boolean Draw = true;
		
		Brick(int x, int y, int width, int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}
	
	public ArrayList<Brick> array;
	
	public Bricks(GamePanel gp) {
		this.gp = gp;
		rows = 5;
		columns = 9;
		array = new ArrayList<Brick>();
		getLoadImage();
		placeBricks();
		
	}
	
	public void getLoadImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("brick.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void placeBricks() {
		int w=6, h=10;
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Brick brick = new Brick(j+w, i+h, 50, 25);
				array.add(brick);
				w += 60;
			}
			h += 30;
			w = 6;
		}
	}
	
	public boolean forX(Ball ball, Brick brick) {
		return (ball.x >= brick.x && ball.x <= brick.x + brick.width) || (ball.x + ball.width >= brick.x && ball.x + ball.width <= brick.x + brick.width);
	}
	
	public boolean forY(Ball ball, Brick brick) {
		return (ball.y >= brick.y && ball.y <= brick.y+brick.height) || (ball.y+ball.height >= brick.y && ball.y+ball.height <= brick.y+brick.height);
	}
	
	public void collision() {
		for(int i=0; i<array.size(); i++) {
			Brick brick = array.get(i);
			Ball ball = gp.ball;
			int space = 10;
			
			if(ball.x+ball.width >= brick.x && ball.x+ball.width <= brick.x+space && forY(ball, brick)) {
				brick.Draw = false;
				ball.horizontal = false;
				array.remove(brick);
				gp.sound.playSound("Brick_Sound.wav");
			}
			if(ball.x <= brick.x+brick.width && ball.x >= brick.x+(brick.width-space) && forY(ball, brick)) {
				brick.Draw = false;
				ball.horizontal = true;
				array.remove(brick);
				gp.sound.playSound("Brick_Sound.wav");
			}
			
			if(ball.y+ball.height >= brick.y && ball.y+ball.height <= brick.y+space && forX(ball, brick)) {
				brick.Draw = false;
				ball.vertical = false;
				array.remove(brick);
				gp.sound.playSound("Brick_Sound.wav");
			}
			if(ball.y <= brick.y+brick.height && ball.y >= brick.y+(brick.height-space) && forX(ball, brick)) {
				brick.Draw = false;
				ball.vertical = true;
				array.remove(brick);
				gp.sound.playSound("Brick_Sound.wav");
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.gray);
		for(int i=0; i<array.size(); i++) {
			Brick brick = array.get(i);
//			if(brick.Draw) {
				g2.drawImage(image, brick.x, brick.y, brick.width, brick.height, null);
//			}
		}
	}
}
