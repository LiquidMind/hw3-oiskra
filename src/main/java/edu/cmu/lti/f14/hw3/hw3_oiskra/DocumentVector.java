package edu.cmu.lti.f14.hw3.hw3_oiskra;

import java.util.HashMap;

/*
 * Helper class for vectors cache needs
 */
public class DocumentVector {
    private Integer queryID;
    private Integer relevance;
    private Integer rank;
    private Double  cosineSimilarity;
    private String text;
    private HashMap<String, Integer> tokens;
    
    public DocumentVector(
            Integer queryID, 
            Integer relevance,
            Integer rank,
            Double cosineSimilarity, 
            String text
    ) {
      this.queryID = queryID;
      this.relevance = relevance;
      this.rank = rank;
      this.cosineSimilarity = cosineSimilarity;
      this.text = text;
      tokens = new HashMap<String,Integer>();
    }
    
    /*
     * Getter for queryID
     */
    public Integer getQueryID() {
      return queryID;
    }

    /*
     * Setter for queryID
     */
    public void setQueryID(Integer queryID) {
      this.queryID = queryID;
    }
    
    /*
     * Getter for relevance
     */
    public Integer getRelevance() {
      return relevance;
    }
    
    /*
     * Setter for relevance
     */
    public void setRelevance(Integer relevance) {
      this.relevance = relevance;
    }
    
    /*
     * Getter for rank
     */
    public Integer getRank() {
      return rank;
    }
    
    /*
     * Setter for rank
     */
    public void setRank(Integer rank) {
      this.rank = rank;
    }
    
    /*
     * Getter for cosineSimilarity
     */
    public Double getCosineSimilarity() {
      return cosineSimilarity;
    }
    
    /*
     * Setter for cosineSimilarity
     */
    public void setCosineSimilarity(Double cosineSimilarity) {
      this.cosineSimilarity = cosineSimilarity;
    }
    
    /*
     * Getter for text
     */
    public String getText() {
      return text;
    }
    
    /*
     * Setter for text
     */
    public void setText(String text) {
      this.text = text;
    }
    
    /*
     * Getter for tokens
     */
    public HashMap<String, Integer> getTokens() {
      return tokens;
    }
    
    /*
     * Setter for tokens
     */
    public void setTokens(HashMap<String, Integer> tokens) {
      this.tokens = tokens;
    }
}