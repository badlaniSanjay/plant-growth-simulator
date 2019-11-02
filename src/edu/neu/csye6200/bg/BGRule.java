
package edu.neu.csye6200.bg;

/**
 *
 * @author sanjay badlani
 * NUID : 001237234
 * This class defines a set of rules on the basis of which the plant grows.
 */
public class BGRule {
    
    private Boolean extendTrunk;
    
    private Integer noOfChildsForTrunk;
    
    private Integer noOfChildsForBranch;
    
    private Integer lengthOfBark;
    
    private Integer lengthOfChild;
    
    private int[] localDirectionOfChild;

    public BGRule() {
    }
    
    

    public BGRule(Boolean extendTrunk, Integer noOfChildsForTrunk, Integer noOfChildsForBranch, Integer lengthOfBark, Integer lengthOfChild) {
        this.extendTrunk = extendTrunk;
        this.noOfChildsForTrunk = noOfChildsForTrunk;
        this.noOfChildsForBranch = noOfChildsForBranch;
        this.lengthOfBark = lengthOfBark;
        this.lengthOfChild = lengthOfChild;
    }

    public Boolean getExtendTrunk() {
        return extendTrunk;
    }

    public void setExtendTrunk(Boolean extendTrunk) {
        this.extendTrunk = extendTrunk;
    }

    public Integer getNoOfChildsForTrunk() {
        return noOfChildsForTrunk;
    }

    public void setNoOfChildsForTrunk(Integer noOfChildsForTrunk) {
        this.noOfChildsForTrunk = noOfChildsForTrunk;
    }

    public Integer getNoOfChildsForBranch() {
        return noOfChildsForBranch;
    }

    public void setNoOfChildsForBranch(Integer noOfChildsForBranch) {
        this.noOfChildsForBranch = noOfChildsForBranch;
    }

    public Integer getLengthOfBark() {
        return lengthOfBark;
    }

    public void setLengthOfBark(Integer lengthOfBark) {
        this.lengthOfBark = lengthOfBark;
    }

    public Integer getLengthOfChild() {
        return lengthOfChild;
    }

    public void setLengthOfChild(Integer lengthOfChild) {
        this.lengthOfChild = lengthOfChild;
    }

    public int[] getLocalDirectionOfChild() {
        return localDirectionOfChild;
    }

    public void setLocalDirectionOfChild(int[] localDirectionOfChild) {
        this.localDirectionOfChild = localDirectionOfChild;
    }
    
    
    
    
    @Override
    public String toString() {
        return "BGRule{" + "extendTrunk=" + extendTrunk + ", noOfChildsForTrunk=" + noOfChildsForTrunk + ", noOfChildsForBranch=" + noOfChildsForBranch + ", lengthOfBark=" + lengthOfBark + ", lengthOfChild=" + lengthOfChild + ", localDirectionOfChild=" + localDirectionOfChild + '}';
    }

    
    
    
    
}
