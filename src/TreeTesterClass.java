import java.util.List;

public abstract class TreeTesterClass {
	static int random = 0;
	public static float TreeTester(Node rootNode, File file){
		float accuracy = 0;
		int correct = 0;
		int total = 0;
		
//		System.out.println(rootNode.getTargetAttribute());
//		System.out.println(rootNode.getAttribute());
//		for (Node child : rootNode.getChildren()) {
//			System.out.println(child.getAttribute());
//		}
//		
		
		for (Instance instance : file.InstanceSet) {
			total++;
			if( Classifier(rootNode, instance) == instance.getClassLabel()){
				correct++;
			}		
		}
		
		accuracy = (float)correct/ total;
		System.out.println("Total: "+total+" correct: "+correct);
		System.out.println(accuracy*100);
		System.out.println(random);
		return accuracy;
	}
	
	public static int Classifier(Node node, Instance instance){
		int label = 0;
		int state = 0;
		if( node.getTargetAttribute()!= -1){
			List<Node> children = node.getChildren();
			for (Node child : children) {
				if (instance.getFeatureValue((node.getTargetAttribute())) == child.getTargetAttribute()){
					label = Classifier(child, instance);
					state = 1;
					continue;
				}
			}
			if (state == 0) {
				return Majority(node);
			}
		}
		else{
			return Majority(node);
		}
		return label;
		
	}
	
	private static int Majority(Node node){
		int positive = 0;
		int negative = 0;
		
		if (random >4822) {
			return 0;
		}
		random++;
		for (Instance instance : node.getInstances()) {
			if (instance.getClassLabel() == 0) {
				positive++;
			}
			else {
				negative++;
			}
		}
		
		if (positive == negative) {
			return 0;
		}
		else if (negative > positive) {
			return 1;
		}
		else{
			return (int) Math.random() %2;
		}
	}
	
	
	
}

