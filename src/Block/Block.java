package Block;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class Block implements KeyListener{
	
	GamePanel gp;
	public int x, y;
	public int width, height;
	public int circle;
	
	public boolean leftPressed, rightPressed;
	
	public Block(GamePanel gp) {
		this.gp = gp;
		x = 225;
		y = 665;
		width = 80;
		height = 25;
		circle = 10;
	}
	
	public void move() {
		if(leftPressed) {
			x -= 3;
			x = Math.max(0+circle, x);
		}
		if(rightPressed) {
			x += 3;
			x = Math.min(gp.borderWidth-(width+circle), x);
		}
	}
	
	public void draw(Graphics2D g2) {		
		g2.setColor(Color.blue);
		g2.fill3DRect(x, y, width, height, true);
//		g2.setColor(Color.white);
		g2.fillOval(x-circle, y, 25, 25);
		g2.fillOval((x+width)-circle, y, 25, 25);
		
		if(gp.gameThread != null) {
			g2.setColor(Color.green);
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.drawString(""+gp.SCore, x+(width/2)-3, y+20);
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
