package com.hardestfield.game.model;

/**
 * Created by mihai on 12/30/2014.
 * THis class helps the actor to jump more
 */
public class Beehive extends GameObject {
    public static float BEEHIVE_WIDTH = 0.3f;
    public static float BEEHIVE_HEIGHT = 0.3f;

    /**
     * Generic constructor
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public Beehive(float x, float y) {
        super(x, y, BEEHIVE_WIDTH, BEEHIVE_HEIGHT);
    }
}
