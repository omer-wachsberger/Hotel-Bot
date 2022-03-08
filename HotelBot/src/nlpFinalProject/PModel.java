package nlpFinalProject;

import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.classifiers.functions.SMO;

public class PModel {
	private Instances trainingSet;

	//constructor
	public PModel(Instances trainingSet) {
		this.trainingSet = trainingSet;
	}

	public FilteredClassifier createClassifier() throws Exception {
		//Initiate classifier object
		FilteredClassifier classifier = new FilteredClassifier();

		// preprocess the text);
		TextualPreprocessing textualPreprocessing = new TextualPreprocessing();
		classifier.setFilter(textualPreprocessing.getFilter());

		// Build a classifier with SMO algorithm
		classifier.setClassifier(new SMO());

		// build classifier and make the predictions
		classifier.buildClassifier(this.trainingSet);

		return classifier;
	}
}