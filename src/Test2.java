import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class for experiment 2.
 */
public abstract class Test2 {
	public static void Start(String fileName) {

		File file = new File(fileName);
		float errorPercentage = 10f;
		System.out.println("Error percentage is " + errorPercentage);
		addNoise(file.TrainingSet, errorPercentage);

		Node rootNode = TreeBuilderClass.BuildTree(file);

		float accuracy;
		accuracy = TreeTesterClass.TreeTester(rootNode, file);
		System.out.println("Tree Classification Accuracy: " + accuracy);
		System.out.println("Number of Nodes in the tree " + TreeBuilderClass.numberOfNodes);

	}

	/**
	 * Static function to add Noise to the training dataset
	 * 
	 * @param TrainingSet
	 *            The dataset
	 * @param errorPercentage
	 *            The error percentage to be introduced.
	 */
	private static void addNoise(List<Instance> TrainingSet, float errorPercentage) {
		int range = (int) (TrainingSet.size() * errorPercentage / 100);
		if (0 < errorPercentage && errorPercentage < 100) {
			for (int i = 0; i < range; i++) {
				TrainingSet.get(i).setClassLabel((int) Math.random() % 2);

			}
			long seed = System.nanoTime();
			Collections.shuffle(TrainingSet, new Random(seed));
		} else {
			System.err.println("Please enter a percentage of error!!");
		}
	}

}
