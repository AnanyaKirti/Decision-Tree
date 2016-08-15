import apple.laf.JRSUIUtils.Tree;

public class DecisionTree {

	/**
	 * 
	 * @param args input the file, as well as the test number
	 */
	public static void main(String[] args){
		// TODO use args
		
		long startTime = System.nanoTime();
		int testNumber = Integer.parseInt(args[1]);
		if ( testNumber == 1 ) {
			Test1.Start(args[0]);			
		}
		else if (testNumber == 2 ) {
			Test2.Start(args[0]);
		}
		
		
		long executionTime = (System.nanoTime() - startTime) / 1000000000;
		System.out.print("Time for execution " + executionTime);
		
	}
}
	
	
	
