import java.util.ArrayList;
import java.util.List;

/**
 * Class for experiment 4.
 */
public class Test4 {
	public static void Start(String fileName) {
		File file = new File(fileName);
		List<Node> forest = new ArrayList<Node>();
		
		float accuracy;
		int numberOfTrees = 20;
		for(int j = 1; j < numberOfTrees; j++){
			for (int i = 0; i < j; i++) {
				forest.add(i, RandomTreeBuilder.BuildTree(file));
			}
			accuracy = RandomClassifier.TreeTester(forest, file);
			System.out.println("Forest" + j +" Classification Accuracy: " + accuracy);
		}
	}
}
