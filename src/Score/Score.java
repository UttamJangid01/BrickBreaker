package Score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Score {
	
	GamePanel gp;
	
	public Score(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.setFont(new Font("Arial", Font.PLAIN, 40));
		g2.drawString("Score : "+gp.SCore, gp.borderWidth/3, gp.borderHeight/2);
	}
}
