package gui;

import java.awt.BorderLayout;
/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: TitlePage is the first displayed page in the CardLayout.
 *
 */
public class TitlePage extends HelperPage{

	private static final long serialVersionUID = 1L;
	/**
	 *  Instantiates a TitlePage.
	 */
	public TitlePage(){
		super();
		pageName = "2015 APCS Final Project";
		nextWord = "Begin";
		hasPrevButton = false;
		this.setLayout(new BorderLayout());
		setup();
		MathGraphics tg = new MathGraphics();
		this.add(tg,BorderLayout.CENTER);
		tg.repaint();
	}
}
