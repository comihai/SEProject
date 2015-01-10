package com.hardestfield.game.model;

/**
 * Created by mihai on 12/30/2014.
 * This class is for counting the score
 */
public class Acorn extends GameObject {
    public static final float ACORN_WIDTH = 0.5f;
    public static final float ACORN_HEIGHT = 0.8f;
    public static final int ACORN_SCORE = 10;

    protected float stateTime;

    /**
     * Generic constructor
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public Acorn(float x, float y) {
        super(x, y, ACORN_WIDTH, ACORN_HEIGHT);
        stateTime = 0;
    }

    public Acorn(float x, float y, float width, float height) {
        super(x, y, width, height);
        stateTime = 0;
    }

    /**
     * This function accumulates the time evolution of the game
     *
     * @param deltaTime The time interval for updating
     */
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
}
