package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: The HomePage displays options to edit data or begin a test.
 *
 */
public class HomePage extends HelperPage{

	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> testOptions = null;
	
	/**
	 * Creates a HomePage.
	 */
	public HomePage(){
		super();
		pageName = "Home";
		hasNextButton = false;
		setup();
	}
	/**
	 * Creates all the components unique to a HomePage. This includes the buttons for editing data and beginning a test.
	 */
	public void setup(){
		super.setup();
		JPanel tests = new JPanel(new GridLayout(9,1));
		
		testOptions = new ArrayList<JButton>();
		JButton CIForMean = new JButton("Calculate Confidence Interval for Mean");
		CIForMean.setBorder(new TitledBorder(new LineBorder(Color.black), "Confidence Interval For Mean"));
		testOptions.add(CIForMean);
		JButton CIForProp = new JButton("Calculate Confidence Interval for Proportion");
		CIForProp.setBorder(new TitledBorder(new LineBorder(Color.black), "Confidence Interval for Proportion"));
		testOptions.add(CIForProp);
		JButton popMean = new JButton("Calculate Population Mean");
		popMean.setBorder(new TitledBorder(new LineBorder(Color.black), "Calculate Population Mean"));
		testOptions.add(popMean);
		JButton SigTestForProp = new JButton ("Perform a Significance Test for Population Proportion");
		SigTestForProp.setBorder(new TitledBorder(new LineBorder(Color.black), "Significance Test for Population Proportion"));
		testOptions.add(SigTestForProp);
		JButton SigTestForMean = new JButton ("Perform a Significance Test for Population Mean");
		SigTestForMean.setBorder(new TitledBorder(new LineBorder(Color.black), "Significance Test For Population Mean"));
		testOptions.add(SigTestForMean);
		
		tests.add(CIForMean);
		tests.add(CIForProp);
		tests.add(popMean);
		tests.add(SigTestForProp);
		tests.add(SigTestForMean);
	
		this.add(tests,BorderLayout.CENTER);
		
		
		JButton editData = new JButton ("View and Edit Data.");
		editData.setBorder(new TitledBorder(new MatteBorder(5,5,5,5,Color.gray), "Edit Data"));
		
		testOptions.add(editData);
		
		this.add(editData,BorderLayout.SOUTH);
		
		
	}
	
	public ArrayList<JButton> getButtons(){
		return testOptions;
	}
}
