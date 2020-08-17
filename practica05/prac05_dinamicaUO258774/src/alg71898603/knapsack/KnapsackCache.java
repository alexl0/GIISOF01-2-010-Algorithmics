package alg71898603.knapsack;

import java.util.ArrayList;
import java.util.List;

import alg71898603.p5.DynamicProgrammingCache;

public class KnapsackCache extends DynamicProgrammingCache {

	public KnapsackCache(String[] labels, int[] values, int[] weights, int max) {
		//Cache table initialization
		super(labels, values, weights, max);
	}
	
	@Override
	protected int defaultValueForCell(int i, int j) {
		if(i == 0)
			return 0;
		else return cache[i - 1][j];
	}
	
	@Override
	protected void updateCell(int i, int j) {
		//Initialize default value to current cell
		//Option 1: 'currentCombination' is not applied and last value is used
		int defaultValue = defaultValueForCell(i, j);
		
		//Option 2: there is enough space for current object
		int value1 = 0;
		if((j - objectWeigths[i]) >= 0) {
			if(i == 0) {
				if(j >= objectWeigths[i])
					value1 = objectValues[i];
			}
			else
				value1 = cache[i - 1][j - objectWeigths[i]] + objectValues[i];
		}
			
		
		//new value is max of 'Option 1' and 'Option 2'
		if(value1 > defaultValue)
			cache[i][j] = value1;
		else
			cache[i][j] = defaultValue;
	}
	
	public int getNextObject(int i, int j)
	{
		if(i < 1) return i;
		while(cache[i][j] == cache[i-1][j]) {
			i--;
			if(i == 0) break;
		}
		return i;
	}

	@Override
	public String[] getBestElements() {
		List<String> l = new ArrayList<String>();
		int i = this.objectLabels.length - 1;
		int j = this.maxWeigth;
		
		while(cache[i][j] > 0)
		{
			i = getNextObject(i, j);
			l.add(this.objectLabels[i]);
			j -= this.objectWeigths[i];
			i--; //No repetitions
			if(j == 0) break;
		}

		return l.toArray(new String[0]);
	}
}
