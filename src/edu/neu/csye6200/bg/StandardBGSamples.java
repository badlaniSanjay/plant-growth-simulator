package edu.neu.csye6200.bg;

/**
 *
 * @author sanjay badlani
 * NUID : 001237234
 * This class defines a set of standard BG Rules
 */
public class StandardBGSamples {
    
   public static BGRule likePineTree = new BGRule(Boolean.TRUE, 6, 0, 75, 40);;
    
   public static  BGRule bushes = new BGRule(Boolean.FALSE, 3, 3, 20, 20);
    
   public static  BGRule normalTreeWithTwoBranches = new BGRule(Boolean.FALSE, 2, 2, 100, 50);
    
   public static  BGRule likeCoconutTree = new BGRule(Boolean.TRUE, 6, 0, 6, 4);
    
   public static BGRule normalTreeWithThreeBranches = new BGRule(Boolean.TRUE, 2, 2, 100, 50);;

    public StandardBGSamples() {
        
    }
    
    
    
    
}
