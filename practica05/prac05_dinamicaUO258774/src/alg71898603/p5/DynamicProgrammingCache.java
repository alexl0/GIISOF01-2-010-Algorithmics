package alg71898603.p5;

public abstract class DynamicProgrammingCache {
	protected String[] objectLabels; 
	protected int[] objectValues;
	protected int[] objectWeigths;
	protected int[][] cache;

	protected int maxWeigth;
	protected int maxValue;

	protected int h_space = 3;

	public int getMaxValue(){
		return this.maxValue;
	}

	protected DynamicProgrammingCache(){
		this.objectLabels = null; 
		this.objectValues = null;
		this.objectWeigths = null;
		this.cache = null;

		this.maxWeigth = 0;
		this.maxValue = -1;

	}

	public DynamicProgrammingCache(String[] labels, int[] values, int[] weights, int max) {
		this.objectLabels = labels;
		this.objectValues = values;
		this.objectWeigths = weights;

		this.maxWeigth = max;

		//Cache table initialization
		this.cache = new int[objectLabels.length][maxWeigth+1];
	}

	//fill-in cache table 
	public void updateTable() {
		for(int i = 0; i < objectLabels.length; i++)
			this.cache[i][0] = 0;
		for(int j = 1; j < maxWeigth + 1; j++) {
			for(int i = 0; i < objectLabels.length; i++) {
				updateCell(i, j);
			}
		}
		this.maxValue = this.cache[objectLabels.length-1][maxWeigth];
	}

	protected abstract void updateCell(int i, int j);

	protected abstract int defaultValueForCell(int i, int j);

	public abstract String[] getBestElements();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int keyMaxLen = 0;
		//Some calculations
		for (String s : this.objectLabels) {
			if(s.length() > keyMaxLen)
				keyMaxLen = s.length();
		}


		//Print header
		String padString = String.format("%s%d%s", "%1$", keyMaxLen, "s|");
		String padCell = String.format("%s%d%s", "%1$", h_space, "d|");
		sb.append(String.format(padString, ""));
		for(int i = 0; i < this.maxWeigth + 1; i++)
			sb.append(String.format(padCell, i));
		sb.append('\n');
		//Print rows
		for(int i = 0; i < this.objectLabels.length; i++) {
			sb.append(String.format(padString, objectLabels[i]));
			for(int j = 0; j < this.maxWeigth + 1; j++) {
				sb.append(String.format(padCell, this.cache[i][j]));
			}
			sb.append('\n');
		}

		return sb.toString();
	}

}
