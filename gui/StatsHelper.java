package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.DataSet;
import statisticaltests.*;
/**
 * 
 * @author Jake Runyan
 * Description: Creates the CardLayout and adds all the panels to it.
 */
public class StatsHelper extends JPanel{

	private static final long serialVersionUID = 1L;

	private HelperPage title,home,conditions,results,data;
	private CardLayout layout;
	private InferenceTest selectedTest = null;
	
	private NextButtonHandler nbh;
	private PreviousButtonHandler pbh;
	private CIMHandler cimh;
	private CIPHandler ciph;
	private CMHandler cmh;
	private STPHandler stph;
	private STMHandler stmh;
	private EditDataHandler edh;
	private EditDataPrevButtonHandler edpbh;
	private String conclusion;
	
	ArrayList<Double> numericalData;
	private DataSet dataset = null;
	
	/**
	 * Creates the StatsHelper by initializing cards and adding them to a CardLayout.
	 */
	public StatsHelper(){
		super(new CardLayout());
		
		conclusion = "";
		numericalData = new ArrayList<Double>();
		nbh = new NextButtonHandler();
		pbh = new PreviousButtonHandler();
		cimh = new CIMHandler();
		ciph = new CIPHandler();
		cmh = new CMHandler();
		stph = new STPHandler();
		stmh = new STMHandler();
		edh = new EditDataHandler();
		edpbh = new EditDataPrevButtonHandler();
		
		title = new TitlePage();
		title.getNextButton().addActionListener(nbh);
		home = new HomePage();
		ArrayList<JButton> testButtons = ((HomePage)home).getButtons();
			testButtons.get(0).addActionListener(cimh);
			testButtons.get(1).addActionListener(ciph);
			testButtons.get(2).addActionListener(cmh);
			testButtons.get(3).addActionListener(stph);
			testButtons.get(4).addActionListener(stmh);
			testButtons.get(5).addActionListener(edh);
		home.getNextButton().addActionListener(nbh);
		home.getPreviousButton().addActionListener(pbh);
		conditions = new ConditionsPage();
		conditions.getNextButton().addActionListener(nbh);
		conditions.getPreviousButton().addActionListener(pbh);
		results = new ResultsPage();
		results.getNextButton().addActionListener(nbh);
		results.getPreviousButton().addActionListener(pbh);
		data = new DataPage();
		data.getNextButton().addActionListener(nbh);
		data.getPreviousButton().addActionListener(edpbh);
		pageSetup();
	}
	/**
	 * Sets up the different pages and adds then as different cards.
	 */
	private void pageSetup(){
		this.add(title,"one");
		this.add(home,"two");
		this.add(conditions,"three");
		this.add(results,"five");
		this.add(data,"six");
		
		layout = (CardLayout)this.getLayout();
	}
	
	public HelperPage getTitlePage(){
		return title;
	}
	
	public HelperPage getHomePage(){
		return home;
	}
	
	public HelperPage getConditionsPage(){
		return conditions;
	}
	
	public HelperPage getResultsPage(){
		return title;
	}
	
	/**
	 * Progresses through the cards inside the StatsHelper's CardLayout.
	 * @throws NullDataException when there is not enough available data from the user. This is thrown and caught within the method.
	 * @throws NumberFormatException when the inputted data is not of the correct form. This is thrown and caught within the method and the user is notified.
	 * 
	 */
	public void nextPage(){
		if(results.isVisible()){
			layout.show(this, "two");
		}else if(home.isVisible()){
			System.out.println("Test Selection Locked In: " + selectedTest);
			
			if( ((ConditionsPage)conditions).isViewable() == true ){
				((ConditionsPage)conditions).resetConditions();
			}
			((ConditionsPage)conditions).displayConditions(selectedTest);
			conditions.repaint();
			layout.next(this);
			
		}else if(conditions.isVisible()){
			try{
				try{
					if(numericalData.size() <= 1 || numericalData == null){
						throw new NullDataException();
					}
					System.out.println("Old Dataset: " + dataset);
					System.out.println(((ConditionsPage)conditions).createDataSet(numericalData));
					dataset = ((ConditionsPage)conditions).createDataSet(numericalData);
					layout.next(this);
					System.out.println("New Dataset: " + dataset);
				}catch(NumberFormatException nfe){
					int a = JOptionPane.showConfirmDialog(this, "Un-Oh. Someone done goofed.\nPlease input information about your dataset in the text boxes below\nand try to begin the test again!\nInputs are intended to be numbers only.\nPress 'OK' to go to the Edit Data Screen or close the window to stay here.", "Oops, Something went wrong.", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
					if(a==0)
						layout.show(this, "six");
				}
			}catch(NullDataException nde){
				int a = JOptionPane.showConfirmDialog(this, "There is not enough data to perform a test on!\nIf you did enter data, ensure that you press enter after entering each \npiece of data, particularly the last one.\nPress 'OK' to go to the Edit Data screen or close the window to stay here", "Oops, Something went wrong.", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				if(a==0)
					layout.show(this, "six");
			}
			selectedTest.calculate(dataset);
			conclusion = selectedTest.getCalculation();
			((ResultsPage)results).showConclusion(conclusion);
		}else{
			layout.next(this);
		}
	}
	
	/*
	 * Goes to the previous page in the CardLayout.
	 */
	public void previousPage(){
			layout.previous(this);
	}
	
	/*
	 * Pops up a window to the user displaying the current data loaded into the StatsHelper.
	 */
	public void dataUpdatePopup(){
		String dataString = "";
		dataString += "           [";
		for(int a = 0; a < numericalData.size()-1;a++){
			dataString += numericalData.get(a) + ", ";
			if(a>0 && a%8==0)
				dataString+="\n          ";
		}
		dataString += numericalData.get(numericalData.size()-1) + "]";
		
		JOptionPane.showConfirmDialog(this, "Success!\nYour data has been updated.\nYour dataset now contains:\n" + dataString, "Data Edits Complete.", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 *  Turns the layout to a specific page.
	 * @param page the specific page that the card layout should switch to.
	 */
	public void turnToPage(String page){
		layout.show(this, page);
	}
	
	class NextButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Parse through each page in the cardlayout and check which one's visible. that's the one you're on
			nextPage();
		}	
	}
	
	class PreviousButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			previousPage();
		}	
	}
	
	class CIMHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A CIMHandler has been clicked!");
			selectedTest = new ConfidenceIntervalForMean();
			
			nextPage();
		}
		
	}
	
	class CIPHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A CIPHandler has been clicked!");
			selectedTest = new ConfidenceIntervalForProportion();
			nextPage();
		}
		
	}
	
	class CMHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A CMHandler has been clicked!");
			selectedTest = new PopulationMean();
			nextPage();
		}
		
	}
	
	class STPHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A STPHandler has been clicked!");
			selectedTest = new SignificanceTestForProportion();
			nextPage();
		}
		
	}
	
	class STMHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A STMHandler has been clicked!");
			selectedTest = new SignificanceTestForMean();
			nextPage();
		}
		
	}
	
	class EditDataHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A Edit Data Button has been clicked!");
			turnToPage("six");
		}
		
	}
	
	class EditDataPrevButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("A Edit Data Button has been clicked!");
			System.out.println(((DataPage)data).compileData());
			numericalData = ((DataPage)data).compileData();
			selectedTest = null;
			dataUpdatePopup();
			turnToPage("two");
		}
		
	}
	
	class NullDataException extends Exception{
		
		private static final long serialVersionUID = 1L;

		public NullDataException(){
			
		}
		
		public NullDataException(String message){
			super(message);
		}
		
	}
	
}
