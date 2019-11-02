package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanjay badlani NUID : 001237234 This is a runnable thread which grows
 * the plant.
 */
public class BGTest implements Runnable {

    private BGGrowthEventPublisher bGGrowthEventPublisher = BGGrowthEventPublisher.getInstance();
    private int numberOfGenerations;
    private BGRule bGRule;
    private BGGrow bgGrow;

    private ArrayList<BGGeneration> mangoTree;

    Thread thrd;/*from   j a v  a2s  .c  o m*/
    boolean suspended;
    boolean stopped;

    public BGTest(String name, int numberOfGenerations, BGRule bGRule) {
        // super(name);
        thrd = new Thread(this, name);
        suspended = false;
        stopped = false;

        createBaseStem();
        bgGrow = new BGGrow();

        this.numberOfGenerations = numberOfGenerations;
        this.bGRule = bGRule;
        thrd.start();
    }

    public void createBaseStem() {
        mangoTree = new ArrayList<>();
        Stem baseStem = new Stem(StemType.TRUNK, 500, 0, 100, 90, 1);
        BGGeneration baseLayer = new BGGeneration(1);
        baseLayer.addStem(baseStem);
        mangoTree.add(baseLayer);
    }

    public void run() {
        try {
            for (int i = 0; i < numberOfGenerations; i++) {

                bGGrowthEventPublisher.doAction(mangoTree.get(mangoTree.size() - 1));
                bgGrow.growThisGenerationSet(mangoTree, bGRule);
                Thread.sleep(5000);
                synchronized (this) {
                    while (suspended) {
                        wait();
                    }
                    if (stopped) {
                        break;

                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(BGTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    synchronized public void stop() {
        stopped = true;
        suspended = false;
        notify();
    }

    synchronized public void suspend() {
        suspended = true;
        notify();
    }

    synchronized public void resume() {

        suspended = false;
        if (stopped == true) {

            stopped = false;
            thrd = new Thread(this, "name");
            createBaseStem();
            thrd.start();
        } else {

            notify();
        }
    }

    synchronized public void updateBGRuleAndResume(BGRule bGRule) {
        this.bGRule = bGRule;
        suspended = false;
        if (stopped == true) {

            stopped = false;
            thrd = new Thread(this, "name");
            createBaseStem();
            thrd.start();
        } else {

            notify();
        }
    }

}
