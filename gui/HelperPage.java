package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import statisticaltests.InferenceTest;
/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: HelperPage represents a page in a StatsHelper.
 *
 */
public abstract class HelperPage extends JPanel{ // originally extended JPanel
	
	private static final long serialVersionUID = 1L;
	private JButton nextButton,previousButton;
	protected String nextWord = "Next"; // Text to be displayed on the 'next' Button
	protected String prevWord = "Previous";
	protected String pageName = "New Page";
	protected boolean hasNextButton = true;
	protected boolean hasPrevButton = true;
	private InferenceTest currentTest = null;
	
	/**
	 * Creates the top navigation bar, common to all HelperPages.
	 */
	public void setup(){
		
		JPanel navToolbar = new JPanel(new BorderLayout());
		navToolbar.setBackground(Color.gray);
		navToolbar.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		this.setLayout(new BorderLayout());
		
		if(hasNextButton){
			nextButton = new JButton(nextWord);
			navToolbar.add(nextButton, BorderLayout.EAST);
		}else{
			nextButton = new JButton("                ");
			nextButton.setEnabled(false);
			navToolbar.add(nextButton, BorderLayout.EAST);
		}
		
		if(hasPrevButton){
			previousButton = new JButton(prevWord);
			navToolbar.add(previousButton, BorderLayout.WEST);
		}else{
			previousButton = new JButton("                ");
			previousButton.setEnabled(false);
			navToolbar.add(previousButton, BorderLayout.WEST);
		}
		
		JTextField pageTitle = new JTextField();
		pageTitle.setText(pageName);
		pageTitle.setEditable(false);
		pageTitle.setBackground(Color.gray.brighter());
		pageTitle.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		pageTitle.setHorizontalAlignment(JTextField.CENTER);
		navToolbar.add(pageTitle,BorderLayout.CENTER);
		
		this.add(navToolbar, BorderLayout.NORTH);
		
	}
	
	public void setInferenceTest(InferenceTest it){
		currentTest = it;
	}
	
	public InferenceTest getInferenceTest(){
		return currentTest;
	}
	
	public JButton getNextButton(){
		return nextButton;
	}
	
	public JButton getPreviousButton(){
		return previousButton;
	}
}
