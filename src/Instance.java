/**
 * The instance class is used to represent an instance 
 * 
 * @param s The String is is used to collect the feature set of the instance. 
 * The last item of the string is the class label
 */
class Instance{
	private int classLabel = -1;
	private int[] featureList;
	/**
	 * @param s The String is is used to collect the feature set of the instance. 
	 * The last item of the string is the class label
	 */
	Instance(String s){
		try{
			featureList = toIntArray(s);
		}
		catch (Exception c){
			System.out.println("error in instance with featurs :" + s);
		}
	}
	
	/**
	 * Method to get the ClassLabel
	 * 
	 * @return classLabel returns the class label associated with the instance/
	 * returns -1 if error.
	 */
	public int getClassLabel(){
		return this.classLabel;
	}
	
	/**
	 * Method to set the class label
	 * 
	 * @param i set the class label to the value i
	 */
	public void setClassLabel(int i){
		this.classLabel = i;
	}
	
	/**
	 * Method to convert a string to an int array.
	 * Also sets the classlabel of the instance.
	 * 
	 * @param input The string s
	 * @return the int array which represents the feature set of the instance.
	 */
	public int[] toIntArray(String input) {
	    String[] split = input.split("\\s+");
	    int[] result = new int[split.length -1];
	    for (int i = 0; i < split.length -1; i++) {
	        result[i] = Integer.parseInt(split[i]);
	    }
	    classLabel = Integer.parseInt(split[split.length - 1]);
	    return result;
	}
	
	
	public int[] getAllFeatureValue(){
		return featureList;
	}
	
	/**
	 * Method to get the value of the required feature.
	 *  
	 * @param i The ith feature
	 * @return	returns the value of the feature.
	 */
	public int getFeatureValue(int i){
		try {
			return featureList[i];
		} catch (Exception e) {
			System.out.println("Error! in getting Feature.");
			return -1;
		}
	}
}

