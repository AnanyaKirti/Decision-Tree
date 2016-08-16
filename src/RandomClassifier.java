	import java.util.List;


public abstract class RandomClassifier{
	public static float TreeTester(List<Node> trees, File file){
			float accuracy = 0;
			int correct = 0;
			int incorrect=0;
			int totalCorrect=0;
			int total = 0;
			
			for (Instance instance : file.InstanceSet) {
				total++;
				correct = 0;
				incorrect=0;
				for (int i = 0; i < trees.size(); i++) {
			
					if( Classifier(trees.get(i), instance) == instance.getClassLabel()){
						correct++;
					}
					else {
						incorrect++;
					}
					
				}
				if (correct>incorrect){
					totalCorrect++;
				}	
		
	
			
					
			}
			accuracy = (float)totalCorrect/ total *100;
//			System.out.println("Total: "+total+" correct: "+correct);
//			System.out.println(accuracy*100);
			return accuracy;
		}
		
		public static int Classifier(Node node, Instance instance){
			int label = 0;
			int state = 0;
			if( node.hasChildren() == 1){
				List<Node> children = node.getChildren();
				for (Node child : children) {
					if (instance.getFeatureValue((node.getTargetAttribute())) == child.getTargetAttributeValue()){
						label = Classifier(child, instance);
						state = 1;
						break;
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
			for (Instance instance : node.getInstances()) {
				if (instance.getClassLabel() == 0) {
					positive++;
				}
				else {
					negative++;
				}
			}
			if (positive > negative) {
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


