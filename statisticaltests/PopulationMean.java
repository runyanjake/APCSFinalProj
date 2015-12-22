package statisticaltests;

import data.DataSet;

/**
 * 
 * @author Monil Patel
 * Description: Represents the population mean from which many inference tests and confidence interval calculations depend on.
 */
public class PopulationMean extends InferenceTest{
	private double result;
	/**
	 * Initializes the population mean to 0.
	 */
	public PopulationMean() {
		result=0;
	}

	/**
	 * Checks whether conditions of the test are met.
	 * @return if the conditions of the statistical test are met.
	 */
	public boolean conditionsMet() {
		return true;
	}

	/**
	 * Calculates the specified inference test or confidence interval
	 * @param d is the DataSet that the calculations will be done on.
	 */
	public void calculate(DataSet d) {
		data = d;
		double finalResult=0;
		if(conditionsMet())
		{
			double result;
			int size=data.getGivenMeansOrProportions().size();
			for(int j=0;j<100;j++)
			{
				result=0;
				for(int i=0; i<10;i++)
				{
					result+=data.getGivenMeansOrProportions().get((int)(size*Math.random()));
				}
				result/=10;
				finalResult+=result;
			}
			finalResult/=100;
		}
		this.result=finalResult;
	}
	public String getCalculation() 
	{
		return "\n\nThe population mean is "+result;
	}
	public double getResult()
	{
		return result;
	}
	public String getConditions() 
	{
		return "";
	}
}
