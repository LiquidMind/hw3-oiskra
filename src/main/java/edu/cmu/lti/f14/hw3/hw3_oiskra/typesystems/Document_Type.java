
/* First created by JCasGen Wed Oct 22 21:33:04 EDT 2014 */
package edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Oct 22 21:33:04 EDT 2014
 *  */
public class Document_Type extends Annotation_Type {
  /**  
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /**  */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Document_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Document_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Document(addr, Document_Type.this);
  			   Document_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Document(addr, Document_Type.this);
  	  }
    };
  /**  */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Document.typeIndexID;
  /**  
      */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
 
  /**  */
  final Feature casFeat_relevanceValue;
  /**  */
  final int     casFeatCode_relevanceValue;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRelevanceValue(int addr) {
        if (featOkTst && casFeat_relevanceValue == null)
      jcas.throwFeatMissing("relevanceValue", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    return ll_cas.ll_getIntValue(addr, casFeatCode_relevanceValue);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRelevanceValue(int addr, int v) {
        if (featOkTst && casFeat_relevanceValue == null)
      jcas.throwFeatMissing("relevanceValue", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    ll_cas.ll_setIntValue(addr, casFeatCode_relevanceValue, v);}
    
  
 
  /**  */
  final Feature casFeat_queryID;
  /**  */
  final int     casFeatCode_queryID;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQueryID(int addr) {
        if (featOkTst && casFeat_queryID == null)
      jcas.throwFeatMissing("queryID", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    return ll_cas.ll_getIntValue(addr, casFeatCode_queryID);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQueryID(int addr, int v) {
        if (featOkTst && casFeat_queryID == null)
      jcas.throwFeatMissing("queryID", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    ll_cas.ll_setIntValue(addr, casFeatCode_queryID, v);}
    
  
 
  /**  */
  final Feature casFeat_text;
  /**  */
  final int     casFeatCode_text;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /**  */
  final Feature casFeat_tokenList;
  /**  */
  final int     casFeatCode_tokenList;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTokenList(int addr) {
        if (featOkTst && casFeat_tokenList == null)
      jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    return ll_cas.ll_getRefValue(addr, casFeatCode_tokenList);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokenList(int addr, int v) {
        if (featOkTst && casFeat_tokenList == null)
      jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    ll_cas.ll_setRefValue(addr, casFeatCode_tokenList, v);}
    
  
 
  /**  */
  final Feature casFeat_cosineSimilarity;
  /**  */
  final int     casFeatCode_cosineSimilarity;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getCosineSimilarity(int addr) {
        if (featOkTst && casFeat_cosineSimilarity == null)
      jcas.throwFeatMissing("cosineSimilarity", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_cosineSimilarity);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCosineSimilarity(int addr, double v) {
        if (featOkTst && casFeat_cosineSimilarity == null)
      jcas.throwFeatMissing("cosineSimilarity", "edu.cmu.lti.f14.hw3.hw3_oiskra.typesystems.Document");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_cosineSimilarity, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * 
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Document_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_relevanceValue = jcas.getRequiredFeatureDE(casType, "relevanceValue", "uima.cas.Integer", featOkTst);
    casFeatCode_relevanceValue  = (null == casFeat_relevanceValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_relevanceValue).getCode();

 
    casFeat_queryID = jcas.getRequiredFeatureDE(casType, "queryID", "uima.cas.Integer", featOkTst);
    casFeatCode_queryID  = (null == casFeat_queryID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_queryID).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_tokenList = jcas.getRequiredFeatureDE(casType, "tokenList", "uima.cas.FSList", featOkTst);
    casFeatCode_tokenList  = (null == casFeat_tokenList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenList).getCode();

 
    casFeat_cosineSimilarity = jcas.getRequiredFeatureDE(casType, "cosineSimilarity", "uima.cas.Double", featOkTst);
    casFeatCode_cosineSimilarity  = (null == casFeat_cosineSimilarity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cosineSimilarity).getCode();

  }
}



    