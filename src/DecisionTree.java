import java.io.*;
import java.util.*;

public class DecisionTree {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		try (BufferedReader br = new BufferedReader(new FileReader("ticdata2000.txt"))) {
		    int numberOfInstances = Integer.parseInt(br.readLine());
		    System.out.println("number of Instances is: " + numberOfInstances);

		    int numberOfFeatures= Integer.parseInt(br.readLine());
		    System.out.println("number of Features is: " + numberOfFeatures); 
		    
		    int du;
		    String line;
		    List<Instance> InstanceSet = new ArrayList<Instance>();
		    while ((line = br.readLine()) != null) {
		    	InstanceSet.add(new Instance(line));
		    	du = InstanceSet.get(InstanceSet.size()-1).getClassLabel();
		    	System.out.println("from the method "+ du);
		    }
		    
		    long seed = System.nanoTime();
		    Collections.shuffle(InstanceSet, new Random(seed)); // randomised the instance set.
		    List<Instance> TrainingSet ;
		    List<Instance> TestSet;
		}
		catch (Exception e) {
			System.out.println("Error in opening file!");
		}
	}

}


class Instance{
	private int classLabel = -1;
	private int[] featureList;
	Instance(String s){
		try{
			featureList = toIntArray(s);
			System.out.println(classLabel);
		}
		catch (Exception c){
			System.out.println("error in instance");
		}
	}
	public int getClassLabel(){
		return classLabel;
	}
	
	public void setClassLabel(int i){
		classLabel = i;
	}
	
	public int[] toIntArray(String input) {
	    String[] split = input.split("\\s+");
	    int[] result = new int[split.length -1];
	    for (int i = 0; i < split.length -1; i++) {
	        result[i] = Integer.parseInt(split[i]);
	    }
	    classLabel = Integer.parseInt(split[split.length - 1]);
	    return result;
	}
}


