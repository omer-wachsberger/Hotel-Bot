package nlpFinalProject;

import java.util.ArrayList;

import nlpFinalProject.Answers.ClassAttributesValues;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class TextualInstance {
	private static final String DATA_SET_NAME = "DataSet";
	private static final String ATT_MESSAGE = "Message";
	private static final String ATT_CLASS = "class-att";
	private Instance instance;
	
	public TextualInstance(String message) {
		//Generate the class attribute values
        ArrayList<String> classAttributeValues = new ArrayList<String>();
        classAttributeValues.add(ClassAttributesValues.reservation.name());
        classAttributeValues.add(ClassAttributesValues.spa.name());
        classAttributeValues.add(ClassAttributesValues.room_service.name());
        classAttributeValues.add(ClassAttributesValues.complaints.name());
        classAttributeValues.add(ClassAttributesValues.location.name());
        //Create the class attribute with the valid values
        Attribute classAttribute = new Attribute(ATT_CLASS, classAttributeValues);
        
        //Generate the text attribute
        Attribute textAttribute = new Attribute(ATT_MESSAGE, (ArrayList<String>) null);
        
        //Generate the attribute list and fill the attributes
        ArrayList<Attribute> attributes = new ArrayList<Attribute>(2);
        attributes.add(classAttribute);
        attributes.add(textAttribute);

        //Generate the Instances with the related attributes
        Instances instances = new  Instances(DATA_SET_NAME, attributes, 0);
        instances.setClassIndex(0);
        
        //Generate the instance for predication
		instance = new DenseInstance(2);
		instance.setDataset(instances);
		instance.setValue(textAttribute, message);
	}
	
	
	public Instance getInstance() {
		return instance;
	}
}