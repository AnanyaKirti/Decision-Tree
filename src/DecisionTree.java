import java.io.*;
import java.util.*;


public class DecisionTree {

	
	
	/**
	 * 
	 * @param args input the file, as well as the test number
	 */
	public static void main(String[] args){
		// TODO use args
		
		long startTime = System.nanoTime();
		
		File file = new File("ticdata2000.txt");
		TreeBuilderClass.BuildTree(file);
		
//		for (int i = 0; i < TreeBuilderClass.splitCount.length; i++) {
//			System.out.println("Feature " + i + " count : " + TreeBuilderClass.splitCount[i]);
//		}
		long executionTime = ((long)System.nanoTime() - startTime) / 1000000000;
		System.out.print("Time for execution " + executionTime);

		
//		List<Instance> TrainingSet = file.TrainingSet;
//		List<Instance> TestSet = file.InstanceSet;
//		int numberOfInstances = file.numberOfInstances;
//		int numberOfFeatures= file.numberOfFeatures;
		
	}
}
	
	
	
