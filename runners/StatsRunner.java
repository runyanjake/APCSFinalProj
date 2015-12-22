package runners;

import javax.swing.JFrame;

import gui.StatsHelper;
/**
 * 
 * @author Jake Runyan
 * @version 1 May 25, 2015
 * Description: StatsRunner contains the main method and creates the panels to be seen.
 *
 */
public class StatsRunner {

	public static void main(String[] args){

		JFrame statsFrame = new JFrame("Statistical Inference Test Calculator by Jake and Monil");
		statsFrame.setResizable(false);
		statsFrame.setBounds(100, 100, 500, 500);
		
		StatsHelper pucc = new StatsHelper();
		statsFrame.add(pucc);
		
		statsFrame.setVisible(true);
		pucc.setVisible(true);
	}
}
