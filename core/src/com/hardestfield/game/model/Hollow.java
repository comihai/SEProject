package com.hardestfield.game.model;

/**
 * Created by mihai on 1/1/2015.
 *
 * This class is for finishing the game or moving to the next level
 */
public class Hollow extends GameObject {

    public static float HOLLOW_WIDTH = 6f;
    public static float HOLLOW_HEIGHT = 2f;

    /**
     * Generic constructor
     * @param x  position on the x axis
     * @param y  position on the y axis
     */
    public Hollow (float x, float y) {
        super(x, y, HOLLOW_WIDTH, HOLLOW_HEIGHT);
    }
}
