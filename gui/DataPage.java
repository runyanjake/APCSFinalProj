package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: The DataPage displays the data loaded in the calculator and allows the user to input and edit their dataset.
 *
 */
public class DataPage extends HelperPage{

	private static final long serialVersionUID = 1L;
	private JTextArea tb;
	private DefaultTableModel model;
	/**
	 * Creates a DataPage.
	 */
	public DataPage(){
		super();
		pageName = "Edit Data";
		prevWord = "Update";
		hasNextButton = false;
		setup();
	}
	/**
	 * Sets up the DataPage for inputting and editing data.
	 */
	public void setup(){
		super.setup();
		JPanel background = new JPanel(new BorderLayout());
		background.setBorder(new SoftBevelBorder(1,Color.gray,Color.gray.darker()));
		
		JTable jt = new JTable(new DefaultTableModel(null,new Object[]{"Entries"}));
		model = (DefaultTableModel)jt.getModel();
		model.addRow(new Object[]{"Sample Data 1"});
		model.addRow(new Object[]{"Sample Data 2"});
		jt.setBorder(new SoftBevelBorder(1,Color.gray,Color.gray.darker()));
		
		JScrollPane scrollJT = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		background.add(scrollJT,BorderLayout.CENTER);
		
		JPanel dataTopBar = new JPanel(new BorderLayout());
		tb = new JTextArea("Input Data Here: Each Line represents one data point. \nRemember yout MUST hit the enter key after each entry for it to be saved. \nYou may leave a entry blank if you don't want it used.\nEntries are to be numerical only. Add Entries Below.\nFor a Proportion test, 1 = true response, 0 = false response");
		tb.setBackground(Color.LIGHT_GRAY);
		tb.setEditable(false);
		dataTopBar.add(tb,BorderLayout.CENTER);
		
		JButton addLine = new JButton("Add Line");
		dataTopBar.add(addLine,BorderLayout.EAST);
		addLine.addActionListener(new LineButtonHandler());
		
		background.add(dataTopBar,BorderLayout.NORTH);
		
		
		this.add(background,BorderLayout.CENTER);
	}
	/**
	 * Compiles the user data into an ArrayList.
	 * @return an ArrayList that represents the data inputted by the user.
	 */
	public ArrayList<Double> compileData(){
		ArrayList<Double> ans = new ArrayList<Double>();
		System.out.println(model.getRowCount());
		for(int a = 0; a < model.getRowCount(); a++){
			try{
				System.out.println("\nTesting at index " + a);
				System.out.println("Value is: " +  model.getValueAt(a,0));
				ans.add(Double.parseDouble((String)model.getValueAt(a, 0)));
			}catch(NumberFormatException e){
				System.out.println("Data in row " + a + "(" + model.getValueAt(a, 0)+ ") was not a number.");
			}
			
		}
		return ans;
	}
	
	
	class LineButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.out.println("Added Line");
			model.addRow(new Object[]{""});
			model.fireTableDataChanged();
		}
		
	}
	
	
	
}
