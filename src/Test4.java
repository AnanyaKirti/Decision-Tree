import java.util.ArrayList;
import java.util.List;

public class Test4 {
	public static void Start(String fileName) {
		File file = new File(fileName);
		List<Node> forest = new ArrayList<Node>();
		
		for (int i = 0; i < 100; i++) {			
			forest.add(i,RandomTreeBuilder.BuildTree(file));
			}
		
		float accuracy;
		accuracy = RandomClassifier.TreeTester(forest, file);

		System.out.println("Tree Classification Accuracy: " + accuracy);
		}
		
	}
