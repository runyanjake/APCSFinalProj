package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: ResultsPage shows the results of a test to the user.
 *
 */
public class ResultsPage extends HelperPage{

	private static final long serialVersionUID = 1L;
	/**
	 * Instantiates a ResultsPage.
	 */
	public ResultsPage(){
		super();
		hasPrevButton = false;
		pageName = "Results";
		nextWord = "Finish";
		setup();
	}
	/**
	 * Shows the result of the specific inference test.
	 * @param conclusion is the result of the specific inference test 
	 */
	public void showConclusion(String conclusion){
		JTextArea conc = new JTextArea(conclusion);
		conc.setEditable(false);
		MatteBorder outside = new MatteBorder(20,20,20,20,new ImageIcon("mathbackground.jpg"));
		EtchedBorder inside = new EtchedBorder(EtchedBorder.RAISED);
		CompoundBorder mathborder = new CompoundBorder(outside,inside);
		conc.setBorder(mathborder);
		this.add(conc,BorderLayout.CENTER);
	}	
}
