package nlpFinalProject;

import java.util.Random;
import weka.classifiers.evaluation.Evaluation;
import weka.core.converters.ConverterUtils;
import nlpFinalProject.Answers.ClassAttributesValues;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;


public class Prediction {

	private static Instances trainingSet = null;

	//constructor
	public Prediction(String fileLocation) throws Exception {
		loadData(fileLocation);
	}
	
	//gets method
	public Instances getTrainingSet() {
		return Prediction.trainingSet;
	}
	
	public Classifier getClassifier() {
		if (trainingSet == null) {
			System.out.println("Data File is empty");
			return null;
		}
		
		Classifier classifier = null;
		try {
			trainingSet.setClassIndex(0);
			PModel pModel = new PModel(trainingSet);
			classifier = pModel.createClassifier();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return classifier;
	}

	// Loading the data
	public boolean loadData(String path) throws Exception {
		ConverterUtils.DataSource source = new ConverterUtils.DataSource(path);
		Prediction.trainingSet = source.getDataSet(); 
		return null != trainingSet;
	}
	
	// returns prediction for the guest's message.
	public static String predictTextByModel(Classifier classifier, String message) {
		int prediction = 0;
		ClassAttributesValues attribute = null;
		try {
			TextualInstance textualInstance = new TextualInstance(message);
			Instance mInstance = textualInstance.getInstance();
			prediction = (int) classifier.classifyInstance(mInstance);
			attribute = ClassAttributesValues.values()[prediction];
		} catch (Exception e) {
			System.out.println(e);
		}
		//returns the optimal answer to the guest
		String ans= Answers.getTextForAttribute(attribute);
		return ans;
	}

	//Prints the precision and cross validation
	public void printPrecisionAndCross(Classifier classifier, Instances traningSet, int crossValidationFolds) {
		try {
			Evaluation eval = new Evaluation(traningSet);
			eval.crossValidateModel(classifier, traningSet, crossValidationFolds, new Random(1));
			System.out.println("Correct instances " + eval.correct());
			System.out.println("Incorrect instances " + eval.incorrect());
			System.out.println("Accuracy: " + eval.pctCorrect());
			int p = Attribute.STRING;
			System.out.println("Precision: " + (eval.precision(p)));
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
