package edu.neu.csye6200.ui;

import edu.neu.csye6200.bg.BGGeneration;
import edu.neu.csye6200.bg.BGGrowthConstants;
import edu.neu.csye6200.bg.BGGrowthEventPublisher;
import edu.neu.csye6200.bg.BGRule;
import edu.neu.csye6200.bg.BGTest;
import edu.neu.csye6200.bg.StandardBGSamples;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is the main class of the application to test biological Growth. This
 * class also acts a observer to be notified of the plant growth.
 *
 * @author Sanjay Badlani
 */
public class BGGrowthAPP extends BGApp implements Observer {

    private static Logger log = Logger.getLogger(BGGrowthAPP.class.getName());

    protected JPanel mainPanel = null;
    protected JPanel northPanel = null;
    protected JButton startBtn;
    protected JButton stopBtn;
    protected JButton clearBtn;
    protected JButton resumeButton;
    protected JButton updateBtn;
    protected JLabel numberOfGenerationsLabel;
    protected JTextField numberOfGenerationsTextField;
    protected JButton selectStandardRuleBtn;
    protected JButton customizeRuleBtn;
    protected JLabel selectGrowthRule;
    protected JComboBox<String> selectGrowthRuleCB;
    protected JLabel ExtendTheTrunk;
    protected JComboBox<Boolean> extendTheTrunkCB;
    protected JLabel numberOfChildBranchesForTrunk;
    protected JTextField numberOfChildBranchesForTrunkTF;
    protected JLabel numberOfChildBranchesForBranch;
    protected JTextField numberOfChildBranchesForBranchTF;
    protected JLabel lengthOfTrunk;
    protected JTextField lengthOfTrunkTF;
    protected JLabel lengthOfBranch;
    protected JTextField lengthOfBranchTF;
    private Boolean usingStandardGrowth;
    private BGTest bgTest;
    private BGCanvas bgPanel;

    /**
     * Sample app constructor
     */
    public BGGrowthAPP() {
        frame.setSize(500, 400); // initial Frame size
        frame.setTitle("BGGrowth APP");

        menuMgr.createDefaultActions(); // Set up default menu items

        showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }

    /**
     * Create a main panel that will hold the bulk of our application display
     */
    @Override
    public JPanel getMainPanel() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, getNorthPanel());

        bgPanel = BGCanvas.getInstance();
        mainPanel.add(BorderLayout.CENTER, bgPanel);

        return mainPanel;
    }

    /**
     * Create a top panel that will hold control buttons
     *
     * @return
     */
    public JPanel getNorthPanel() {
        northPanel = new JPanel();
        //northPanel.setLayout(new FlowLayout());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        usingStandardGrowth = true;
        //northPanel.add(stopBtn);

        Box first = Box.createHorizontalBox();
        numberOfGenerationsLabel = new JLabel("Enter number Of Generations", JLabel.LEFT);
        numberOfGenerationsTextField = new JTextField();
        numberOfGenerationsTextField.setColumns(10);
        numberOfGenerationsTextField.setText("20");
        first.add(numberOfGenerationsLabel);
        first.add(numberOfGenerationsTextField);

        Box second = Box.createHorizontalBox();
        selectStandardRuleBtn = new JButton("Select Standard Rule");
        selectStandardRuleBtn.addActionListener(this); // Allow the app to hear about button pushes    	
        customizeRuleBtn = new JButton("Customize Your own rule"); // Allow the app to hear about button pushes
        customizeRuleBtn.addActionListener(this);
        second.add(selectStandardRuleBtn);
        second.add(customizeRuleBtn);

        Box third = Box.createHorizontalBox();
        selectGrowthRule = new JLabel("Select A Standard Growth Rule", JLabel.LEFT);
        selectGrowthRuleCB = new JComboBox();
        selectGrowthRuleCB.addItem(BGGrowthConstants.normalTreeWithThreeBranchs);
        selectGrowthRuleCB.addItem(BGGrowthConstants.normalTreeWithTwoBranchs);
        selectGrowthRuleCB.addItem(BGGrowthConstants.pineTree);
        selectGrowthRuleCB.addItem(BGGrowthConstants.bush);
        selectGrowthRuleCB.addActionListener(this);
        third.add(selectGrowthRule);
        third.add(selectGrowthRuleCB);
        //third.setEnabled(false);

        Box fourth = Box.createHorizontalBox();
        ExtendTheTrunk = new JLabel("Does the Trunk continue to Grow after Branching ?", JLabel.LEFT);
        extendTheTrunkCB = new JComboBox();
        extendTheTrunkCB.addItem(Boolean.TRUE);
        extendTheTrunkCB.addItem(Boolean.FALSE);
        extendTheTrunkCB.addActionListener(this);
        fourth.add(ExtendTheTrunk);
        fourth.add(extendTheTrunkCB);
        //fourth.setEnabled(false);

        Box fifth = Box.createHorizontalBox();
        numberOfChildBranchesForTrunk = new JLabel("Number Of Branches Trunk splits into ?", JLabel.LEFT);
        numberOfChildBranchesForTrunkTF = new JTextField();
        numberOfChildBranchesForTrunkTF.setColumns(10);
        lengthOfTrunk = new JLabel("Length Of Each Trunk ?", JLabel.LEFT);
        lengthOfTrunkTF = new JTextField();
        lengthOfTrunkTF.setColumns(10);
        fifth.add(numberOfChildBranchesForTrunk);
        fifth.add(numberOfChildBranchesForTrunkTF);
        fifth.add(lengthOfTrunk);
        fifth.add(lengthOfTrunkTF);
        //fifth.setEnabled(false);

        Box sixth = Box.createHorizontalBox();
        numberOfChildBranchesForBranch = new JLabel("Number Of Branches Each Branch splits into ?", JLabel.LEFT);
        numberOfChildBranchesForBranchTF = new JTextField();
        numberOfChildBranchesForBranchTF.setColumns(10);
        lengthOfBranch = new JLabel("Length Of Each Branch ?", JLabel.LEFT);
        lengthOfBranchTF = new JTextField();
        lengthOfBranchTF.setColumns(10);
        sixth.add(numberOfChildBranchesForBranch);
        sixth.add(numberOfChildBranchesForBranchTF);
        sixth.add(lengthOfBranch);
        sixth.add(lengthOfBranchTF);
        //sixth.setEnabled(false);

        Box seventh = Box.createHorizontalBox();
        startBtn = new JButton("START");
        stopBtn = new JButton("STOP");
        clearBtn = new JButton("RESET");
        updateBtn = new JButton("UPDATE");
        resumeButton = new JButton("RESUME");
        startBtn.addActionListener(this); // Allow the app to hear about button pushes
        // Allow the app to hear about button pushes
        stopBtn.addActionListener(this);
        resumeButton.addActionListener(this);
        clearBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        //stopBtn.setEnabled(false);
        seventh.add(startBtn);
        seventh.add(stopBtn);
        seventh.add(clearBtn);
        seventh.add(updateBtn);
        seventh.add(resumeButton);
        enableAndDisableButtons(true, false, false, false, false);

       

        Box mainColumn = Box.createVerticalBox();
        mainColumn.add(first);
        mainColumn.add(second);
        mainColumn.add(third);
        mainColumn.add(fourth);
        mainColumn.add(fifth);
        mainColumn.add(sixth);
        mainColumn.add(seventh);

        standardGrowthSelected();
        northPanel.add(mainColumn);

        return northPanel;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        log.info("We received an ActionEvent " + ae);
        if (ae.getSource() == selectStandardRuleBtn) {
            standardGrowthSelected();

        } else if (ae.getSource() == customizeRuleBtn) {
            selectGrowthRule.setEnabled(false);
            selectGrowthRuleCB.setEnabled(false);
            ExtendTheTrunk.setEnabled(true);
            extendTheTrunkCB.setEnabled(true);
            numberOfChildBranchesForTrunk.setEnabled(true);
            numberOfChildBranchesForTrunkTF.setEnabled(true);
            numberOfChildBranchesForBranch.setEnabled(true);
            numberOfChildBranchesForBranchTF.setEnabled(true);
            lengthOfBranch.setEnabled(true);
            lengthOfBranchTF.setEnabled(true);
            lengthOfTrunk.setEnabled(true);
            lengthOfTrunkTF.setEnabled(true);
            usingStandardGrowth = false;
            selectStandardRuleBtn.setEnabled(true);
            customizeRuleBtn.setEnabled(false);

        } else if (ae.getSource() == startBtn) {
            System.out.println("Start pressed");
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            clearBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            resumeButton.setEnabled(false);

            startPlantGrowth();
        } else if (ae.getSource() == stopBtn) {
            System.out.println("Stop pressed");
            startBtn.setEnabled(false);
            stopBtn.setEnabled(false);
            clearBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            resumeButton.setEnabled(true);
            stopPlantGrowth();
        } else if (ae.getSource() == clearBtn) {
            System.out.println("RESET pressed");
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            updateBtn.setEnabled(false);
            resumeButton.setEnabled(false);
            resetPlantGrowth();
        } else if (ae.getSource() == resumeButton) {
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            clearBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            resumeButton.setEnabled(false);
            resumePlantGrowth();
        } else if (ae.getSource() == updateBtn) {
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            clearBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            resumeButton.setEnabled(false);
            System.out.println("update pressed");

            startPlantGrowth();
        } else if (ae.getSource() == extendTheTrunkCB) {
            Boolean extendTrunk = (Boolean) extendTheTrunkCB.getSelectedItem();
            System.out.println(extendTrunk);
        }
    }

    public void enableAndDisableButtons(boolean start, boolean stop,
            boolean reset, boolean update, boolean resume) {
        startBtn.setEnabled(start);
        stopBtn.setEnabled(stop);
        clearBtn.setEnabled(reset);
        updateBtn.setEnabled(update);
        resumeButton.setEnabled(resume);
    }

    public void standardGrowthSelected() {
        selectGrowthRule.setEnabled(true);
        selectGrowthRuleCB.setEnabled(true);
        ExtendTheTrunk.setEnabled(false);
        extendTheTrunkCB.setEnabled(false);
        numberOfChildBranchesForTrunk.setEnabled(false);
        numberOfChildBranchesForTrunkTF.setEnabled(false);
        numberOfChildBranchesForBranch.setEnabled(false);
        numberOfChildBranchesForBranchTF.setEnabled(false);
        lengthOfBranch.setEnabled(false);
        lengthOfBranchTF.setEnabled(false);
        lengthOfTrunk.setEnabled(false);
        lengthOfTrunkTF.setEnabled(false);
        usingStandardGrowth = true;
        selectStandardRuleBtn.setEnabled(false);
        customizeRuleBtn.setEnabled(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        log.info("Window opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        log.info("Window closing");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        log.info("Window closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        log.info("Window iconified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        log.info("Window deiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        log.info("Window activated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        log.info("Window deactivated");
    }

    /**
     * Sample Wolf application starting point
     *
     * @param args
     */
    public static void main(String[] args) {
        BGGrowthAPP bgapp = new BGGrowthAPP();
        log.info("BGGrowth App started");
        bgapp.setCommunication();

    }

    private void setCommunication() {
        BGGrowthEventPublisher bgPublisher = BGGrowthEventPublisher.getInstance(); //we have a publisher

        bgPublisher.addObserver(this); //make the subscription
    }

    private void startPlantGrowth() {

        try {
            BGRule bgRule = null;
            int numberOfGenerations = Integer.parseInt(numberOfGenerationsTextField.getText());
            if (usingStandardGrowth) {
                String standardGrowthRuleString = (String) selectGrowthRuleCB.getSelectedItem();
                switch (standardGrowthRuleString) {
                    case "A Bush":
                        bgRule = StandardBGSamples.bushes;
                        break;
                    case "A Normal Tree With Two Branches":
                        bgRule = StandardBGSamples.normalTreeWithTwoBranches;
                        break;
                    case "A pine tree":
                        bgRule = StandardBGSamples.likePineTree;
                        break;
                    case "A Normal Tree With Three Branches":
                        bgRule = StandardBGSamples.normalTreeWithThreeBranches;
                }

            } else {
                bgRule = new BGRule();
                bgRule.setExtendTrunk((Boolean) extendTheTrunkCB.getSelectedItem());
                bgRule.setLengthOfBark(Integer.parseInt(lengthOfTrunkTF.getText()));
                bgRule.setNoOfChildsForTrunk(Integer.parseInt(numberOfChildBranchesForTrunkTF.getText()));
                bgRule.setLengthOfChild(Integer.parseInt(lengthOfBranchTF.getText()));
                bgRule.setNoOfChildsForBranch(Integer.parseInt(numberOfChildBranchesForBranchTF.getText()));

            }

            if (bgTest != null) {
                bgTest.updateBGRuleAndResume(bgRule);
                return;
            } else {
                bgTest = new BGTest("mangoTree", numberOfGenerations, bgRule);
            }

        } catch (Exception e) {
            System.out.println("The Data entered by user is not proper");
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        bgPanel.addbGGeneration((BGGeneration) arg);
        bgPanel.repaint();

    }

    private void stopPlantGrowth() {
        bgTest.suspend();
    }

    private void resetPlantGrowth() {
        bgTest.stop();
        bgPanel.clearbGGeneration();
        bgPanel.repaint();
    }

    private void resumePlantGrowth() {
        bgTest.resume();
    }

}
