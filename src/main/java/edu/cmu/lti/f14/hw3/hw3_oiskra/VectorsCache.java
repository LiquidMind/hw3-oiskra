package edu.cmu.lti.f14.hw3.hw3_oiskra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class VectorsCache {
  /*
   * Only instance of VectorsCache for singleton needs 
   */
  private static VectorsCache cache;
  
  /*
   * Map from the questions IDs to the list of the tokens of a question 
   */
  private Map<Integer, HashMap<String, Integer>> queries;
  
  /*
   * Map from the questions IDs to the list of all related document vectors 
   */
  private Map<Integer, ArrayList<DocumentVector>> documents;
  
  /*
   * Set with all stop words
   */
  private HashSet<String> stopWords;

  /*
   * Private constructor that initializes all internal variables
   */
  private VectorsCache()
  {
    queries = new HashMap<Integer,HashMap<String,Integer>>();
    documents = new HashMap<Integer,ArrayList<DocumentVector>>();
    stopWords = new HashSet<String>();
  }
  
  
  /*
   * Get the instance of singleton class if exists or create new one otherwise
   */
  public static VectorsCache getInstance()
  {
    if(cache == null)
      cache = new VectorsCache();
    return cache;
  }
  
  /*
   * Get all bunch of queries
   */
  public Map<Integer, HashMap<String, Integer>> getQueries() {
    return queries;
  }

  
  /*
   * Set all bunch of queries
   */
  public void setQueries(Map<Integer, HashMap<String, Integer>> queries) {
    this.queries = queries;
  }

  /*
   * Get all bunch of documents
   */
  public Map<Integer, ArrayList<DocumentVector>> getDocuments() {
    return documents;
  }

  /*
   * Set all bunch of documents
   */
  public void setDocuments(Map<Integer, ArrayList<DocumentVector>> documents) {
    this.documents = documents;
  }
  
  /*
   * Get all bunch of stop words
   */
  public HashSet<String> getStopWords() {
    return stopWords;
  }

  /*
   * Set all bunch of stop words
   */
  public void setStopWords(HashSet<String> stopWords) {
    this.stopWords = stopWords;
  }
}
