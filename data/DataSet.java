package data;

import java.util.ArrayList;

/**
 * 
 * @author Monil Patel
 * Description: Represents the data set from which the calculation will be done. 
 */
public class DataSet {
	private double  popProp, sampleSize;
	private ArrayList<Double> arr=new ArrayList<Double>();
	/**
	 * Creates a DataSet that has the user's inputed data, from which the calculations will be done/
	 * @param it represents the ArrayList of doubles of the means or proportions given in a specific problem
	 * @param truePop represents the true population mean or population proportion.
	 * @param sample represents the sample size of the specified problem
	 */
	public DataSet(ArrayList<Double> it, double truePop, double sample)
	{
		arr=it;
		popProp=truePop;
		sampleSize=sample;
	}
	public double getSampleSize()
	{
		return arr.size();
	}
	public double getPopTrue()
	{
		return popProp;
	}
	public ArrayList<Double> getGivenMeansOrProportions()
	 {
		  return arr;
	 }
	/**
	 * Displays the sample size, true population, and ArrayList of means or proportions.
	 * @return the string representation of the dataSet
	 */
	public String toString(){
		return "" + arr.toString() + " PopProp: " + popProp + "  SampleSize: " + sampleSize;
	}
}
