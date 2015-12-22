package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: MathGraphics represents the title page's background.
 *
 */
public class MathGraphics extends JPanel{

	private static final long serialVersionUID = 1L;
	ImageIcon mathBackground;
	boolean isTitle = false;
	
	/**
	 * Creates an instance of the MathGraphics object.
	 */
	public MathGraphics(){
		//load the pic
		mathBackground = new ImageIcon("mathbackground.jpg");
	}
	
	/**
	 * Displays the photo background and the overlaid text.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		mathBackground.paintIcon(this, g, 0, 0);
		
		Font title = new Font("title", Font.BOLD, 40);
		g.setColor(Color.white);
		g.setFont(title);
		g.drawString("Statisctics Calculator", 45, 100);
		
		Font title2 = new Font("title", Font.BOLD, 10);
		g.setFont(title2);
		g.drawString("By Jake and Monil, APCS p4", 45, 140);
	}
}
