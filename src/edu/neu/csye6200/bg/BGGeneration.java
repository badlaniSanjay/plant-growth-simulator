package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 *
 * @author sanjay badlani
 * NUID : 001237234
 * This class stores the a list of stem in a particular generation and their generation count
 */
public class BGGeneration {
    
    private int generationCount;
    
    private ArrayList<Stem> stemsInThisGeneration;

    public BGGeneration() {
    }

    public BGGeneration(int generationCount) {
        this.generationCount = generationCount;
        stemsInThisGeneration = new ArrayList<>();
        
    }
    
    public void addStem(Stem stem){
        stemsInThisGeneration.add(stem);
    }

    public ArrayList<Stem> getStemsInThisGeneration() {
        return stemsInThisGeneration;
    }

    public int getGenerationCount() {
        return generationCount;
    }
    
    

    @Override
    public String toString() {
        return "BGGeneration{" + "generationCount=" + generationCount + ", stemsInThisGeneration=" + stemsInThisGeneration + '}';
    }

    void addAllStems(ArrayList<Stem> childStems) {
        stemsInThisGeneration.addAll(childStems);
    }
    
     
    
}
