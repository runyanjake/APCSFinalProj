package statisticaltests;
//import org.apache.commons.math3.distribution.TDistribution;

import data.DataSet;
/**
 * @author Monil Patel 
 * Description: Calculates the significance test for the mean on DataSet. Checks conditions, perform the test itself, and return values specific to the inference test.
 */
public class SignificanceTestForMean extends InferenceTest {
	private double result, xbar;
	private String conditions;
	private double prob;
	/**
	 * Initializes the calculations and normal conditions.
	 */
	public SignificanceTestForMean()
	{
		normCond = "The Central Limit Theorem ([n] >= 30, n = Sample Size)\n\tmust be satisfied.";
		result=0;
		xbar=0;
		conditions="";
		prob=0;
	}
	/**
	 * Checks whether conditions of the test are met.
	 * @return if the conditions of the statistical test are met.
	 */
	public boolean conditionsMet() {
		if(super.getData().getSampleSize()>=30)
		{
			return true;
		}
		return false;
	}

	/**
	 * Calculates the specified inference test or confidence interval
	 * @param d is the DataSet that the calculations will be done on.
	 */
	public void calculate(DataSet d) {
		data = d;
		//if(conditionsMet())
		{
			xbar=super.calculateSampleMean();
			System.out.print(super.calculateStandardDeviationOfStatistics());
			//TDistribution t=new TDistribution(d.getSampleSize()-1);
						
			result=xbar-data.getPopTrue();
			result/=super.calculateStandardDeviationOfStatistics()/Math.sqrt(data.getSampleSize());
			
			//prob=1-t.cumulativeProbability(result);
			
			prob = 0.05;
		}
	}
	public String getConditions()
	{
		if(conditionsMet())
		{
			conditions="Normal condition is met";	
		}
		else
		{
			conditions="Normal condition is not met";
		}
		return conditions;
	}
	public double getProb()
	{
		return prob;
	}

	/**
	 * Returns the calculation done.
	 */
	public String getCalculation() 
	{
		String result="";
		if(getProb()<.05)
		{
			result="\n\nSince the P-value "+getProb()+" is less than alpha=.05, \nthe sample result is statistically significant at the 5% level.\nTherefore the sample proportion is invalid at this level.";
		}
		else
		{
			result="\n\nSince the P-value "+getProb()+" is greater than alpha=.05, \nthe sample result is not statistically significant at the 5% level.\nTherefore the sample proportion is not invalid at this level.";
		}
		return result;
	}
}
