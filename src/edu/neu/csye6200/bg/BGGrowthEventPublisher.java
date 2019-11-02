package edu.neu.csye6200.bg;

import java.util.Observable;
import java.util.logging.Logger;

/**
 *
 * @author sanjay badlani NUID : 001237234 This class is used to notify the
 * observer whenever a new Generation of stem is added to the plant.
 */
public class BGGrowthEventPublisher extends Observable {

    private static Logger log = Logger.getLogger(BGGrowthEventPublisher.class.getName());

    private static BGGrowthEventPublisher instance = null;

    public static BGGrowthEventPublisher getInstance() {
        if (instance == null) {
            instance = new BGGrowthEventPublisher();
        }
        return instance;
    }

    //constructor
    private BGGrowthEventPublisher() {

        //System.out.println("EventPublisher - we are here");
    }

    //An event has occurred, so notify the subscribers
    public void doAction(BGGeneration bgGeneration) {
        log.info("Notifing the Observer for " + bgGeneration);
        setChanged(); //Indicate that a change has happened
        notifyObservers(bgGeneration);
    }

}
