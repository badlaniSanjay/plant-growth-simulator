
package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 *
 * @author sanjay badlani
 * NUID : 001237234
 * This class is used to grow a set of BGGeneration based on the BGRule provided to it
 */
public class BGGrow {
    
     private static Logger log = Logger.getLogger(BGGrowthEventPublisher.class.getName());
    
    public  ArrayList<BGGeneration> growThisGenerationSet(ArrayList<BGGeneration> givenPlant, BGRule bgRule){
       BGGeneration lastCreatedGeneration = givenPlant.get(givenPlant.size()-1);
       BGGeneration nextGGeneration = growThisGeneration(lastCreatedGeneration, bgRule);
       givenPlant.add(nextGGeneration);
       return givenPlant;
    }
    
    public BGGeneration growThisGeneration(BGGeneration givenGeneration, BGRule bgRule){
        BGGeneration nextBGGeneration = new BGGeneration(givenGeneration.getGenerationCount()+1);
        for(Stem eachStem : givenGeneration.getStemsInThisGeneration()){
           growStem(eachStem, bgRule);
           if(eachStem.getChildStems() != null){
           for(Stem eachChild : eachStem.getChildStems()){
               nextBGGeneration.addStem(eachChild);
           }
           }
           
        }
        return nextBGGeneration;
    }

    private void growStem(Stem eachStem, BGRule bgRule) {
        if(eachStem.getStemType() == StemType.TRUNK){
            
            if(bgRule.getExtendTrunk()){
                eachStem.setNumberOfChildStems(bgRule.getNoOfChildsForTrunk() +1 );
                createTrunkStem(bgRule.getLengthOfBark(), eachStem);
            }else {
            eachStem.setNumberOfChildStems(bgRule.getNoOfChildsForTrunk());
            }
            createChildStem(bgRule.getNoOfChildsForTrunk(), bgRule.getLengthOfChild(), eachStem);
        }
        else if(eachStem.getStemType() == StemType.BRANCH){
            eachStem.setNumberOfChildStems(bgRule.getNoOfChildsForBranch());
            createChildStem(bgRule.getNoOfChildsForBranch(), bgRule.getLengthOfChild(), eachStem);
        }else {
            //throw a new Exception
        }
    }
    
    public void createChildStem(int noOfChildrenToBeSplit, int length, Stem parentStem){
        
        log.info("creating child stem for "+parentStem);
        
        double measureOfEachAngle = 0.0;
        double lastAngle = 0;
        if(noOfChildrenToBeSplit % 2 == 0){
            
            measureOfEachAngle = 90 / (noOfChildrenToBeSplit+2);
        }else {
             measureOfEachAngle = 180 / (noOfChildrenToBeSplit+2);
        }
        for(int i = 0; i<noOfChildrenToBeSplit;i++){
            double xOfChild = parentStem.getEndX();
            double yOfChild = parentStem.getEndY();
            if(i == noOfChildrenToBeSplit/2 && noOfChildrenToBeSplit % 2 == 0){
                lastAngle = lastAngle + 90;
            }else {
                lastAngle = lastAngle + measureOfEachAngle;
            }
            if(parentStem.getLocalDirection() > 90 ){
            lastAngle += (parentStem.getLocalDirection() - 90);
            }else {
               lastAngle += ( 90 - parentStem.getLocalDirection()); 
            }
            Stem childStem = 
                    new Stem(StemType.BRANCH, xOfChild, yOfChild, length, lastAngle, parentStem.getLayer()+1);
            parentStem.addChild(childStem);
        }
    }
    
    public void createTrunkStem(int length, Stem parentStem){
        log.info("creating child trunk for parentStem");
        Stem childTrunk = new 
        Stem(StemType.TRUNK, parentStem.getEndX(), parentStem.getEndY(), length, parentStem.getLocalDirection(), parentStem.getLayer()+1);
        parentStem.addChild(childTrunk);
        
    }
}
