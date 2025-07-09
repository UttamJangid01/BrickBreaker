package Ball;

import java.awt.Color;
import java.awt.Graphics2D;

import Block.Block;
import main.GamePanel;

public class Ball {
	
	GamePanel gp;
	public int x, y;
	public int width, height;
	public boolean vertical;
    public boolean horizontal;
	
	public Ball(GamePanel gp) {
		this.gp = gp;
		x = 262;
		y = 635;
		width = 25;
		height = 25;
		vertical = true;
		horizontal = false;
	}
	
	public void move() {
		// horizontal true + and false -
		// vertical true + and false -
				
		if(y <= 0) {
			vertical = true;
		}
		//ball.y + ball.height >= borderHeight
		else if(((x >= gp.block.x-gp.block.circle && x <= (gp.block.x-gp.block.circle) + (gp.block.width+gp.block.circle)) || (x + width >= gp.block.x-gp.block.circle && x + width <= (gp.block.x-gp.block.circle) + (gp.block.width+gp.block.circle)))
		&& (y + height >= gp.block.y && y + height <= gp.block.y + gp.block.height)) {
			vertical = false;
		}
		if(x <= 0) {
			horizontal = true;
		}
		else if(x + width >= gp.borderWidth) {
			horizontal = false;
		}
		
		// for block and ball directions
		Block block = gp.block;
		if(y+height >= block.y && y+height <= block.y+10 && ((x >= block.x && x <= block.x + block.width) || (x + width >= block.x && x + width <= block.x + block.width))) {
			if(block.leftPressed) {
				horizontal = false;
			}
			if(block.rightPressed) {
				horizontal = true;
			}
		}
		
		if(vertical) {
			y += 3;
		}else {
			y -= 3;
		}
				
		if(horizontal) {
			x += 3;
		}else {
			x -= 3;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillOval(x, y, width, height);
	}
}
