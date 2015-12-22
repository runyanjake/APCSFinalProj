package statisticaltests;

import data.DataSet;

/**
 *	Description: An interference test can be performed on a DataSet. 
 *Has to check conditions, perform the test itself, and return values specific to the inference test.
 */
public abstract class InferenceTest {
	protected  DataSet data;	
	protected String normCond = "";
	/**
	 * Checks whether conditions of the test are met.
	 * @return if the conditions of the statistical test are met.
	 */
	public abstract boolean conditionsMet();
	public abstract String getConditions();
	public abstract String getCalculation();
	/**
	 * Calculates the specified inference test or confidence interval
	 * @param d is the DataSet that the calculations will be done on.
	 */
	public void calculate(DataSet d){
		data = d;
	}
	
	public String getNormCond(){
		return normCond;
	}
	/**
	 *  Calculates the sample mean of the dataSet
	 * @return the sample mean of set of data.
	 */
	public double calculateSampleMean()
	{
		double mean = 0;
		for(int i=0;i<data.getGivenMeansOrProportions().size();i++)
		{
			mean+=data.getGivenMeansOrProportions().get(i);
		}
		mean/=data.getGivenMeansOrProportions().size();
		return mean;		
	}
	/**
	 *  Calculates the sample proportion of the dataSet
	 * @return the sample proportion of set of data.
	 */
	public double calculateSampleProportion()
	{
		double proportion=0;
		double count=0;
		for(int i=0; i<data.getGivenMeansOrProportions().size();i++)
		{
			if(data.getGivenMeansOrProportions().get(i)==1.0)
			{
				count++;
			}
		};
		proportion=count/data.getGivenMeansOrProportions().size();
		return proportion;
	}
	/**
	 *  Calculates the standard deviation of the statistic.
	 * @return the standard deviation of the statistic of set of data.
	 */
	public double calculateStandardDeviationOfStatistics()
	{	
		double result=0,res=0;
		for(int i=0; i<data.getGivenMeansOrProportions().size();i++)
		{
			result=data.getGivenMeansOrProportions().get(i);
			double popMean=data.getPopTrue();
			res+= (Math.pow(result-popMean, 2));
		}
		res/=data.getSampleSize()-1;
		res=Math.sqrt(res);
		return res;
	}
	public DataSet getData()
	{
		return data;
	}	
}
