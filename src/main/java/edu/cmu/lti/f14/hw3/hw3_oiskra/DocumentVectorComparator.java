package edu.cmu.lti.f14.hw3.hw3_oiskra;

import java.util.Comparator;

public class DocumentVectorComparator implements Comparator<Object> {
  @Override
  public int compare(Object arg0, Object arg1) {
    // TODO Auto-generated method stub
    
    // Return the document with higher cosineSImilarity
    if (((DocumentVector) arg0).getCosineSimilarity() < ((DocumentVector) arg1).getCosineSimilarity())
      return 1;
    
    if (((DocumentVector) arg0).getCosineSimilarity() > ((DocumentVector) arg1).getCosineSimilarity())
      return -1;
    
    // If cosineSimilarities are similar return document with the higher relevance
    if (((DocumentVector) arg0).getRelevance() != 1 && ((DocumentVector) arg1).getRelevance() == 1)
      return 1;
    
    if (((DocumentVector) arg0).getRelevance() == 1 && ((DocumentVector) arg1).getRelevance() != 1) 
      return -1;

    // It shouldn't happens but if both cosineSimilarity and relevance are similar return 0
    return 0;
  }     
}