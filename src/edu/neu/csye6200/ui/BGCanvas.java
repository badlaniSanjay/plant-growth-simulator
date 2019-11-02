package edu.neu.csye6200.ui;

import edu.neu.csye6200.bg.BGGeneration;
import edu.neu.csye6200.bg.Stem;
import edu.neu.csye6200.bg.StemType;
import java.awt.BasicStroke;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * A sample canvas that draws a rainbow of lines
 *
 * @author MMUNSON
 */
public class BGCanvas extends JPanel {

    private static BGCanvas instance = null;
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(BGCanvas.class.getName());
    private int lineSize = 20;
    private Color col = null;
    private Color darkBrown     = new Color(101, 67, 33);
    private Color lightBrown     = new Color(181, 101, 29);
    private Color skyBlue = new Color(135,206,250);
    private long counter = 0L;
    private int ctr = 0;
    private ArrayList<BGGeneration> bGGenerationList = new ArrayList<>();

    /**
     * CellAutCanvas constructor
     */
    private BGCanvas() {
        col = Color.WHITE;
    }

    public static BGCanvas getInstance() {
        if (instance == null) {
            instance = new BGCanvas();
        }
        return instance;
    }

    /**
     * The UI thread calls this method when the screen changes, or in response
     * to a user initiated call to repaint();
     */
    public void paint(Graphics g) {
        drawBG(g); // Our Added-on drawing
    }

    /**
     * Draw the CA graphics panel
     *
     * @param g
     */
    public void drawBG(Graphics g) {
        log.info("Drawing BG " + counter++);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.rotate(Math.toRadians(180));
        // g2d.scale(1, -1);
        //g2d.rotate(3.14);
        Dimension size = getSize();

        g2d.setColor(skyBlue);
        g2d.fillRect(0, 0,  size.width, (int)(0.70 *size.height));
        //g2d.setColor(Color.BLACK);
       // g2d.fillRect(0, 0, size.width- (int)(0.75 * size.w), size.height);
        
       g2d.setColor(Color.GREEN);
        g2d.fillRect(0, (int)(0.70 *size.height),  size.width, size.height);

        for (BGGeneration bGGeneration : bGGenerationList) {

            for (Stem eachStem : bGGeneration.getStemsInThisGeneration()) {
                if(eachStem.getStemType().equals(StemType.TRUNK)){
                g2d.setStroke(new BasicStroke(10));
                paintLine(g2d, (int) eachStem.getX(), size.height - (int) eachStem.getY(),
                        (int) eachStem.getEndX(),
                        size.height - (int) eachStem.getEndY(), darkBrown);
                }
                else {
                    g2d.setStroke(new BasicStroke(3));
                    paintLine(g2d, (int) eachStem.getX(), size.height - (int) eachStem.getY(),
                        (int) eachStem.getEndX(),
                        size.height - (int) eachStem.getEndY(), lightBrown);
                    
                }
                
            }

        }

    }

    /*
	 * A local routine to ensure that the color value is in the 0 to 255 range.
     */
    private int validColor(int colorVal) {
        if (colorVal > 255) {
            colorVal = 255;
        }
        if (colorVal < 0) {
            colorVal = 0;
        }
        return colorVal;
    }

    /**
     * A convenience routine to set the color and draw a line
     *
     * @param g2d the 2D Graphics context
     * @param startx the line start position on the x-Axis
     * @param starty the line start position on the y-Axis
     * @param endx the line end position on the x-Axis
     * @param endy the line end position on the y-Axis
     * @param color the line color
     */
    private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
        g2d.setColor(color);
        g2d.drawLine(startx, starty, endx, endy);
    }

 

    /*    
    * This method is used to add a new BG Generation to List of BGGeneration that will be drawn on canvas.


     */
    public void addbGGeneration(BGGeneration bGGeneration) {
        bGGenerationList.add(bGGeneration);
    }

    public void clearbGGeneration() {
        bGGenerationList.clear();
    }

}
