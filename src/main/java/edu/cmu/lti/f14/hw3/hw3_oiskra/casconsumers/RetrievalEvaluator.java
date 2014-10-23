package edu.cmu.lti.f14.hw3.hw3_oiskra.casconsumers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import edu.cmu.lti.f14.hw3.hw3_oiskra.DocumentVector;
import edu.cmu.lti.f14.hw3.hw3_oiskra.DocumentVectorComparator;
import edu.cmu.lti.f14.hw3.hw3_oiskra.VectorsCache;
import edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document;
import edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Token;
import edu.cmu.lti.f14.hw3.hw3_oiskra.utils.Utils;


public class RetrievalEvaluator extends CasConsumer_ImplBase {

	/** query id number **/
	public ArrayList<Integer> qIdList;

	/** query and text relevant values **/
	public ArrayList<Integer> relList;

		
	public void initialize() throws ResourceInitializationException {

		qIdList = new ArrayList<Integer>();

		relList = new ArrayList<Integer>();

	}

	/*
	 * Utility function to compute average documents length
	 */
  private double computeAverageDocumentsLength(ArrayList<DocumentVector> documents) {
    double result = 0.0;
    for (DocumentVector document : documents)
      result += document.getTokens().size();
    return result / documents.size();
  }
	
  /*
   * Utility function to compute documents frequency
   */
  private Map<String, Integer> getDocumentsFrequency(ArrayList<DocumentVector> documents,
          Map<String, Integer> query) {
    Map<String, Integer> frequencies = new HashMap<String, Integer>();
    int count = 0;
    for (String token : query.keySet()) {
      count = 0;
      for (DocumentVector document : documents) {
        if (document.getTokens().containsKey(token))
          count++;
      }
      frequencies.put(token, count);
    }
    return frequencies;
  }
  
  /**
   * Print documentVector to the outpur file in required format
   * @param documentVector
   * @param writer
   */
  private void printDocumentResults(DocumentVector documentVector, PrintWriter writer) 
  {
    writer.println(
            "cosine=" + String.format("%.4f", documentVector.getCosineSimilarity())
            + "\trank=" + documentVector.getRank()
            + "\tqid=" + documentVector.getQueryID()
            + "\trel=" + documentVector.getRelevance()
            + "\t" + documentVector.getText()
    );
  }

  
	/**
	 * TODO :: 1. construct the global word dictionary 2. keep the word
	 * frequency for each sentence
	 */
	@Override
  public void processCas(CAS aCas) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCas.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    FSIterator it = jcas.getAnnotationIndex(Document.type).iterator();

    if (it.hasNext()) {
      Document doc = (Document) it.next();

      // Make sure that your previous annotators have populated this in CAS
      FSList fsTokenList = doc.getTokenList();
      // ArrayList<Token>tokenList=Utils.fromFSListToCollection(fsTokenList, Token.class);

      qIdList.add(doc.getQueryID());
      relList.add(doc.getRelevanceValue());

      // Do something useful here

      HashMap<String, Integer> queryTokens;
      HashMap<String, Integer> documentTokens;
      VectorsCache cache = VectorsCache.getInstance();

      if (doc.getRelevanceValue() == 99) {
        ArrayList<Token> tokenList = Utils.fromFSListToCollection(fsTokenList, Token.class);
        queryTokens = new HashMap<String, Integer>();
        for (Token token : tokenList) {
          queryTokens.put(token.getText(), token.getFrequency());
        }
        cache.getQueries().put(doc.getQueryID(), queryTokens);
      } else {
        if (!cache.getDocuments().containsKey(doc.getQueryID())) {
          cache.getDocuments().put(doc.getQueryID(), new ArrayList<DocumentVector>());
        }
        ArrayList<DocumentVector> documentsList = cache.getDocuments().get(doc.getQueryID());
        ArrayList<Token> tokenList = Utils.fromFSListToCollection(fsTokenList, Token.class);

        documentTokens = new HashMap<String, Integer>();
        for (Token token : tokenList) {
          documentTokens.put(token.getText(), token.getFrequency());
        }
        DocumentVector documentVector = new DocumentVector(
                doc.getQueryID(),
                doc.getRelevanceValue(), 
                0, 
                doc.getCosineSimilarity(), 
                doc.getText()
        );
        documentVector.setTokens(documentTokens);
        documentsList.add(documentVector);
        cache.getDocuments().put(doc.getQueryID(), documentsList);
      }
    }
  }

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	@Override
	public void collectionProcessComplete(ProcessTrace arg0)
			throws ResourceProcessException, IOException {

		super.collectionProcessComplete(arg0);

		// TODO :: compute the cosine similarity measure
		
		// Get the instance of VectorsCache
		VectorsCache cache = VectorsCache.getInstance();
		
    Map<Integer, HashMap<String, Integer>> queries = cache.getQueries();
    Map<Integer, ArrayList<DocumentVector>> documents = cache.getDocuments();
    double mmrSum = 0.0;
    
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new FileOutputStream(new File("report.txt"), false));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    
    for(Integer queryID:queries.keySet()) {
      ArrayList<DocumentVector> vectors = documents.get(queryID);
      
      double averageDocumentLength = computeAverageDocumentsLength(vectors);
      
      Map<String,Integer> frequiencies = getDocumentsFrequency(vectors,queries.get(queryID));

      
      for(DocumentVector vector:vectors) {
        vector.setCosineSimilarity(computeCosineSimilarity(queries.get(queryID), vector.getTokens()));
      }
      Collections.sort(vectors, new DocumentVectorComparator());
        
      int rank = 1;
      DocumentVector mmrVector = null;

      // TODO :: compute the rank of retrieved sentences
        
      for(DocumentVector v:vectors) {
        v.setRank(rank++);
        if(v.getRelevance() == 1)
          mmrVector = v;
      }

      mmrSum += 1/((double)mmrVector.getRank());
      printDocumentResults(mmrVector, pw);
    }
		
		// TODO :: compute the metric:: mean reciprocal rank
		double metric_mrr = compute_mrr(mmrSum, queries.size());
		System.out.println(" (MRR) Mean Reciprocal Rank ::" + metric_mrr);
		
    pw.println("MRR=" + String.format("%.4f", metric_mrr));
    pw.close();
	}

	/**
	 * Compute cosine similarity using standard definition from Wikipedia
	 * @param queryVector 
	 * @param documentVector
	 * @return cosine_similarity
	 */
	private double computeCosineSimilarity(
	        Map<String, Integer> queryVector,
	        Map<String, Integer> documentVector
	) {
	  double cosine_similarity = 0.0;
		// TODO :: compute cosine similarity between two sentences
		
    double dotProduct = 0.0;
    double queryEuclideanLength = 0.0;
    double documentEuclideanLength = 0.0;
    
    for(String query: queryVector.keySet())
    {
      if(documentVector.containsKey(query))
        dotProduct += queryVector.get(query) * documentVector.get(query);
    }
    
    for (Integer frequency:queryVector.values()) {
      queryEuclideanLength += frequency * frequency;
    }
    
    for(Integer frequency:documentVector.values()) {
      documentEuclideanLength += frequency*frequency;
    }
    
    queryEuclideanLength = Math.sqrt(queryEuclideanLength);
    documentEuclideanLength = Math.sqrt(documentEuclideanLength);
    
    cosine_similarity = dotProduct/(queryEuclideanLength*documentEuclideanLength);
    
    return cosine_similarity;
	}

	/**
	 * Compute MRR using standard definition from Wikipedia
	 * @param rank
	 * @param count
	 * @return mrr
	 */
	private double compute_mrr(double rank, int count) {
		double metric_mrr=0.0;

		// TODO :: compute Mean Reciprocal Rank (MRR) of the text collection
		
		metric_mrr = rank / count;
		
		return metric_mrr;
	}

}
