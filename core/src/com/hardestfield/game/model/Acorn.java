package com.hardestfield.game.model;

/**
 * Created by mihai on 12/30/2014.
 * This class is for counting the score
 */
public class Acorn extends GameObject{
    public static final float ACORN_WIDTH = 0.5f;
    public static final float ACORN_HEIGHT = 0.8f;
    public static final int ACORN_SCORE = 10;

    float stateTime;

    public Acorn(float x, float y) {
        super(x, y, ACORN_WIDTH,ACORN_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime)
    {
        stateTime += deltaTime;
    }
}
