package statisticaltests;

import data.DataSet;

/**
 * @author Monil Patel 
 * Description: Calculates the Confidence Interval for the proportion on DataSet. Checks conditions, perform the test itself, and return values specific to the inference test.
 */
public class ConfidenceIntervalForProportion extends InferenceTest {
	private double phat;
	private String result, conditions;
	/**
	 * Initializes the calculations to 0 and sets the normal condition.
	 */
	public ConfidenceIntervalForProportion()
	{ 
		normCond = "The Normal Condition ([n][p] >= 10 AND [n][1-p] >= 10, \n\tn = Sample Size, p = Sample Proportion) must be satisfied.";
		phat=0;
		result="";
		conditions="";
	}
	/**
	 * Checks whether conditions of the test are met.
	 * @return if the conditions of the statistical test are met.
	 */
	public boolean conditionsMet() 
	{
		if((super.getData().getSampleSize()*phat)>=10 && super.getData().getSampleSize()*(1-phat)>=10)
		{
			return true;
		}
		return false;
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
	 * Calculates the specified inference test or confidence interval
	 * @param d is the DataSet that the calculations will be done on.
	 */
	public void calculate(DataSet d) {
		data = d;
		//if(conditionsMet())
		{
			double calc=0;
			phat=super.calculateSampleProportion();
			calc=Math.sqrt((phat*(1-phat)/data.getSampleSize()));
			calc*=1.645;
			
			double x=phat+calc;
			double y=phat-calc;
			
			result="\n\nWe can be " + 95+"% confident that the true population proportion is between \n("+y+", "+x+")";

		}
	}

	/**
	 * Returns the calculation done.
	 */
	public String getCalculation() 
	{
		return result;
	}
}
