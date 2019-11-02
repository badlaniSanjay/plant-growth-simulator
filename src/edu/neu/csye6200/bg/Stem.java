package edu.neu.csye6200.bg;

import java.io.Serializable;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Sanjay Narendra Badlani NUID : 001237234 This is a basic stem class
 * which has all the properties of a stem.
 *
 */
public class Stem implements Serializable {

    private StemType stemType;
    private double x;
    private double y;
    private double endX;
    private double endY;
    private double length;
    private double localDirection;
    private int layer;
    private int childStemCount = 0;
    private int numberOfChildStems;
    DecimalFormat df = new DecimalFormat("#.##");
    private Stem[] childStems;

    public Stem(StemType stemType, double x, double y, double length, double localDirection, int layer) {
        this.stemType = stemType;
        this.x = x;
        this.y = y;
        this.length = length;
        this.localDirection = localDirection;
        this.layer = layer;
        endX = x + length * cos(Math.toRadians(localDirection));
        endY = y + length * sin(Math.toRadians(localDirection));
    }

    public void setNumberOfChildStems(int numberOfChildStems) {
        this.numberOfChildStems = numberOfChildStems;
    }

    public void addChild(Stem childStem) {
        if (childStems == null) {
            childStems = new Stem[numberOfChildStems];
        }
        childStems[childStemCount++] = childStem;
    }

    public String displayStem(int layer) {
        StringBuilder sb = new StringBuilder();
        sb.append("At Layer " + layer + " || ");
        sb.append(toString());
        sb.append(System.getProperty("line.separator"));
        for (Stem eachChildStem : childStems) {
            sb.append(eachChildStem.displayStem(layer + 1));
            sb.append(System.getProperty("line.separator"));

        }
        return sb.toString();
    }

    public double[] returnDimensions() {
        double[] dimensions = new double[4];
        dimensions[0] = Math.min(x, endX);
        dimensions[1] = Math.max(x, endX);
        dimensions[2] = Math.min(y, endY);
        dimensions[3] = Math.max(y, endY);

        for (Stem eachChild : childStems) {
            double[] tempDimensions = eachChild.returnDimensions();
            dimensions[0] = Math.min(dimensions[0], tempDimensions[0]);
            dimensions[1] = Math.max(dimensions[1], tempDimensions[1]);
            dimensions[2] = Math.min(dimensions[2], tempDimensions[2]);
            dimensions[3] = Math.max(dimensions[3], tempDimensions[3]);
        }

        return dimensions;
    }

    public StemType getStemType() {
        return stemType;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public double getLength() {
        return length;
    }

    public double getLocalDirection() {
        return localDirection;
    }

    public int getLayer() {
        return layer;
    }

    public int getChildStemCount() {
        return childStemCount;
    }

    public int getNumberOfChildStems() {
        return numberOfChildStems;
    }

    public Stem[] getChildStems() {
        return childStems;
    }

    @Override
    public String toString() {
        return "Stem{" + "x=" + x + ", y=" + y + ", endX=" + endX + ", endY=" + endY + ", length=" + length + ", localDirection=" + localDirection + '}';
    }

}
