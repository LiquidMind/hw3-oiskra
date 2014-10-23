package edu.cmu.lti.f14.hw3.hw3_oiskra.annotators;

import java.util.*;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document;
import edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Token;
import edu.cmu.lti.f14.hw3.hw3_oiskra.utils.Utils;

/*
 * Uses sentences loaded by DocumentReader, splits them into tokens, and creates sparse vector
 */
public class DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iter = jcas.getAnnotationIndex().iterator();
		if (iter.isValid()) {
			iter.moveToNext();
			Document doc = (Document) iter.get();
			createTermFreqVector(jcas, doc);
		}

	}

	/**
   * A basic white-space tokenizer, it deliberately does not split on punctuation!
   *
	 * @param doc input text
	 * @return    a list of tokens.
	 */

	List<String> tokenize0(String doc) {
	  List<String> res = new ArrayList<String>();
	  
	  for (String s: doc.split("\\s+"))
	    res.add(s);
	  return res;
	}

	/**
	 * 
	 * @param jcas
	 * @param doc
	 */

	private void createTermFreqVector(JCas jcas, Document doc) {

		String docText = doc.getText();
		
		// Get tokens list using standard tokenizer0
		List<String> stringList = tokenize0(docText);
    
		// Save data into HashMap
		HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
		
		for (String text:stringList) {
		  if(frequencies.containsKey(text))
      {
		    // Increase frequency of the term
		    frequencies.put(text, frequencies.get(text) + 1);
      }
      else {
        // Set initial terms frequency to 1
        frequencies.put(text, 1);
      }
		}
		
		// Convert found parts to the tokens and add them to the CAS
		List<Token> tokenList = new ArrayList<Token>();
		
		for(String text:frequencies.keySet())
    {
      Token token = new Token(jcas);
      token.setText(text);
      token.setFrequency(frequencies.get(text));
      tokenList.add(token);
      
      // Print tokens just to see if all is OK
      //System.out.println(text + " - " + frequencies.get(text));
    }
    doc.setTokenList(Utils.fromCollectionToFSList(jcas, tokenList));
	}

}
