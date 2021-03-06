package com.hardestfield.game.model;

/**
 * Created by mihai on 12/31/2014.
 * This class is used to score. AcornLeaf => 50p | Acorn => 10p
 */
public class AcornLeaf extends Acorn {
    public static final float ACORNLEAF_WIDTH = 0.8f;
    public static final float ACORNLEAF_HEIGHT = 0.8f;
    public static final int ACORNLEAF_SCORE = 20;

    /**
     * Generic constructor
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public AcornLeaf(float x, float y) {
        super(x, y, ACORNLEAF_WIDTH, ACORNLEAF_HEIGHT);
    }
}
