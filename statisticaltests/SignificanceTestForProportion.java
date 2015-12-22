package statisticaltests;

//import org.apache.commons.math3.distribution.NormalDistribution;

import data.DataSet;

/**
 * @author Monil Patel 
 * Description: Calculates the significance test for the proportion on DataSet. Checks conditions, perform the test itself, and return values specific to the inference test.
 */
public class SignificanceTestForProportion extends InferenceTest{
	private double result, phat, prob;
	private String conditions;
	/**
	 * Initializes the normal condition and the calculations
	 */
	public SignificanceTestForProportion()
	{
		normCond = "The Normal Condition ([n][p] >= 10 AND [n][1-p] >= 10, \n\tn = Sample Size, p = Sample Proportion) must be satisfied.";
		result=0;
		phat=0;
		conditions="";
		prob=0;
	}
	/**
	 * Checks whether conditions of the test are met.
	 * @return if the conditions of the statistical test are met.
	 */
	public boolean conditionsMet() {
		if((super.getData().getSampleSize()*phat)>=10 && super.getData().getSampleSize()*(1-phat)>=10)
		{
			return true;
		}
		return false;
	}

	/**
	 * Calculates the specified inference test or confidence interval
	 * @param d is the DataSet that the calculations will be done on.
	 */
	public void calculate(DataSet d) 
	{
		data = d;
		//if(conditionsMet())
		{
			double prop=data.getPopTrue();
			phat=super.calculateSampleProportion();

			result=phat-prop;
			result/=Math.sqrt((prop*(1-prop))/data.getSampleSize());
		}
	}
	public double getProb()
	{
		//NormalDistribution p=new NormalDistribution();
		//prob= p.cumulativeProbability(result);
		//return prob;	
		
		return 0;
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
