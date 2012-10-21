package AutoCooker.Utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import org.powerbot.game.api.methods.input.Mouse;

import AutoCooker.Cooker;

public class Paint {

	public final static void drawMouse(final Graphics render) {
		Graphics2D g = (Graphics2D) render;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setColor(Color.CYAN);
		g.drawLine(Mouse.getX() - 6, Mouse.getY(), Mouse.getX() + 6, Mouse.getY());
		g.drawLine(Mouse.getX(), Mouse.getY() - 6, Mouse.getX(), Mouse.getY() + 6);
	}
	
    private final static Color color1 = new Color(0, 0, 0,100);
    private final static Color color2 = new Color(204, 0, 51);
    private final static Color color3 = new Color(255, 255, 255);

    private final static BasicStroke stroke1 = new BasicStroke(1);

    private final static Font font1 = new Font("Arial", 0, 12);

    public final static void drawPaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(355, 55, 158, 205, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(355, 55, 158, 205, 16, 16);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("Cooking:", 380, 70);
        g.drawString(Cooker.sFood, 435, 70);
        g.drawString("Cooked:", 360, 95);
        g.drawString(String.valueOf(Cooker.lCooked), 430, 95);
        g.drawString("Burned:", 360, 115);
        g.drawString(String.valueOf(Cooker.lBurned), 430, 115);
        g.drawString("Cooked P/h:", 360,135);
        g.drawString(String.valueOf(Cooker.lCookPh), 430, 135);
        g.drawString("Level:", 360, 155);
        g.drawString(String.valueOf(Cooker.lLevel) + "(+" + String.valueOf(Cooker.lLevel - Cooker.lILevel) + ")" , 430,155);
        g.drawString("XP to Level:", 360, 175);
        g.drawString(String.valueOf(Cooker.lXPLevel), 430, 175);
        g.drawString("XP P/h:", 360, 195);
        g.drawString(String.valueOf(Cooker.lXPHr), 430, 195);
        g.drawString("Level in:", 360, 215);
        g.drawString(Cooker.sTTL, 430, 215);
        g.drawString("Time Elapsed:", 360, 240);
        g.drawString(Cooker.getTime(), 450,240);
        g.drawString("Status: ", 360, 255);
        g.drawString(Cooker.sStatus, 410, 255);
    }
}


