import java.io.*;
import java.util.*;


public class DecisionTree {

	
	
	/**
	 * 
	 * @param args input the file, as well as the test number
	 */
	public static void main(String[] args){
		// TODO use args
		
		
		File file = new File("ticdata2000.txt");
		TreeBuilderClass.BuildTree(file);
//		System.out.println(File.FeatureValues.get(0));
		
		
		
//		List<Instance> TrainingSet = file.TrainingSet;
//		List<Instance> TestSet = file.InstanceSet;
//		int numberOfInstances = file.numberOfInstances;
//		int numberOfFeatures= file.numberOfFeatures;
		
	}
}
	
	
	
