package statisticaltests;

import data.DataSet;

/**
 * @author Monil Patel 
 * Description: Calculates the Confidence Interval for the mean on DataSet. Checks conditions, perform the test itself, and return values specific to the inference test.
 */
public class ConfidenceIntervalForMean extends InferenceTest {
	private String result, conditions;
	private double xbar;
	/**
	 * Initializes the calculations to 0 and sets the normal condition.
	 */
	public ConfidenceIntervalForMean()
	{
		normCond = "The Central Limit Theorem ([n] >= 30, n = Sample Size)\n\tmust be satisfied.";
		xbar=0;
		result="";
		conditions="";
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
		data=d;
		//if(conditionsMet())
		{
			double calc=0;
			xbar=super.calculateSampleMean();			
			calc=super.calculateStandardDeviationOfStatistics()/Math.sqrt(data.getSampleSize());
			calc*=1.645;
			
			double x=xbar+calc;
			double y=xbar-calc;
			result="\n\nWe can be " + 95+"% confident that the true population mean is between\n("+y+", "+x+")";
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
