package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import data.DataSet;
import statisticaltests.*;

/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: ConditionsPage shows the specific conditions required to complete a specific inference test and prompts the user to enter any extra test-specific data.
 *
 */
public class ConditionsPage extends HelperPage{

	private static final long serialVersionUID = 1L;
	private JTable jt; 
	private InferenceTest currentTest;
	private JPanel extras = null;
	private JTextArea conditions = null;
	private boolean viewable = false;
	/**
	 * Creates a ConditionsPage.
	 */
	public ConditionsPage(){
		super();
		pageName = "Conditions";
		nextWord = "Begin Test";
		setup();
	}	
	/**
	 * Displays the conditions of the specific inference test.
	 * @param it represents the inference test is is to be displayed.
	 */
	public void displayConditions(InferenceTest it){
		viewable = true;
		
		currentTest = it;
		conditions = new JTextArea("The Following Conditions must be met to offer a Meaningful Conclusion:\n\n\n\n\tThe data must be gathered Randomly.\n\n\tCollecting one piece of data must not affect another.\n\n\t" + currentTest.getNormCond() + "\n\n\n\n\n\nEven if these conditions are not met, a test can be performed.\nHowever, the data will not provide any grounds towards a conclusion.");
		Border outer = new MatteBorder(15,15,15,15,Color.gray);
		Border inner = new MatteBorder(2,2,2,2,Color.black);
		conditions.setBorder(new CompoundBorder(outer,inner));
		
		extras = new JPanel(new BorderLayout());
		JTextArea title = new JTextArea("Test-Specific Data. All Confidence Levels are 95%. Press Enter after each Entry.");
		title.setEditable(false);
		title.setBorder(new LineBorder(Color.black));
		title.setBackground(Color.gray.brighter());
		
		jt = new JTable(new DefaultTableModel(null,new Object[]{"Entries"}));
		DefaultTableModel model = (DefaultTableModel)jt.getModel();
		if(currentTest instanceof ConfidenceIntervalForMean){
		}else if (currentTest instanceof ConfidenceIntervalForProportion){
			model.addRow(new Object[]{"Input the Population Size"});
		}else if (currentTest instanceof PopulationMean){
			
		}else if (currentTest instanceof SignificanceTestForMean){
			model.addRow(new Object[]{"Input the True Population Mean"});
		}else if (currentTest instanceof SignificanceTestForProportion){
			model.addRow(new Object[]{"Input the True Population Proportion"});
			model.addRow(new Object[]{"Input the Population Size"});
		}
		
		extras.add(jt,BorderLayout.CENTER);
		extras.add(title,BorderLayout.NORTH);

		
		this.add(extras,BorderLayout.SOUTH);
		
		this.add(conditions,BorderLayout.CENTER);
	}
	
	/**
	 * Resets the conditions page, readying it to be refreshed.
	 * @pre The displayConditions method must have been called to initialize the ConditionsPage.
	 */
	public void resetConditions(){
		this.remove(conditions);
		this.remove(extras);
	}
	
	public boolean isViewable(){
		return viewable;
	}
	
	/**
	 * Creates the dataSet based off of the different test specified.
	 * @param numData is the ArrayList of means or proportions inputted by the user.
	 * @return a dataSet object to be used in calculating the inference tests
	 */
	public DataSet createDataSet(ArrayList<Double> numData){
		DataSet ans = null;
		double truePop = 0;
		double sampleSize = 0;

		DefaultTableModel model = (DefaultTableModel)jt.getModel();
		
			if(currentTest instanceof ConfidenceIntervalForMean){
			}else if (currentTest instanceof ConfidenceIntervalForProportion){
			
						sampleSize = (Double.parseDouble((String)model.getValueAt(0, 0)));
					
			}else if (currentTest instanceof PopulationMean){
				
			}else if (currentTest instanceof SignificanceTestForMean){
				
						truePop = (Double.parseDouble((String)model.getValueAt(0, 0)));
					
			}else if (currentTest instanceof SignificanceTestForProportion){
				
				truePop = (Double.parseDouble((String)model.getValueAt(0, 0)));
				sampleSize = (Double.parseDouble((String)model.getValueAt(1, 0)));
					
			}
			
		ans = new DataSet(numData,truePop,sampleSize);
		System.out.println(ans);
		return ans;
	}
}
