package nlpFinalProject;
import weka.classifiers.Classifier;
import weka.core.Instances;

/*
 Hi Shachar and Yair,
 We chose to create a hotel automatic costumer service bot.
 There are two options to use our bot's ui.
 You can write manually your own massages and click "send" and you can also click on the "generate" 
 button to generate a random message and click on the "send" button to send it to the W&R Hotel bot.
 We chose to use the SMO Algorithm and our precision result is 85%. 
 In addition, accuracy and correct/incorrect instances appear in the console. 
 */
public class Main {

	public static void main(String[] args) {

		try {

			Prediction prediction = new Prediction("data\\data.arff");
			Instances trainingSet = prediction.getTrainingSet();
			Classifier classifier = prediction.getClassifier();
			System.out.println("Hello and welcome to W&R Hotel" );
			System.out.println("==============================");
			System.out.println("Our Chat Bot GUI will come up in a few moments.");
			System.out.println("==== Summary ====");
			prediction.printPrecisionAndCross(classifier, trainingSet, 10);
			

			ChatBot chat = ChatBot.getInstance(Answers.openingMessage);
			chat.setClassifier(classifier);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

	
	