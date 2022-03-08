package nlpFinalProject;

import weka.core.SelectedTag;
import weka.core.stemmers.LovinsStemmer;
import weka.core.stopwords.Rainbow;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.filters.Filter;

public class TextualPreprocessing {

	private Filter filter;

	//gets method
	public Filter getFilter() {
		return filter;
	}
	
	// preprocess the the messages data.
	public TextualPreprocessing() {
		
		StringToWordVector stringToWordVector = new StringToWordVector();
		stringToWordVector.setIDFTransform(true);
		stringToWordVector.setTFTransform(true);
		stringToWordVector.setLowerCaseTokens(true);
		stringToWordVector.setInvertSelection(false);
		stringToWordVector.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
		stringToWordVector.setStemmer(new LovinsStemmer());
		stringToWordVector.setStopwordsHandler(new Rainbow());
		stringToWordVector.setWordsToKeep(1000);
		stringToWordVector.setDoNotOperateOnPerClassBasis(false);
		stringToWordVector.setMinTermFreq(1);
		stringToWordVector.setAttributeIndices("last");
		stringToWordVector.setOutputWordCounts(true);
		// Tokenizer
		NGramTokenizer nGramTokenizer = new NGramTokenizer();
		nGramTokenizer.setNGramMaxSize(3);
		nGramTokenizer.setNGramMinSize(1);
		stringToWordVector.setTokenizer(nGramTokenizer);

		filter = stringToWordVector;
	}
}