import java.util.ArrayList;
import java.util.List;
public class testTrees {

	public static void Start(String fileName) {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		List<Node> trees=new ArrayList();
		
		for (int i = 0; i < 100;i++) {			
			trees.add(i,RandomTreeBuilder.BuildTree(file));
			}
		
//		for (int i = 0; i < TreeBuilderClass.splitCount.length; i++) {
//			System.out.println("Feature " + i + " count : " + TreeBuilderClass.splitCount[i]);
//		}
		
		float accuracy;
		accuracy = randomClassifier.TreeTester(trees, file);
			
		System.out.println("Tree Classification Accuracy: " + accuracy);
		
		}
		
	}
